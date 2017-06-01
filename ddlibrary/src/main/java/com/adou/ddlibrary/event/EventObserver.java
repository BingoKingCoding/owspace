package com.adou.ddlibrary.event;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/28 00 52.
 *
 * @Email:634051075@qq.com
 */

public interface EventObserver
{
    void onEvent(BaseEvent event);

    void onEventMainThread(BaseEvent event);

    void onEventBackgroundThread(BaseEvent event);

    void onEventAsync(BaseEvent event);
}
