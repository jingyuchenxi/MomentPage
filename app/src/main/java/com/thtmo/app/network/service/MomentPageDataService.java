package com.thtmo.app.network.service;

import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.vo.AvatarDto;
import com.thtmo.app.vo.TweetsDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MomentPageDataService {

    @GET(ResConfig.PROFILE_JSON_URL)
    Observable<AvatarDto> getProfile();

    @GET(ResConfig.TWEETS_JSON_URL)
    Observable<List<TweetsDto>> getTweets();
}
