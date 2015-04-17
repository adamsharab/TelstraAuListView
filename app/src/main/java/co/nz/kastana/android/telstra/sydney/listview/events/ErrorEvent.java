package co.nz.kastana.android.telstra.sydney.listview.events;

public interface ErrorEvent {
    int getCode();
    String getMessage();
    void setCode(int code);
    void setMessage(String message);
}
