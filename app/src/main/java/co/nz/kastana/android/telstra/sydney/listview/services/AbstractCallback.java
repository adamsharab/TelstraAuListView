package co.nz.kastana.android.telstra.sydney.listview.services;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import co.nz.kastana.android.telstra.sydney.listview.events.ErrorEvent;
import co.nz.kastana.android.telstra.sydney.listview.events.SuccessEvent;
import co.nz.kastana.android.telstra.sydney.listview.roboguice.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.google.common.base.Optional.fromNullable;

public abstract class AbstractCallback<T> implements Callback<T> {

    @Inject
    private EventBus eventBus;
    private boolean isCanceled = false;

    @Override
    public void success(T object, Response response) {
        if(!isCanceled()) {
            onSuccess(fromNullable(object));
        }
    }

    @Override
    public void failure(RetrofitError error) {
        if(!isCanceled()) {
            onFailure(error);
        }
    }

    protected void publish(SuccessEvent event) {
        eventBus.publish(event);
    }

    protected void publish(ErrorEvent event) {
        eventBus.publish(event);
    }

    public void cancel() {
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    protected abstract void onSuccess(Optional<T> object);
    protected abstract void onFailure(RetrofitError error);
}
