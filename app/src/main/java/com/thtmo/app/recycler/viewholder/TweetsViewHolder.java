package com.thtmo.app.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thtmo.app.R;
import com.thtmo.app.vo.TweetsDto;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tweetsIcon)
    SimpleDraweeView tweetsIcon;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.contentImg)
    SimpleDraweeView contentImg;

    @BindView(R.id.comment)
    TextView comment;

    public TweetsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refreshTweetsView(TweetsDto tweetsDto) {
        if (ObjectUtils.allNotNull(tweetsDto)) {
            if (ObjectUtils.allNotNull(tweetsDto.getSender())) {
                tweetsIcon.setImageURI(tweetsDto.getSender().getAvatar());
            }
            if (ObjectUtils.allNotNull(tweetsDto.getContent())) {
                content.setText(tweetsDto.getContent());
            }
            if (ObjectUtils.isNotEmpty(tweetsDto.getImages())) {
                contentImg.setImageURI(StringUtils.defaultIfBlank(tweetsDto.getImages().get(0), ""));
            }
            if (ObjectUtils.isNotEmpty(tweetsDto.getComments())) {
                comment.setText(tweetsDto.getComments().get(0).toString());
            }
        }
    }
}
