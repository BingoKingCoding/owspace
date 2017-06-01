package com.adou.ddlibrary.model;

import com.adou.ddlibrary.network.ReturnCode;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/31 21 38.
 *
 * @Email:634051075@qq.com
 */

public class Response<T>
{
    @SerializedName("code")
        public int code;

        @SerializedName("msg")
        public String msg;

        @SerializedName("data")
        public T data;

        public Response(int code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public boolean isSuccess() {
            return code == ReturnCode.CODE_SUCCESS;
        }

        public boolean isListEmpty() {
            return data == null || (data instanceof List && ((List) data).isEmpty());
        }

        public boolean isCode(int code) {
            return this.code == code;
        }
}
