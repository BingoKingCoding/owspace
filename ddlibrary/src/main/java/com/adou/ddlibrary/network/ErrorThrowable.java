package com.adou.ddlibrary.network;

import com.adou.ddlibrary.model.Response;

/**
 * <请描述这个类是干什么的>
 * Created by adou on 2017/5/31 21 36.
 *
 * @Email:634051075@qq.com
 */

public class ErrorThrowable extends Throwable
{
    public int code;

        public String msg;

        public ErrorThrowable(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public ErrorThrowable(Response response) {
            this.code = response.code;
            this.msg = response.msg;
        }

        public boolean isEmpty() {
            return code == ReturnCode.CODE_EMPTY;
        }
}
