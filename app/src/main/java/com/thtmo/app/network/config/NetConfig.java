package com.thtmo.app.network.config;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class NetConfig {
    private static OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(5000L, TimeUnit.MILLISECONDS)
            .readTimeout(5000L, TimeUnit.MILLISECONDS)
            .connectionPool(new ConnectionPool(10, 5L, TimeUnit.MINUTES))
            .build();

    private static Retrofit getRetrofit(String baseUrl, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OK_HTTP_CLIENT)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static <T> T getServiceInstance(String baseUrl, Converter.Factory converterFactory, Class<T> clazz) {
        return getRetrofit(baseUrl, converterFactory).create(clazz);
    }
}
