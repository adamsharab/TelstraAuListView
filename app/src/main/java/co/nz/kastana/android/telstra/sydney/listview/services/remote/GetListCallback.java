package co.nz.kastana.android.telstra.sydney.listview.services.remote;

import android.content.Context;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import co.nz.kastana.android.telstra.sydney.listview.events.ErrorEvent;
import co.nz.kastana.android.telstra.sydney.listview.events.GetListErrorEvent;
import co.nz.kastana.android.telstra.sydney.listview.events.GetListReadyEvent;
import co.nz.kastana.android.telstra.sydney.listview.model.GetListResponse;
import co.nz.kastana.android.telstra.sydney.listview.services.BaseRemoteCallback;

import static co.nz.kastana.android.telstra.sydney.listview.R.string.error_generic_getList;

public class GetListCallback extends BaseRemoteCallback<GetListResponse> {
    private String genericErrorMessage;

    @Inject
    public GetListCallback(Context context) {
        genericErrorMessage = context.getString(error_generic_getList);
    }

    @Override
    protected ErrorEvent getGenericErrorEvent(String message) {
        return new GetListErrorEvent(genericErrorMessage);
    }

    @Override
    protected String getGenericErrorMessage() {
        return genericErrorMessage;
    }

    @Override
    protected void onSuccess(Optional<GetListResponse> object) {
        if (object.isPresent()) {
            GetListResponse customerResponse = object.get();
            publish(new GetListReadyEvent(customerResponse));
        } else {
            publish(new GetListErrorEvent(genericErrorMessage));
        }
    }
}
