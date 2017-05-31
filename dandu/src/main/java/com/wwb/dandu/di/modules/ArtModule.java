package com.wwb.dandu.di.modules;


import com.wwb.dandu.presenter.ArticalContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtModule {
    private ArticalContract.View mView;

    public ArtModule(ArticalContract.View mView) {
        this.mView = mView;
    }
    @Provides
    public ArticalContract.View provideView(){
        return mView;
    }
}
