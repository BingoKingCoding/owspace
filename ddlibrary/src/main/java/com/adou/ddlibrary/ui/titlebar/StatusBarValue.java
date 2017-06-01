package com.adou.ddlibrary.ui.titlebar;

import android.support.annotation.ColorRes;

/**
 * Created by wwb on 2017/5/27.
 */

public class StatusBarValue
{
    //状态栏颜色
    private int statusBarColor;
    //状态栏布局模式
    private LayoutMode layoutMode = LayoutMode.BELOW_TITLE_BAR;

    public enum LayoutMode
    {
        //全屏
        FULLSCREEN,
        //内容布局在时间状态栏下
        BELOW_STATE_BAR,
        //内容布局在标题栏下
        BELOW_TITLE_BAR,
        //无STATUSBAR
        NULL
    }

    public void setLayoutMode(LayoutMode layoutMode) {
        this.layoutMode = layoutMode;
    }

    public void setStatusBarColor(@ColorRes int color) {
        this.statusBarColor = color;
    }

    public LayoutMode getLayoutMode() {
        return layoutMode;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }


}
