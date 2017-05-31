package com.wwb.dandu.presenter;


import com.wwb.dandu.model.entity.Item;

import java.util.List;

public interface ArticalContract {
    interface Presenter{
        void getListByPage(int page, int model, String pageId, String deviceId, String createTime);
    }
    interface View{
        void showLoading();
        void dismissLoading();
        void showNoData();
        void showNoMore();
        void updateListUI(List<Item> itemList);
        void showOnFailure();
    }
}
