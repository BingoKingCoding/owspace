package com.adou.ddlibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import nucleus5.factory.PresenterFactory;
import nucleus5.factory.ReflectionPresenterFactory;
import nucleus5.presenter.Presenter;
import nucleus5.view.PresenterLifecycleDelegate;
import nucleus5.view.ViewWithPresenter;

/**
 * <请描述这个类是干什么的>
 *
 * Created by adou on 2017/5/26.
 *
 * @Email: 634051075@qq.com
 */

public class BaseNucleusAppCompatActivity<P extends Presenter> extends RxAppCompatActivity implements ViewWithPresenter<P>
{

    private static final String PRESENTER_STATE_KEY = "presenter_state";

    private PresenterLifecycleDelegate<P> presenterDelegate =
            new PresenterLifecycleDelegate<>(ReflectionPresenterFactory.<P>fromViewClass(getClass()));


    /**
     * Returns a current presenter factory.
     */
    @Override
    public PresenterFactory getPresenterFactory()
    {
        return presenterDelegate.getPresenterFactory();
    }


    /**
     * Sets a presenter factory.
     * Call this method before onCreate/onFinishInflate to override default {@link ReflectionPresenterFactory} presenter factory.
     * Use this method for presenter dependency injection.
     */
    @Override
    public void setPresenterFactory(PresenterFactory presenterFactory)
    {
        presenterDelegate.setPresenterFactory(presenterFactory);
    }

    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onResume/onPause and onAttachedToWindow/onDetachedFromWindow calls
     * if the presenter factory returns a non-null value.
     *
     * @return a currently attached presenter or null.
     */
    @Override
    public P getPresenter()
    {
        return presenterDelegate.getPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState());
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        presenterDelegate.onResume(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        presenterDelegate.onDropView();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenterDelegate.onDestroy(!isChangingConfigurations());
    }
}
