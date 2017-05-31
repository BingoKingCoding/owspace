package com.wwb.dandu.presenter;

import com.orhanobut.logger.Logger;
import com.wwb.dandu.model.api.ApiService;
import com.wwb.dandu.model.entity.DetailEntity;
import com.wwb.dandu.model.entity.Result;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wwb on 2017/1/30.
 */

public class DetailPresenter implements DetailContract.Presenter {
    private ApiService apiService;
    private DetailContract.View view;

    @Inject
    public DetailPresenter(DetailContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
        Logger.d("apppp:" + apiService);
    }

    @Override
    public void getDetail(String itemId) {
        apiService.getDetail("api", "getPost", itemId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result.Data<DetailEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showOnFailure();
                    }

                    @Override
                    public void onNext(Result.Data<DetailEntity> detailEntityData) {
                        view.updateListUI(detailEntityData.getDatas());
                    }
                });
    }
}
