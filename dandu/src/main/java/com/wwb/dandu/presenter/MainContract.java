package com.wwb.dandu.presenter;

import com.wwb.dandu.model.entity.Item;

import java.util.List;

/**
 * Created by wwb on 2017/1/24.
 */

public interface MainContract {
    interface Presenter{
        void getListByPage(int page, int model, String pageId,String deviceId,String createTime);
        void getRecommend(String deviceId);
    }
    interface View{
        void showLoading();
        void dismissLoading();
        void showNoData();
        void showNoMore();
        void updateListUI(List<Item> itemList);
        void showOnFailure();
        void showLunar(String content);
    }
}
