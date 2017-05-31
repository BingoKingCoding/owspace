package com.wwb.dandu.di.components;

import com.wwb.dandu.di.modules.NetModule;
import com.wwb.dandu.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by wwb on 2017/1/19.
 */
@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();
    OkHttpClient getOkHttpClient();
    Retrofit getRetrofit();
}
