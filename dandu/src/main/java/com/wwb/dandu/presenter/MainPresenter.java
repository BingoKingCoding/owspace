package com.wwb.dandu.presenter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;
import com.wwb.dandu.model.api.ApiService;
import com.wwb.dandu.model.entity.Item;
import com.wwb.dandu.model.entity.Result;
import com.wwb.dandu.util.TimeUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wwb on 2017/1/24.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private ApiService apiService;

    @Inject
    public MainPresenter(MainContract.View view,ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
        Logger.d("apppp:"+apiService);
    }

    @Override
    public void getListByPage(int page, int model, String pageId, String deviceId, String createTime) {
        apiService.getList("api", "getList", page, model, pageId, createTime, "android", "1.3.0", TimeUtil.getCurrentSeconds(), deviceId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result.Data<List<Item>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        view.showOnFailure();
                    }

                    @Override
                    public void onNext(Result.Data<List<Item>> listData) {
                        int size = listData.getDatas().size();
                        if (size > 0) {
                            view.updateListUI(listData.getDatas());
                        } else {
                            view.showNoMore();
                        }
                    }
                });
    }

    @Override
    public void getRecommend(String deviceId) {
        String key = TimeUtil.getDate("yyyyMMdd");
        Logger.e("getRecommend key:"+key);
        apiService.getRecommend("home","Api","getLunar","android",deviceId,deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e("onError:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        String key = TimeUtil.getDate("yyyyMMdd");
                        try {
//                             {
//                            "status":"ok",
//                                    "datas":{
//                                "20170131":{
//                                    "id":"293348",
//                                            "uid":"408361",
//                                            "thumbnail":"https://img.owspace.com/Public/uploads/Picture/2017-01-25/5888c128b4ea1.jpeg",
//                                            "status":"1",
//                                            "share":"http://static.owspace.com/wap/293348.html",
//                                            "html":"http://static.owspace.com/wap/293348.html"
//                                }
//                            },
//                            "msg":"",
//                                    "code":0
//                        }
                            //将上面的 thumbnail解析出来
                            JsonParser jsonParser = new JsonParser();
                            JsonElement jel = jsonParser.parse(s);
                            JsonObject jsonObject = jel.getAsJsonObject();
                            jsonObject = jsonObject.getAsJsonObject("datas");
                            jsonObject = jsonObject.getAsJsonObject(key);
                            view.showLunar(jsonObject.get("thumbnail").getAsString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}
