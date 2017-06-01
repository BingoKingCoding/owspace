package com.adou.ddlibrary.ui.titlebar;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

public class TitleBarBuild
{

    private Context context;

    protected ITitleBar iTitleBar;

    protected String title = "";

    protected int leftImageRes;
    protected int rightImageRes;

    protected String rightString = "";
    protected int rightTextColor;

    protected int leftVisible;
    protected int rightVisible;
    protected int centerVisible;

    protected View.OnClickListener onCenterClickListener;
    protected View.OnClickListener onLeftClickListener;
    protected View.OnClickListener onRightClickListener;

    protected int backgroundColor;

    public TitleBarBuild(Context context) {
        this.context = context;
    }

    public TitleBarBuild config(ITitleBar iTitleBar) {
        this.iTitleBar = iTitleBar;
        return this;
    }

    public TitleBarBuild setTitle(String title) {
        this.title = title;
        return this;
    }

    public TitleBarBuild setTitle(@StringRes int titleRes) {
        return setTitle(context.getString(titleRes));
    }

    public TitleBarBuild setRightText(String rightString) {
        this.rightString = rightString;
        return this;
    }

    public TitleBarBuild setRightTextColor(@ColorRes int color) {
        this.rightTextColor = color;
        return this;
    }

    public TitleBarBuild setRightText(@StringRes int rightStringRes) {
        return setRightText(context.getString(rightStringRes));
    }

    public TitleBarBuild setRightImage(@DrawableRes int rightImageRes) {
        this.rightImageRes = rightImageRes;
        return this;
    }

    public TitleBarBuild setLeftImage(@DrawableRes int leftImage) {
        this.leftImageRes = leftImage;
        return this;
    }

    public TitleBarBuild setLeftVisible(boolean visible) {
        this.leftVisible = visible ? View.VISIBLE : View.INVISIBLE;
        return this;
    }

    public TitleBarBuild setRightVisible(boolean visible) {
        this.rightVisible = visible ? View.VISIBLE : View.INVISIBLE;
        return this;
    }

    public TitleBarBuild setCenterVisible(boolean visible) {
        this.centerVisible = visible ? View.VISIBLE : View.INVISIBLE;
        return this;
    }

    public TitleBarBuild setOnLeftClickListener(View.OnClickListener onClickListener) {
        this.onLeftClickListener = onClickListener;
        return this;
    }

    public TitleBarBuild setOnRightClickListener(View.OnClickListener onClickListener) {
        this.onRightClickListener = onClickListener;
        return this;
    }

    public TitleBarBuild setOnCenterClickListener(View.OnClickListener onClickListener) {
        this.onCenterClickListener = onClickListener;
        return this;
    }

    public TitleBarBuild setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isShowTitle() {
        return iTitleBar != null;
    }

    public void clear() {
        iTitleBar = null;
        onCenterClickListener = null;
        onLeftClickListener = null;
        onRightClickListener = null;
    }
}
