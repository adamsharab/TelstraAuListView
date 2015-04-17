package co.nz.kastana.android.telstra.sydney.listview.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.nz.kastana.android.telstra.sydney.listview.model.Customer;
import static co.nz.kastana.android.telstra.sydney.listview.R.layout.list_customers;
import static co.nz.kastana.android.telstra.sydney.listview.R.id.title;
import static co.nz.kastana.android.telstra.sydney.listview.R.id.description;
import static co.nz.kastana.android.telstra.sydney.listview.R.id.imageView;

public class CustomerListAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<Customer> mCustomers;

    public CustomerListAdapter(Context context, List<Customer> customers) {
        super(context, list_customers);
        mContext = context;
        mCustomers = customers;
    }

    @Override
    public int getCount() {
        return mCustomers.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolderItem;
        if(convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(list_customers, parent, false);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.title = (TextView) convertView.findViewById(title);
            viewHolderItem.description = (TextView) convertView.findViewById(description);
            viewHolderItem.imageView = (ImageView) convertView.findViewById(imageView);
            convertView.setTag(viewHolderItem);
        }
        else {
            viewHolderItem = (ViewHolderItem) convertView.getTag();
        }
        hideViewIfEmpty(viewHolderItem.title, mCustomers.get(position).getTitle());
        hideViewIfEmpty(viewHolderItem.description, mCustomers.get(position).getDescription());
        hideViewIfEmpty(viewHolderItem.imageView, mCustomers.get(position).getImageHref());
        adjustParentLayoutIfNecessary(viewHolderItem.imageView);
        setTextViewText(mCustomers.get(position).getTitle(), viewHolderItem.title);
        setTextViewText(mCustomers.get(position).getDescription(), viewHolderItem.description);
        mCustomers.get(position).loadImage(viewHolderItem.imageView, mCustomers.get(position).getImageHref());
        return convertView;
    }

    static class ViewHolderItem {
        TextView title;
        TextView description;
        ImageView imageView;
    }

    private synchronized static void hideViewIfEmpty(View view, String text){
        if(text == null || TextUtils.isEmpty(text))
            view.setVisibility(View.GONE);
        else
            view.setVisibility(View.VISIBLE);
    }

    private synchronized static void setTextViewText(String text, TextView textView) {
        textView.setText(text != null? text.replaceAll("\\\\n", "\\\n").trim(): null);
    }

    private void adjustParentLayoutIfNecessary(ImageView imageView){
        if(imageView.getVisibility() == View.GONE){
            ((LinearLayout)imageView.getParent()).setWeightSum(3);
        }
    }

}
