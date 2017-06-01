package com.adou.ddlibrary.ui.titlebar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2017/5/10 17:46
 */
public class BaseTitleBar
{
    private ViewGroup mRootTitleBar;
    private TitleBarBuild mTitleBarBuild;

    public void attach(Activity context, ViewGroup parentView, TitleBarBuild titleBarBuild) {
        this.mTitleBarBuild = titleBarBuild;
        if (titleBarBuild.iTitleBar == null) {
            return;
        }
        initTitleBar(context, parentView);
    }

    private void initTitleBar(Activity context, ViewGroup parentView) {
        mRootTitleBar = (ViewGroup) LayoutInflater.from(context).inflate(mTitleBarBuild.iTitleBar.getTitleLayout(), parentView, false);
        mTitleBarBuild.iTitleBar.initTitleView(context, mRootTitleBar, mTitleBarBuild);
    }

    public ViewGroup getTitleBarView() {
        return mRootTitleBar;
    }

    public void detach() {
        mTitleBarBuild.clear();
        mTitleBarBuild = null;
        mRootTitleBar = null;
    }
}
