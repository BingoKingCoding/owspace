package com.adou.ddlibrary.base;

import android.os.Bundle;

import com.adou.ddlibrary.event.EventManager;

import icepick.Icepick;
import nucleus5.presenter.RxPresenter;

/**
 * Created by wwb on 2017/5/27.
 */

public abstract class BasePresent<View> extends RxPresenter
{
    private boolean isLoadingDialog;

    public boolean isLoadingDialog()
    {
        return isLoadingDialog;
    }

    public void setLoadingDialog(boolean isLoadingDialog)
    {
        this.isLoadingDialog = isLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);
        if (isUseIcepick())
            Icepick.restoreInstanceState(this, savedState);
        if (isOpenEventBus())
            EventManager.register(this);
    }

    @Override
    protected void onSave(Bundle state)
    {
        super.onSave(state);
        if (isUseIcepick())
            Icepick.saveInstanceState(this, state);
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (isOpenEventBus())
            EventManager.unregister(this);
    }

    /**
     * @Description 需要注册是时候重写下，默认不开启
     */
    protected abstract boolean isOpenEventBus();

    /**
     * @Description 需要注册是时候重写下，默认不开启
     */
    protected abstract boolean isUseIcepick();


}
