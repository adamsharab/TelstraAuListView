package co.nz.kastana.android.telstra.sydney.listview.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.inject.Inject;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import co.nz.kastana.android.telstra.sydney.listview.R;
import co.nz.kastana.android.telstra.sydney.listview.adapters.CustomerListAdapter;
import co.nz.kastana.android.telstra.sydney.listview.events.GetListErrorEvent;
import co.nz.kastana.android.telstra.sydney.listview.events.GetListReadyEvent;
import co.nz.kastana.android.telstra.sydney.listview.model.Customer;
import co.nz.kastana.android.telstra.sydney.listview.services.remote.GetListRemoteService;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static co.nz.kastana.android.telstra.sydney.listview.R.layout.activity_main;
import static co.nz.kastana.android.telstra.sydney.listview.R.id.viewAnimator;
import static co.nz.kastana.android.telstra.sydney.listview.R.id.list;

@ContentView(activity_main)
public class MainActivity extends BaseActivity {

    private final static int VIEW_PROGRESS_BAR  = 0;
    private final static int VIEW_LIST          = 1;
    @InjectView(list)           private ListView listView;
    @InjectView(viewAnimator)   private ViewAnimator animator;
    @Inject                     private GetListRemoteService getListRemoteService;

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus.unRegister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateActionBarTitle(null);
        getList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateActionBarTitle(null);
            displayProgressBar();
            getList();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onCustomerListReadyEvent(GetListReadyEvent readyEvent){
        updateActionBarTitle(readyEvent.getCustomerResponse().getActionBarTitle());
        displayList();
        listView.setAdapter(new CustomerListAdapter(this, filterCustomersForEmptyRows(readyEvent.getCustomerResponse().getCustomers())));
    }

    @Subscribe
    public void onCustomerListErrorEvent(GetListErrorEvent errorEvent){
        updateActionBarTitle(null);
        displayList();
        Toast.makeText(this, errorEvent.getMessage(), Toast.LENGTH_LONG).show();
    }

    private static List<Customer> filterCustomersForEmptyRows(List<Customer> customers){
        List<Customer> mCustomers = new ArrayList<Customer>();
        if(customers == null)
            return mCustomers;
        for (Customer customer: customers){
            if(isNotEmptyRow(customer))
                mCustomers.add(customer);
        }
        return mCustomers;
    }

    private static boolean isNotEmptyRow(Customer customer) {
        return !(isEmpty(customer.getTitle()) && isEmpty(customer.getDescription()) && isEmpty(customer.getImageHref()));
    }

    private static boolean isEmpty(String text){
        return text == null || TextUtils.isEmpty(text);
    }

    private void displayList() {
        animator.setDisplayedChild(VIEW_LIST);
    }

    private void displayProgressBar() {
        animator.setDisplayedChild(VIEW_PROGRESS_BAR);
    }

    private void getList() {
        getListRemoteService.getCustomerList();
    }

    private void updateActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
