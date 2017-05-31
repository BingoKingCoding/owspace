package com.wwb.dandu.di.components;

import com.wwb.dandu.di.modules.SplashModule;
import com.wwb.dandu.di.scopes.UserScope;
import com.wwb.dandu.view.activity.SplashActivity;

import dagger.Component;

/**
 * Created by wwb on 2017/1/19.
 */
@UserScope
@Component(modules = SplashModule.class,dependencies = NetComponent.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
