package co.nz.kastana.android.telstra.sydney.listview.services;

import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static co.nz.kastana.android.telstra.sydney.listview.BuildConfig.ENDPOINT;
import static retrofit.RestAdapter.LogLevel.FULL;

public abstract class AbstractRemoteEndpointProvider<T> implements Provider<T> {

    @Inject RestAdapter.Builder builder;

    @Override
    public T get() {
        Class<T> endpointClazz = getEndpointClass();
        return builder
                .setLogLevel(FULL)
                .setEndpoint(ENDPOINT)
                .setConverter(getJsonConverter())
                .build()
                .create(endpointClazz);
    }

    protected abstract Class<T> getEndpointClass();

    private GsonConverter getJsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return new GsonConverter(gsonBuilder.create());
    }

}