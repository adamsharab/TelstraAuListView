package co.nz.kastana.android.telstra.sydney.listview.services;

public interface RemoteOperation<E, C extends AbstractCallback> {
    public void execute(E endpoint, C callback);
}