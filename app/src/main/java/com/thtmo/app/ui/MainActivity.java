package com.thtmo.app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.thtmo.app.R;
import com.thtmo.app.network.config.NetConfig;
import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.network.service.MomentPageDataService;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.profileImage)
    SimpleDraweeView profileImage;

    @BindView(R.id.avatarIcon)
    SimpleDraweeView avatarIcon;

    @BindView(R.id.avatarDesc)
    TextView avatarDesc;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.testBtn)
    Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.testBtn)
    public void onTestBtnClick() {
        MomentPageDataService momentPageDataService = NetConfig.getServiceInstance(ResConfig.BASE_URL, GsonConverterFactory.create(), MomentPageDataService.class);
        momentPageDataService.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avatarDto -> {
                    profileImage.setImageURI(avatarDto.getProfileImage());
                    avatarIcon.setImageURI(avatarDto.getAvatar());
                    avatarDesc.setText(avatarDto.getNick());
                });

        momentPageDataService.getTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tweetsDtoList -> tweetsDtoList.forEach(tweetsDto -> {
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(tweetsDto.toString());
                    textView.setWidth(100);
                    textView.setHeight(100);
                    smartRefreshLayout.addView(textView);
                }));
    }
}
