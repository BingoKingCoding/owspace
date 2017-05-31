package com.wwb.dandu.di.components;

import com.wwb.dandu.di.modules.DetailModule;
import com.wwb.dandu.di.scopes.UserScope;
import com.wwb.dandu.view.activity.AudioDetailActivity;
import com.wwb.dandu.view.activity.DetailActivity;
import com.wwb.dandu.view.activity.VideoDetailActivity;

import dagger.Component;

/**
 * Created by wwb on 2017/1/30.
 */
@UserScope
@Component(modules = DetailModule.class,dependencies = NetComponent.class)
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
    void inject(AudioDetailActivity audioDetailActivity);
    void inject(VideoDetailActivity videoDetailActivity);
}
