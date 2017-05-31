package com.wwb.dandu.di.components;

import com.wwb.dandu.di.modules.ArtModule;
import com.wwb.dandu.di.scopes.UserScope;
import com.wwb.dandu.view.activity.ArtActivity;

import dagger.Component;

@UserScope
@Component(modules = ArtModule.class,dependencies = NetComponent.class)
public interface ArtComponent {
    void inject(ArtActivity artActivity);
}
