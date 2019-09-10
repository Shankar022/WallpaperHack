package com.example.wallpaperhack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.wallpaperhack.R;
import com.example.wallpaperhack.adapters.WallpapersAdapter;
import com.example.wallpaperhack.modelsPOJO.Wallpaper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WallpaperActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    WallpapersAdapter adapter;
    List<Wallpaper> WallpaperList;

    DatabaseReference dbWallpaper;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        WallpaperList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_wallpaper);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WallpapersAdapter(this,WallpaperList);
        progressBar = findViewById(R.id.progressBarWallpaper);
        recyclerView.setAdapter(adapter );

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("categoryName");

        Toolbar toolbar = findViewById(R.id.wallpaper_toolbar);
        toolbar.setTitle(categoryName);
        setSupportActionBar(toolbar);

        progressBar.setVisibility(View.VISIBLE);
        assert categoryName != null;
        dbWallpaper = FirebaseDatabase.getInstance().getReference("images").child(categoryName);
        dbWallpaper.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);

                if(dataSnapshot.exists()){
                    for(DataSnapshot wallpaperSnapshot:dataSnapshot.getChildren()){
                        Wallpaper w = wallpaperSnapshot.getValue(Wallpaper.class);
                        WallpaperList.add(w);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
