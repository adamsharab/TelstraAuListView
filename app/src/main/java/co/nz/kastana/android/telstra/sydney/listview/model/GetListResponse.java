package co.nz.kastana.android.telstra.sydney.listview.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListResponse {
    @SerializedName("title")
    private String actionBarTitle;
    @SerializedName("rows")
    private List<Customer> customers;

    public String getActionBarTitle() {
        return actionBarTitle;
    }

    public void setActionBarTitle(String actionBarTitle) {
        this.actionBarTitle = actionBarTitle;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
