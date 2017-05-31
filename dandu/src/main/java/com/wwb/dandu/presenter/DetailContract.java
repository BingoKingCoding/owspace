package com.wwb.dandu.presenter;

import com.wwb.dandu.model.entity.DetailEntity;

/**
 * Created by wwb on 2017/2/3.
 */

public interface DetailContract {
    interface Presenter{
        void getDetail(String itemId);
    }
    interface View{
        void showLoading();
        void dismissLoading();
        void updateListUI(DetailEntity detailEntity);
        void showOnFailure();
    }
}
