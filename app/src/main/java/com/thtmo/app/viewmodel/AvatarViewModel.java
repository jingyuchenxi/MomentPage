package com.thtmo.app.viewmodel;

import android.annotation.SuppressLint;

import com.thtmo.app.network.config.NetConfig;
import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.network.service.AvatarService;
import com.thtmo.app.vo.AvatarDto;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

public class AvatarViewModel extends ViewModel {
    private static final AvatarService avatarService = NetConfig.getServiceInstance(ResConfig.BASE_URL, GsonConverterFactory.create(), AvatarService.class);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<AvatarDto> avatarData = new MutableLiveData<>();

    public void observeAvatarData(LifecycleOwner lifecycleOwner, Observer observer) {
        avatarData.observe(lifecycleOwner, observer);
    }

    @SuppressLint("CheckResult")
    public void obtainAvatarDto() {
        compositeDisposable.add(avatarService.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avatarDto -> avatarData.setValue(avatarDto)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
