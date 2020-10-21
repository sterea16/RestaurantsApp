package com.example.restaurantsApplication.restaurants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.example.restaurantsApplication.ItemAdapter;
import com.example.restaurantsApplication.R;
import com.example.restaurantsApplication.model.Item;
import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        getWindow().setAllowEnterTransitionOverlap(false);

        progressBar = findViewById(R.id.progress_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemAdapter itemAdapter = new ItemAdapter(getMockedItems(), this);
        recyclerView.setAdapter(itemAdapter);

    }

    private ArrayList<Item> getMockedItems(){
        progressBar.setVisibility(View.VISIBLE);
        String[] titles = new String[20];
        for(int i = 0; i < titles.length; ++i)
            titles[i] = "Title " + (int) (i + 1);

        String[] subtitles = new String[20];
        for(int i = 0; i < titles.length; ++i)
            subtitles[i] = "Title " + (int) (i + 1);

        ArrayList<Item> items = new ArrayList<>();
        for(int i = 0; i < 20; ++i)
            items.add(new Item(R.drawable.ic_launcher_background, titles[i], subtitles[i]));
        progressBar.setVisibility(View.GONE);
        return items;
    }
}
