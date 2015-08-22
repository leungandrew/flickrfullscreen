package com.andrew.flickrfullscreen;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Provides;
import retrofit.RestAdapter;

public class TestFlickrModule {

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public RestAdapter.Builder provideRestAdapterBuilder(final TestRetrofitClient client) {
        return new RestAdapter.Builder()
                .setExecutors(new Executor() {
                    @Override
                    public void execute(@NonNull Runnable callback) {
                        callback.run();
                    }
                }, new Executor() {
                    @Override
                    public void execute(@NonNull Runnable callback) {
                        callback.run();
                    }
                })
                .setClient(client);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public FlickrRestService provideRestService(RestAdapter.Builder builder) {
        return builder
                .build()
                .create(FlickrRestService.class);
    }
}
