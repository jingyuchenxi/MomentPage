package com.thtmo.app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.thtmo.app.R;
import com.thtmo.app.network.config.NetConfig;
import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.network.service.ProfileService;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    private Disposable disposable;

    @BindView(R.id.avatarBgImg)
    ImageView avatarBgImg;

    @BindView(R.id.avatarIcon)
    SimpleDraweeView avatarIcon;

    @BindView(R.id.avatarDesc)
    TextView avatarDesc;

    @BindView(R.id.testBtn)
    Button button;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.testBtn)
    public void onAvatarIconClick(View v) {
        ProfileService profileService = NetConfig.getServiceInstance(ResConfig.BASE_URL, GsonConverterFactory.create(), ProfileService.class);
        disposable = profileService.getProfile().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avatarDto -> avatarIcon.setImageURI(avatarDto.getAvatar()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        unbinder.unbind();
    }
}
