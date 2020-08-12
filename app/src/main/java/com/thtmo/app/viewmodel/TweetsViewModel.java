package com.thtmo.app.viewmodel;

import android.annotation.SuppressLint;

import com.thtmo.app.network.config.NetConfig;
import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.network.service.TweetsService;
import com.thtmo.app.vo.TweetsDto;

import java.util.List;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.converter.gson.GsonConverterFactory;

public class TweetsViewModel extends ViewModel {
    private static final TweetsService tweetsService = NetConfig.getServiceInstance(ResConfig.BASE_URL, GsonConverterFactory.create(), TweetsService.class);
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<List<TweetsDto>> tweetsDataList = new MutableLiveData<>();

    public void observeTweetsData(LifecycleOwner owner, Observer observer) {
        tweetsDataList.observe(owner, observer);
    }

    @SuppressLint("CheckResult")
    public void obtainTweetsDtoList() {
        compositeDisposable.add(tweetsService.getTweets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tweetsDtoList -> tweetsDataList.setValue(tweetsDtoList)));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
