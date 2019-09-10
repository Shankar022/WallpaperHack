package com.example.wallpaperhack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaperhack.R;
import com.example.wallpaperhack.modelsPOJO.Wallpaper;

import java.util.List;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.WallpaperViewHolder> {

    private Context mCtx;
    private List<Wallpaper> WallpaperList;

    public WallpapersAdapter(Context mCtx, List<Wallpaper> wallpaperList) {
        this.mCtx = mCtx;
        this.WallpaperList = wallpaperList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_wallpapers,parent,false);
        return  new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {

        Wallpaper c = WallpaperList.get(position);
        holder.textView.setText(c.getTitle());
        Glide.with(mCtx)
                .load(c.getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return WallpaperList.size();
    }

    class WallpaperViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        private WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_wall_title);
            imageView = itemView.findViewById(R.id.image_view_wall_image);
        }
    }

}
