package com.thtmo.app.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thtmo.app.R;
import com.thtmo.app.recycler.viewholder.ContentImgViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContentImageViewAdapter extends RecyclerView.Adapter<ContentImgViewHolder> {

    private List<String> contentImages = new ArrayList<>();

    @NonNull
    @Override
    public ContentImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContentImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContentImgViewHolder holder, int position) {
        holder.refreshImageView(contentImages.get(position));
    }

    @Override
    public int getItemCount() {
        return contentImages.size();
    }

    public void updateContentImage(List<String> contentImages) {
        this.contentImages = contentImages;
        notifyDataSetChanged();
    }
}
