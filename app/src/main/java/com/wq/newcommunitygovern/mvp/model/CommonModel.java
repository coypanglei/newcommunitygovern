package com.wq.newcommunitygovern.mvp.model;

import android.app.Activity;
import android.app.Application;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.blankj.utilcode.util.KeyboardUtils;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.CommonTitleBean;
import com.weique.commonres.utils.AppUtils;
import com.weique.commonres.utils.commonutils.BackObjectInterface;
import com.weique.commonres.utils.commonutils.CheckUtils;
import com.weique.commonres.utils.commonutils.CommonDialog;
import com.weique.commonres.utils.globalutils.NetworkUtils;
import com.weique.commonres.utils.globalutils.SignUtil;
import com.weique.commonres.http.CenterService;
import com.wq.newcommunitygovern.mvp.contract.CommonContract;
import com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService;
import com.wq.newcommunitygovern.mvp.model.entity.GlobalUserInfoBean;
import com.wq.newcommunitygovern.mvp.ui.activity.main.LoginActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService.EDIT_USER_INFO;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/27/2020 14:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
public class CommonModel extends BaseModel implements CommonContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    IRepositoryManager mRepositoryManager;

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public CommonModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable putCommonList(Map map, String path) {
        return mRepositoryManager
                .obtainRetrofitService(CenterService.class)
                // commonCollectBean.getPath() 接口名称   commonCollectBean.getMap() 接口参数
                .putCommonList(path, SignUtil.addParamSign(map));
    }

    /**
     * 获取 枚举值
     *
     * @param type
     * @return
     */
    @Override
    public Observable<BaseResponse<List<CommonTitleBean>>> getCommonEnums(String type) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("type", type);
        return mRepositoryManager
                .obtainRetrofitService(CenterService.class)
                .getTitles(SignUtil.addParamSign(map));
    }


    @Override
    public void getObject(CommonCollectBean commonBean, IView mRootView, BackObjectInterface backObjectInterface) {
        switch (commonBean.getPath()) {
            case "APP/Login/getUserInfo":

                NetworkUtils.commonGetNetworkData(mRepositoryManager.
                        obtainRetrofitService(MainInterfaceService.class).
                        getUserInfoDetail(SignUtil.addParamSign(null)), mErrorHandler, mRootView, new NetworkUtils.ProgressMonitorHandle<GlobalUserInfoBean>() {
                    @Override
                    public void getBodyFromObject(GlobalUserInfoBean globalUserInfoBean) {
                        backObjectInterface.getObject(globalUserInfoBean);
                    }
                }, false);
                break;
            default:
                break;
        }
    }


}