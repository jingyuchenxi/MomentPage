package com.thtmo.app.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thtmo.app.R;
import com.thtmo.app.recycler.viewholder.CommentViewHolder;
import com.thtmo.app.vo.TweetsDto;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<TweetsDto.Comments> commentsList = new ArrayList<>();

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.refreshCommentView(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public void updateCommentsList(List<TweetsDto.Comments> commentsList) {
        this.commentsList = commentsList;
        notifyDataSetChanged();
    }
}
