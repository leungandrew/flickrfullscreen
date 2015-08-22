package com.andrew.flickrfullscreen;


import com.andrew.flickrfullscreen.model.FlickrPhotoResponse;

import retrofit.Callback;
import retrofit.http.GET;

public interface FlickrRestService {

    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    void getPhotos(Callback<FlickrPhotoResponse> callback);
}
