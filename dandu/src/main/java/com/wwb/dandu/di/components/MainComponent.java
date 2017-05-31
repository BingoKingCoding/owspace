package com.wwb.dandu.di.components;

import com.wwb.dandu.di.modules.MainModule;
import com.wwb.dandu.di.scopes.UserScope;
import com.wwb.dandu.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by wwb on 2017/1/24.
 */
@UserScope
@Component(modules = MainModule.class,dependencies = NetComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
