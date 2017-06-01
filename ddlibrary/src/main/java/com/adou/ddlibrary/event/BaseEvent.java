package com.adou.ddlibrary.event;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/28 00 39.
 *
 * @Email:634051075@qq.com
 */

public class BaseEvent
{
    private int tagInt = EventManager.TAG_INT_EMPTY;
    private Object data;
    private String tagString;


    private BaseEvent(Builder builder)
    {
        this.tagInt = builder.tagInt;
        this.data = builder.data;
        this.tagString = builder.tagString;
    }


    public static class Builder
    {
        private int tagInt;
        private Object data;
        private String tagString;


        public Builder tagInt(int tagInt)
        {
            this.tagInt = tagInt;
            return this;
        }

        public Builder data(Object data)
        {
            this.data = data;
            return this;
        }

        public Builder tagString(String tagString)
        {
            this.tagString = tagString;
            return this;
        }

        public BaseEvent build()
        {
            return new BaseEvent(this);
        }


    }


    public int getTagInt()
    {
        return tagInt;
    }

    public void setTagInt(int tagInt)
    {
        this.tagInt = tagInt;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getTagString()
    {
        return tagString;
    }

    public void setTagString(String tagString)
    {
        this.tagString = tagString;
    }
}
