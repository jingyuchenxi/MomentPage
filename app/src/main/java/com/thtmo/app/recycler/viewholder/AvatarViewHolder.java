package com.thtmo.app.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thtmo.app.R;
import com.thtmo.app.vo.AvatarDto;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AvatarViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.profileImage)
    SimpleDraweeView profileImage;

    @BindView(R.id.avatarDesc)
    TextView avatarDesc;

    @BindView(R.id.avatarIcon)
    SimpleDraweeView avatarIcon;

    public AvatarViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refreshAvatarView(AvatarDto avatarDto) {
        profileImage.setImageURI(avatarDto.getProfileImage());
        avatarIcon.setImageURI(avatarDto.getAvatar());
        avatarDesc.setText(avatarDto.getNick());
        avatarDesc.bringToFront();
    }
}