package com.adou.ddlibrary.ui.titlebar;

import android.app.Activity;
import android.view.ViewGroup;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2017/5/10 17:19
 */
public interface ITitleBar
{
    int getTitleLayout();

    void initTitleView(Activity activity, ViewGroup view, TitleBarBuild titleBarBuild);
}
