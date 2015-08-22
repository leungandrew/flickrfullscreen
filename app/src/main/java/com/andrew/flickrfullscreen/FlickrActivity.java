package com.andrew.flickrfullscreen;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.andrew.flickrfullscreen.model.FlickrPhotoResponse;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FlickrActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.list)
    RecyclerView list;

    @Inject
    FlickrRestService restService;

    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;

    PhotoAdapter adapter;

    FlickrPhotoResponse flickrPhotoResponse;

    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlickrApplication.inject(this);
        setContentView(R.layout.activity_flickr);
        ButterKnife.bind(this);
        swipe.setOnRefreshListener(this);
        fetchList(false);
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    private void fetchList(final boolean fresh) {
        restService.getPhotos(new Callback<FlickrPhotoResponse>() {
            @Override
            public void success(FlickrPhotoResponse flickrPhotoResponse, Response response) {
                FlickrActivity.this.flickrPhotoResponse = flickrPhotoResponse;
                setupList(fresh);
                swipe.setRefreshing(false);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("", "Failure!");
                swipe.setRefreshing(false);
            }
        });
    }

    private void setupList(boolean fresh) {
        if (adapter == null || fresh) {
            adapter = new PhotoAdapter(this, flickrPhotoResponse.items);
            list.setAdapter(adapter);
            list.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            adapter.addItems(flickrPhotoResponse.items);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefresh() {
        fetchList(true);
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onFetchMoreEvent(FetchMoreEvent event) {
        fetchList(false);
    }
}
