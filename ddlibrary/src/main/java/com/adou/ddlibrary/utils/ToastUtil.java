package com.adou.ddlibrary.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.adou.ddlibrary.Library;


/**
 * toast显示类，可以在子线程直接调用
 */
public class ToastUtil
{
    private static Toast toast;

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(String text)
    {
        showToast(text, Toast.LENGTH_LONG);
    }

    public static void showToast(final String text, final int duration)
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            show(text, duration);
        } else
        {
//            mHandler.post(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    show(text, duration);
//                }
//            });

            mHandler.post(() -> show(text, duration));

        }
    }

    private static void show(String text, int duration)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }
        if (toast != null)
        {
            toast.cancel();
        }
        toast = Toast.makeText(Library.getInstance().getApplication(), text, duration);
        toast.show();
    }

}
