package com.andrew.flickrfullscreen;

public class TestFlickrApplication extends FlickrApplication {

    @Override
    protected Object getModule() {
        return new TestFlickrModule();
    }
}