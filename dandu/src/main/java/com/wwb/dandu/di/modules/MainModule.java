package com.wwb.dandu.di.modules;

import com.wwb.dandu.presenter.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wwb on 2017/1/24.
 */
@Module
public class MainModule {
    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View ProvideView() {
        return view;
    }
}
