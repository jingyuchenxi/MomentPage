package com.thtmo.app.recycler.viewholder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.thtmo.app.R;
import com.thtmo.app.vo.TweetsDto;

import org.apache.commons.lang3.ObjectUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.commentUser)
    TextView commentUser;

    @BindView(R.id.commentContent)
    TextView commentContent;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void refreshCommentView(TweetsDto.Comments comments) {
        if (ObjectUtils.allNotNull(comments)) {
            if (ObjectUtils.allNotNull(comments.getSender())) {
                commentUser.setText(comments.getSender().getNick() + ": ");
            }
            commentContent.setText(comments.getContent());
        }
    }
}
