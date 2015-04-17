package co.nz.kastana.android.telstra.sydney.listview.model;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Customer {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void loadImage(ImageView imageView, String url){
        if(hasImageHref(url)) {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private boolean hasImageHref(String url) {
        return url != null && !TextUtils.isEmpty(url);
    }
}
