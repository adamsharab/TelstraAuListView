package co.nz.kastana.android.telstra.sydney.listview.services;

import com.google.inject.AbstractModule;

import retrofit.RestAdapter;

public class RemoteGuiceBindings extends AbstractModule {
    @Override
    protected void configure() {
        bind(RestAdapter.Builder.class)
                .toProvider(RestAdapterBuilderProvider.class)
                .asEagerSingleton();
    }
}
