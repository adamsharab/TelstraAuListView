package co.nz.kastana.android.telstra.sydney.listview.events;

public class GetListErrorEvent extends AbstractErrorEvent {

    public GetListErrorEvent() {
    }

    public GetListErrorEvent(String message) {
        super(message);
    }
}
