package com.andrew.flickrfullscreen;

import android.util.Log;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

@Module(
        injects = {
                FlickrActivity.class,
                PhotoAdapter.class,
        }
)
public class FlickrModule {
    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public RestAdapter.Builder provideRestAdapter() {
        return new RestAdapter.Builder().setClient(new OkClient());
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public FlickrRestService provideRestService(RestAdapter.Builder builder) {
        return builder
                .setEndpoint("https://api.flickr.com")
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        Log.d("", "Retrofit error!");
                        if (cause != null) {
                            cause.printStackTrace();
                            Log.d("", cause.getUrl());
                        }
                        return cause;
                    }
                })
                .build()
                .create(FlickrRestService.class);
    }

    @SuppressWarnings("unused")
    @Provides
    @Singleton
    public Bus providesBus() {
        return new Bus();
    }
}
