package com.thtmo.app.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.thtmo.app.R;
import com.thtmo.app.vo.TweetsDto;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetItemViewAdapter extends RecyclerView.Adapter<TweetItemViewAdapter.ViewHolder> {

    private List<TweetsDto> tweetsDtoList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tweet_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TweetsDto tweetsDto = tweetsDtoList.get(position);
        holder.textView.setText(StringUtils.defaultIfBlank(tweetsDto.getContent(),""));
        holder.tweetsIcon.setImageURI("");
    }

    @Override
    public int getItemCount() {
        return tweetsDtoList.size();
    }

    public void updateTweetsDtoList(List<TweetsDto> tweetsDtoList) {
        this.tweetsDtoList = tweetsDtoList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView textView;

        @BindView(R.id.tweetsIcon)
        SimpleDraweeView tweetsIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
