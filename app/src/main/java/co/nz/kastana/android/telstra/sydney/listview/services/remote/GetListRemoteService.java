package co.nz.kastana.android.telstra.sydney.listview.services.remote;

import co.nz.kastana.android.telstra.sydney.listview.model.GetListResponse;
import co.nz.kastana.android.telstra.sydney.listview.services.AbstractRemoteEndpointProvider;
import co.nz.kastana.android.telstra.sydney.listview.services.BaseRemoteService;
import co.nz.kastana.android.telstra.sydney.listview.services.RemoteOperation;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by ahmed on 16/04/15.
 */
public class GetListRemoteService extends BaseRemoteService<GetListRemoteService.GetListRemoteEndpoint, GetListCallback>{

    public void getCustomerList(){
        execute(new RemoteOperation<GetListRemoteEndpoint, GetListCallback>() {
            @Override
            public void execute(GetListRemoteEndpoint endpoint, GetListCallback callback) {
                endpoint.getCustomerList(callback);
            }
        });
    }

    @Override
    public GetListCallback getCallback() {
        return getInstance(GetListCallback.class);
    }

    @Override
    protected GetListRemoteEndpoint getEndpoint() {
        return getInstance(GetListRemoteEndpointProvider.class).get();
    }

    public static class GetListRemoteEndpointProvider extends AbstractRemoteEndpointProvider<GetListRemoteEndpoint> {
        @Override
        protected Class<GetListRemoteEndpoint> getEndpointClass() {
            return GetListRemoteEndpoint.class;
        }
    }

    public interface GetListRemoteEndpoint {
        @GET("/u/746330/facts.json")
        void getCustomerList(Callback<GetListResponse> callback);
    }
}
