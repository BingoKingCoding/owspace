package com.adou.ddlibrary.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adou.ddlibrary.Library;
import com.adou.ddlibrary.event.EventManager;
import com.adou.ddlibrary.ui.dialog.LoadingDialogListener;
import com.adou.ddlibrary.ui.dialog.ProgressDialog;
import com.adou.ddlibrary.ui.titlebar.BaseTitleBar;
import com.adou.ddlibrary.ui.titlebar.StatusBarCompat;
import com.adou.ddlibrary.ui.titlebar.StatusBarValue;
import com.adou.ddlibrary.ui.titlebar.TitleBarBuild;
import com.adou.ddlibrary.utils.AndroidBug5497Workaround;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

/**
 * Created by wwb on 2017/5/27.
 */

public abstract class BaseActivity<P extends BasePresent> extends BaseAutoLayoutActivity<P> implements LoadingDialogListener
{
    protected Unbinder mUnbinder;
    private TitleBarBuild titleBarBuild;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (isUseIcepick())
            Icepick.restoreInstanceState(this, savedInstanceState);
        if (isOpenEventBus())
            EventManager.register(this);
        mUnbinder = ButterKnife.bind(this);

        ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);
        View activityContentView = getActivityContentView(rootView, LayoutInflater.from(this));


        StatusBarValue statusBarValue = new StatusBarValue();
        initStatusBar(statusBarValue);


        titleBarBuild = new TitleBarBuild(getApplicationContext());
        initTitleBar(titleBarBuild);

        BaseTitleBar baseTitleBar = new BaseTitleBar();
        baseTitleBar.attach(this, rootView, titleBarBuild);

// 后续需要用Merge标签优化布局层级
        setContentView(StatusBarCompat.initStatusBarView(this, activityContentView, baseTitleBar.getTitleBarView(), statusBarValue, titleBarBuild.getBackgroundColor()));

        if (isWindowTranslucentStatus() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            //解决沉浸式状态栏下，android:windowSoftInputMode="adjustResize" 这个属性失效的问题
            AndroidBug5497Workaround.assistActivity(this);
        }

        initData(savedInstanceState);

        if (getPresenter() != null && getPresenter().isLoadingDialog())
        {
            showLoadingDialog();
        }
    }


    @Override
    public void showLoadingDialog()
    {
        if (mProgressDialog == null)
        {
            mProgressDialog = ProgressDialog.show(this);
        }
        if (!mProgressDialog.isShowing())
        {
            mProgressDialog.show();
        }
        setLoadingState(true);
    }

    @Override
    public void closeLoadingDialog()
    {
        if (mProgressDialog != null)
        {
            mProgressDialog.dismiss();
        }
        setLoadingState(false);
    }

    private void setLoadingState(boolean isLoading)
    {
        if (getPresenter() != null)
        {
            getPresenter().setLoadingDialog(isLoading);
        }
    }

    @CallSuper
    public void initStatusBar(StatusBarValue statusBar)
    {
        statusBar.setLayoutMode(StatusBarValue.LayoutMode.BELOW_TITLE_BAR);
        statusBar.setStatusBarColor(Library.getInstance().getLibraryConfig().getMainColor());
    }


    protected View getActivityContentView(ViewGroup parentView, LayoutInflater inflater)
    {
        return inflater.inflate(getContentViewId(), parentView, false);
    }


    protected void initTitleBar(TitleBarBuild titleBarBuild)
    {
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (isUseIcepick())
            Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (isOpenEventBus())
            EventManager.unregister(this);
        mUnbinder.unbind();
        if (titleBarBuild != null)
        {
            titleBarBuild.clear();
            titleBarBuild = null;
        }
        if (mProgressDialog != null)
        {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    /**
     * @Description 需要注册是时候重写下，默认不开启
     */
    protected abstract boolean isOpenEventBus();

    /**
     * @Description 需要注册是时候重写下，默认不开启
     */
    protected abstract boolean isUseIcepick();

    /**
     * @Description 返回layout
     */
    protected abstract int getContentViewId();

    /**
     * @Description 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

}
