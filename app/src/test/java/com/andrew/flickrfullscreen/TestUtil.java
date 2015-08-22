package com.andrew.flickrfullscreen;

import com.andrew.flickrfullscreen.model.FlickrPhotoResponse;
import com.andrew.flickrfullscreen.model.Item;
import com.andrew.flickrfullscreen.model.Media;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static FlickrPhotoResponse getResponse(int size) {

        FlickrPhotoResponse response = new FlickrPhotoResponse();
        List<Item> items = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            Item item = new Item();
            Media media = new Media();
            media.m = "http://link/" + i;
            item.media = media;
            items.add(item);
        }
        response.items = items;
        return response;
    }
}
