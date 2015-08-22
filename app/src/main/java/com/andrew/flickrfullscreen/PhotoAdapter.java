package com.andrew.flickrfullscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andrew.flickrfullscreen.model.Item;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    @Inject
    Bus bus;

    List<Item> items;
    Picasso picasso;

    public PhotoAdapter(Context context, List<Item> items) {
        FlickrApplication.inject(this);
        this.items = items;
        picasso = Picasso.with(context);
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.view_image, null);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Item item = items.get(position);
        picasso.load(item.media.m).into(holder.image);
        if (position == items.size() - 1) {
            bus.post(new FetchMoreEvent());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
