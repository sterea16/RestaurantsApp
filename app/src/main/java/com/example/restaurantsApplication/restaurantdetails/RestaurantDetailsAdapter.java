package com.example.restaurantsApplication.restaurantdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantsApplication.R;

import java.util.ArrayList;

public class RestaurantDetailsAdapter extends RecyclerView.Adapter<RestaurantDetailsAdapter.ItemDetailsViewHolder> {

    private final Context context;
    private final ArrayList<String> photos;

    public RestaurantDetailsAdapter(ArrayList<String> photos, Context context){
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ItemDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new ItemDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetailsViewHolder holder, int position) {
        Glide.with(context).load(photos.get(position)).into(holder.photoView);
        holder.photoView.setContentDescription(context.getResources().getString(R.string.photo_content_description) + (position + 1));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class ItemDetailsViewHolder extends RecyclerView.ViewHolder{
        private final AppCompatImageView photoView;

        public ItemDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.details_photo_imageView);
        }
    }
}
