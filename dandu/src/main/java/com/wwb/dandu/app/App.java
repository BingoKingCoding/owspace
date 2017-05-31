package com.wwb.dandu.app;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.wwb.dandu.BuildConfig;
import com.wwb.dandu.R;
import com.wwb.dandu.di.components.DaggerNetComponent;
import com.wwb.dandu.di.components.NetComponent;
import com.wwb.dandu.di.modules.NetModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by wwb on 2017/1/18.
 */

public class App extends Application {

    private static App instance;
    private NetComponent netComponent;
    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLogger();//初始化log
        initNet();//初始化提供网络api
        initTypeFace();//初始化自定义字体
        initDatabase();
    }

    private void initNet() {
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    private void initTypeFace() {
        CalligraphyConfig calligraphyConfig =new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PMingLiU.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    private void initLogger() {
        LogLevel logLevel;
        if (!BuildConfig.API_ENV){//允许log
            logLevel = LogLevel.FULL;
        }else {
            logLevel = LogLevel.NONE;
        }
        //        Logger.init(YOUR_TAG);
        Logger.init("wwb")         // default PRETTYLOGGER or use just init()
                .methodCount(3)             // default 2
                .logLevel(logLevel) ;       // default LogLevel.FULL
    }
    private void initDatabase(){

    }
    public NetComponent getNetComponent() {
        return netComponent;
    }
    public static App getInstance() {
        return instance;
    }
}
