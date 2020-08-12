package com.thtmo.app.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thtmo.app.R;
import com.thtmo.app.recycler.CommentViewAdapter;
import com.thtmo.app.recycler.ContentImageViewAdapter;
import com.thtmo.app.vo.TweetsDto;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tweetsIcon)
    SimpleDraweeView tweetsIcon;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.contentImages)
    RecyclerView contentImages;

    @BindView(R.id.commentView)
    RecyclerView commentView;

    private ContentImageViewAdapter contentImageAdapter;

    private CommentViewAdapter commentViewAdapter;

    public TweetsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        init(itemView);
    }

    public void refreshTweetsView(TweetsDto tweetsDto) {
        if (ObjectUtils.allNotNull(tweetsDto)) {
            if (ObjectUtils.allNotNull(tweetsDto.getSender())) {
                tweetsIcon.setImageURI(tweetsDto.getSender().getAvatar());
            }
            if (StringUtils.isNotEmpty(tweetsDto.getContent())) {
                content.setText(tweetsDto.getContent());
            } else {
                content.setVisibility(View.GONE);
            }
            if (ObjectUtils.isNotEmpty(tweetsDto.getImages())) {
                contentImageAdapter.updateContentImage(tweetsDto.getImages());
            }
            if (ObjectUtils.isNotEmpty(tweetsDto.getComments())) {
                commentViewAdapter.updateCommentsList(tweetsDto.getComments());
            }
        }
    }

    private void init(View view) {
        contentImageAdapter = new ContentImageViewAdapter();
        contentImages.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        contentImages.setAdapter(contentImageAdapter);

        commentViewAdapter = new CommentViewAdapter();
        commentView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        commentView.setAdapter(commentViewAdapter);
    }
}
