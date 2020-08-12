package com.thtmo.app.ui;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thtmo.app.R;
import com.thtmo.app.recycler.MomentViewAdapter;
import com.thtmo.app.viewmodel.AvatarViewModel;
import com.thtmo.app.viewmodel.TweetsViewModel;
import com.thtmo.app.vo.AvatarDto;
import com.thtmo.app.vo.TweetsDto;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private AvatarViewModel avatarViewModel;

    private TweetsViewModel tweetsViewModel;

    private MomentViewAdapter momentViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        momentViewAdapter = new MomentViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(momentViewAdapter);
        avatarViewModel = new ViewModelProvider(this).get(AvatarViewModel.class);
        tweetsViewModel = new ViewModelProvider(this).get(TweetsViewModel.class);
        avatarViewModel.observeAvatarData(this, (Observer<AvatarDto>) this::refreshAvatarView);
        tweetsViewModel.observeTweetsData(this, (Observer<List<TweetsDto>>) this::refreshTweetsView);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                avatarViewModel.obtainAvatarDto();
                tweetsViewModel.obtainTweetsDtoList();
                refreshLayout.finishRefresh(true);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                tweetsViewModel.obtainTweetsDtoList();
                refreshLayout.finishLoadMore(true);
            }
        });
        avatarViewModel.obtainAvatarDto();
        tweetsViewModel.obtainTweetsDtoList();
    }

    private void refreshAvatarView(AvatarDto avatarDto) {
        momentViewAdapter.updateAvatarDto(avatarDto);
    }

    private void refreshTweetsView(List<TweetsDto> tweetsDtoList) {
        momentViewAdapter.updateTweetsDtoList(tweetsDtoList);
    }
}
