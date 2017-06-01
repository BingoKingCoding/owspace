package com.adou.ddlibrary;

import android.app.Application;

import com.adou.ddlibrary.config.LibraryConfig;

/**
 * Created by wwb on 2017/5/27.
 */

public class Library
{
    private static Library instance;
    private Application mApplication;


    private LibraryConfig mLibraryConfig;

    private Library()
    {
        mLibraryConfig = new LibraryConfig();
    }

    public void init(Application mApplication)
    {
        this.mApplication = mApplication;
        initDefaultConfig();
    }


    public LibraryConfig getLibraryConfig()
    {
        return mLibraryConfig;
    }

    public void setLibraryConfig(LibraryConfig libraryConfig)
    {
        mLibraryConfig = libraryConfig;
    }

    private void initDefaultConfig()
    {
        int main_color = mApplication.getResources().getColor(R.color.main_color);
        int main_color_press = mApplication.getResources().getColor(R.color.main_color_press);

        int bg_title_bar = mApplication.getResources().getColor(R.color.bg_title_bar);
        int bg_title_bar_pressed = mApplication.getResources().getColor(R.color.bg_title_bar_pressed);

        int stroke = mApplication.getResources().getColor(R.color.stroke);
        int gray_press = mApplication.getResources().getColor(R.color.gray_press);

        int text_title_bar = mApplication.getResources().getColor(R.color.text_title_bar);

        int height_title_bar = mApplication.getResources().getDimensionPixelOffset(R.dimen.height_title_bar);
        int corner = mApplication.getResources().getDimensionPixelOffset(R.dimen.corner);
        int width_stroke = mApplication.getResources().getDimensionPixelOffset(R.dimen.width_stroke);

        mLibraryConfig.setMainColor(main_color);
        mLibraryConfig.setMainColorPress(main_color_press);
        mLibraryConfig.setGrayPressColor(gray_press);
        mLibraryConfig.setTitleColor(bg_title_bar);
        mLibraryConfig.setTitleColorPressed(bg_title_bar_pressed);
        mLibraryConfig.setTitleTextColor(text_title_bar);
        mLibraryConfig.setTitleHeight(height_title_bar);
        mLibraryConfig.setStrokeColor(stroke);
        mLibraryConfig.setStrokeWidth(width_stroke);
        mLibraryConfig.setCornerRadius(corner);
    }


    public static Library getInstance()
    {
        if (instance == null)
        {
            instance = new Library();
        }
        return instance;
    }

    public Application getApplication()
    {
        return mApplication;
    }
}
