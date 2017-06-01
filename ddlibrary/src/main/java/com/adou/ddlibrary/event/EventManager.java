package com.adou.ddlibrary.event;

import org.greenrobot.eventbus.EventBus;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/28 00 53.
 *
 * @Email:634051075@qq.com
 */

public class EventManager
{
    public static final int TAG_INT_EMPTY = -999;

    /**
     * 注册观察者
     *
     * @param subscriber
     */
    public static void register(Object subscriber)
    {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 取消观察者
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber)
    {
        EventBus.getDefault().unregister(subscriber);
    }


    /**
     * 发送事件
     *
     * @param event
     */
    public static void post(Object event)
    {
        EventBus.getDefault().post(event);
    }


    /**
     * 调用此方法发送事件，观察者必须实现SDEventObserver接口
     *
     * @param tagString
     */
    public static void post(String tagString)
    {
        post(null, tagString);
    }

    /**
     * 调用此方法发送事件，观察者必须实现SDEventObserver接口
     *
     * @param tagString
     */
    public static void post(Object data, String tagString)
    {
        post(data, tagString, TAG_INT_EMPTY);
    }

    public static void post(Object data, String tagString, int tagInt)
    {
        BaseEvent baseEvent = new BaseEvent.Builder().data(data).tagString(tagString).tagInt(tagInt).build();
        post(baseEvent);
    }


}
