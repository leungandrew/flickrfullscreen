package com.andrew.flickrfullscreen;

import android.app.Application;

import dagger.ObjectGraph;

public class FlickrApplication extends Application {
    protected static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(getModule());
    }

    protected Object getModule() {
        return new FlickrModule();
    }

    public static void inject(Object object) {
        objectGraph.inject(object);
    }
}
