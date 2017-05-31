package com.wwb.dandu.di.modules;

import com.wwb.dandu.presenter.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wwb on 2017/1/19.
 */
@Module
public class SplashModule {
    private SplashContract.View view;

    public SplashModule(SplashContract.View view) {
        this.view = view;
    }

    @Provides
    public SplashContract.View ProvideView() {
        return view;
    }

}
