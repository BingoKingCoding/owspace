package com.adou.ddlibrary.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.adou.ddlibrary.ui.widget.AutoConstraintLayout;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.config.AutoLayoutConifg;

import nucleus5.presenter.Presenter;

/**
 * <请描述这个类是干什么的：设置AutoLayout>
 * <p>
 * Created by adou on 2017/5/26 23 20.
 *
 * @Email:634051075@qq.com
 */

public class BaseAutoLayoutActivity<P extends Presenter> extends BaseNucleusAppCompatActivity<P>
{
    //0:未知 1:状态栏透明 2:状态栏不透明
    private static int IS_WINDOW_TRANSLUCENT_STATUS = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        initWindowTranslucentStatus(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        View view = null;
        if ("FrameLayout".equals(name))
        {
            view = new AutoFrameLayout(context, attrs);
        } else if ("LinearLayout".equals(name))
        {
            view = new AutoLinearLayout(context, attrs);
        } else if ("RelativeLayout".equals(name))
        {
            view = new AutoRelativeLayout(context, attrs);
        } else if ("android.support.constraint.ConstraintLayout".equals(name))
        {
            view = new AutoConstraintLayout(context, attrs);
        }
        return view != null ? view : super.onCreateView(name, context, attrs);
    }

    public void initWindowTranslucentStatus(Context context)
    {
        if (IS_WINDOW_TRANSLUCENT_STATUS != 0)
        {
            return;
        }
        BaseAutoLayoutActivity.IS_WINDOW_TRANSLUCENT_STATUS = getWindowTranslucentStatus(context) ? 1 : 2;
        if (BaseAutoLayoutActivity.IS_WINDOW_TRANSLUCENT_STATUS == 1)
        {
            AutoLayoutConifg.getInstance().useDeviceSize();
        }

    }

    /**
     * @date 2017/5/27
     * @author wwb
     * @Description 获取状态栏是否透明，即沉浸式状态栏
     */
    private boolean getWindowTranslucentStatus(Context context)
    {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowTranslucentStatus});
        boolean b = typedArray.getBoolean(0, false);
        typedArray.recycle();
        return b;
    }

    public boolean isWindowTranslucentStatus()
    {
        return BaseAutoLayoutActivity.IS_WINDOW_TRANSLUCENT_STATUS == 1;
    }

}
