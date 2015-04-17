package co.nz.kastana.android.telstra.sydney.listview.roboguice;


import com.google.inject.Singleton;
import com.squareup.otto.Bus;

@Singleton
public class EventBus {

    private static final Bus BUS = new Bus();

    public void register(Object object) {
        BUS.register(object);
    }

    public void unRegister(Object object) {
        BUS.unregister(object);
    }

    public void publish(Object event) {
        BUS.post(event);
    }
}