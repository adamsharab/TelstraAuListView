package co.nz.kastana.android.telstra.sydney.listview.services;

import android.content.Context;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static roboguice.RoboGuice.getInjector;

public class BaseRemoteService<E, C extends AbstractCallback> {

    @Inject
    Context context;

    Optional<C> waitingCallback = absent();

    public void cancel() {
        if(waitingCallback.isPresent()) {
            waitingCallback.get().cancel();
            waitingCallback = absent();
        }
    }

    protected void execute(RemoteOperation<E, C> operation) {
        cancel();

        E endpoint = getEndpoint();
        C callback = getCallback();
        operation.execute(endpoint, callback);

        waitingCallback = of(callback);
    }

    protected <I> I getInstance(Class<I> clazz) {
        return getInjector(context).getInstance(clazz);
    }

    protected E getEndpoint() {
        return null;
    }
    protected C getCallback() {
        return null;
    }
}