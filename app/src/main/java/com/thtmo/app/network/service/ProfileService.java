package com.thtmo.app.network.service;

import com.thtmo.app.network.config.ResConfig;
import com.thtmo.app.vo.AvatarDto;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ProfileService {

    @GET(ResConfig.PROFILE_JSON_URL)
    Observable<AvatarDto> getProfile();
}
