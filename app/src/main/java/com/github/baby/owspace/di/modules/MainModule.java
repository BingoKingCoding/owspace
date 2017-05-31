package com.github.baby.owspace.di.modules;

import com.github.baby.owspace.presenter.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
//因为Activity的类的构造器，我们无法加入@Inject注解，所以必须提供Module才能提供View接口的实例化对象。
public class MainModule {
    private final MainContract.View mView;

    //构造方法传递View 接口的实例化对象
    public MainModule(MainContract.View mView) {
        this.mView = mView;
    }
    //在DI容器中提供View接口的实例化对象
    @Provides
    public MainContract.View provideMainView() {
        return mView;
    }
}
