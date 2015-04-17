package co.nz.kastana.android.telstra.sydney.listview.events;

import co.nz.kastana.android.telstra.sydney.listview.model.GetListResponse;

public class GetListReadyEvent implements SuccessEvent {

    private GetListResponse response;

    public GetListReadyEvent(GetListResponse response) {
        this.response = response;
    }

    public GetListResponse getCustomerResponse() {
        return response;
    }
}