package co.nz.kastana.android.telstra.sydney.listview.events;

public abstract class AbstractErrorEvent implements ErrorEvent {

    protected int code;
    protected String message;

    public AbstractErrorEvent() {
    }

    public AbstractErrorEvent(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}