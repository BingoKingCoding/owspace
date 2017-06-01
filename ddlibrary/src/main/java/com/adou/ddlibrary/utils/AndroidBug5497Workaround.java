package com.adou.ddlibrary.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.utils.ScreenUtils;

/**
 * 解决沉浸式状态栏下，android:windowSoftInputMode="adjustResize" 这个属性失效的问题
 * 参考技术文档: http://www.diycode.cc/topics/383
 */
public class AndroidBug5497Workaround
{

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity (Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

    private int statusBarHeight = -1;
    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;

    private AndroidBug5497Workaround(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        statusBarHeight = ScreenUtils.getStatusBarHeight(activity);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        int screenHeight = AutoLayoutConifg.getInstance().getScreenHeight();
        if (usableHeightNow != usableHeightPrevious) {
            if (usableHeightNow + statusBarHeight != screenHeight) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightNow + statusBarHeight;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = screenHeight;
            }

            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    // 需要在4.4以下手机上测试下 & 三星SM-N9009设备上有点问题
    private int computeUsableHeight() {//扣除状态和操作栏的剩余可见高度
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        int h = r.bottom - r.top;
        if(h == AutoLayoutConifg.getInstance().getScreenHeight()) {
            h -= statusBarHeight;
        }
        return h;
    }
}