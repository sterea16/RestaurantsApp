package com.example.restaurantsApplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.restaurantsApplication.model.Item;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private ArrayList<Item> items;
    private Context context;

    public ItemAdapter(ArrayList<Item> items, Context context){
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSubtitle());
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, item.getIcon()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private AppCompatImageView image;
        private AppCompatTextView title;
        private AppCompatTextView subtitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.list_item_icon);
            title = itemView.findViewById(R.id.list_item_title);
            subtitle = itemView.findViewById(R.id.list_item_subTitle);
        }
    }
}
