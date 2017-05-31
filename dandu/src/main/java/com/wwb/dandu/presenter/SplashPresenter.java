package com.wwb.dandu.presenter;

import com.orhanobut.logger.Logger;
import com.wwb.dandu.app.App;
import com.wwb.dandu.model.api.ApiService;
import com.wwb.dandu.model.entity.SplashEntity;
import com.wwb.dandu.util.NetUtil;
import com.wwb.dandu.util.OkHttpImageDownloader;
import com.wwb.dandu.util.TimeUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by wwb on 2017/1/19.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View view;
    private ApiService apiService;

    @Inject//此处关键，用来提供Presenter 的实例化对象
    public SplashPresenter(SplashContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
        Logger.d("apppp:" + apiService);
    }

    @Override
    public void getSplash(String deviceId) {
        String client = "android";
        String version = "1.2.0";
        Long time = TimeUtil.getCurrentSeconds();
        apiService.getSplash(client, version, time, deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<SplashEntity>() {
                    @Override
                    public void onCompleted() {
                        Logger.e("load splash onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, "load splash failed:");
                    }

                    @Override
                    public void onNext(SplashEntity splashEntity) {
                        if (NetUtil.isWifi(App.getInstance().getApplicationContext())) {
                            if (splashEntity!=null){
                                List<String> images = splashEntity.getImages();
                                for (String url:images) {
                                    OkHttpImageDownloader.download(url);
                                }
                            }
                        } else {
                            Logger.i("不是WIFI环境,就不去下载图片了");
                        }
                    }
                });
    }
}
