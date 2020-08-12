package com.thtmo.app.recycler.viewholder;

import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thtmo.app.R;

import org.apache.commons.lang3.StringUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentImgViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.contentImage)
    SimpleDraweeView contentImage;

    public ContentImgViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refreshImageView(String imageUrl) {
        if (StringUtils.isNotEmpty(imageUrl)) {
            contentImage.setImageURI(imageUrl);
        } else {
            contentImage.setVisibility(View.GONE);
        }
    }
}
