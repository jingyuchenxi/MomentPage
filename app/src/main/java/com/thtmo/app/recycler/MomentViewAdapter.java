package com.thtmo.app.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.thtmo.app.R;
import com.thtmo.app.recycler.viewholder.AvatarViewHolder;
import com.thtmo.app.recycler.viewholder.TweetsViewHolder;
import com.thtmo.app.vo.AvatarDto;
import com.thtmo.app.vo.TweetsDto;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.thtmo.app.util.Constant.ITEM_TYPE_ONE;
import static com.thtmo.app.util.Constant.ITEM_TYPE_TWO;
import static com.thtmo.app.util.Constant.RECYCLER_VIEW_HEADER_POSITION;

public class MomentViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private AvatarDto avatarDto = new AvatarDto();
    private List<TweetsDto> tweetsDtoList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (ITEM_TYPE_ONE == viewType) {
            return new AvatarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_avatar, parent, false));
        } else {
            return new TweetsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tweets, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AvatarViewHolder) {
            AvatarViewHolder avatarViewHolder = (AvatarViewHolder) holder;
            avatarViewHolder.refreshAvatarView(avatarDto);
        } else {
            TweetsViewHolder tweetsViewHolder = (TweetsViewHolder) holder;
            tweetsViewHolder.refreshTweetsView(tweetsDtoList.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return 1 + tweetsDtoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == RECYCLER_VIEW_HEADER_POSITION) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_TWO;
        }
    }

    public void updateAvatarDto(AvatarDto avatarDto) {
        this.avatarDto = avatarDto;
        notifyDataSetChanged();
    }

    public void updateTweetsDtoList(List<TweetsDto> tweetsDtoList) {
        this.tweetsDtoList = tweetsDtoList;
        notifyDataSetChanged();
    }
}
