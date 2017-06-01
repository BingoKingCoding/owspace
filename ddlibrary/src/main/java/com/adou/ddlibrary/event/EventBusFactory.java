package com.adou.ddlibrary.event;


import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/28 00 40.
 *
 * @Email:634051075@qq.com
 */

public class EventBusFactory
{
    private static Map<String, EventBus> mMapEventBus = new HashMap<String, EventBus>();

    public static EventBus get(String key)
    {
        EventBus eventBus = null;
        if (!TextUtils.isEmpty(key))
        {
            eventBus = mMapEventBus.get(key);
            if (eventBus == null)
            {
                eventBus = new EventBus();
                mMapEventBus.put(key, eventBus);
            }
        }
        return eventBus;
    }

    public static EventBus remove(String key)
    {
        return mMapEventBus.remove(key);
    }

    public static void clear()
    {
        mMapEventBus.clear();
    }

}
