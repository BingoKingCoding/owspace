package com.wwb.dandu.di.modules;

import com.wwb.dandu.presenter.DetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wwb on 2017/1/30.
 */
@Module
public class DetailModule {
    private DetailContract.View view;

    public DetailModule(DetailContract.View view) {
        this.view = view;
    }

    @Provides
    public DetailContract.View ProvideView() {
        return view;
    }
}
