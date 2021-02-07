package com.wq.newcommunitygovern.mvp.presenter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.adapter.provider.ProviderFactory;
import com.weique.commonres.adapter.provider.ProviderStore;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.utils.commonutils.ARouterUtils;
import com.weique.commonres.utils.globalutils.NetworkUtils;
import com.weique.commonres.utils.globalutils.SignUtil;
import com.weique.commonres.utils.globalutils.UserInfoUtils;
import com.weique.commonres.utils.globalutils.UserLoginInfoUtils;
import com.weique.commonservice.zongzhi.bean.LoginInfoBean;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;
import com.wq.newcommunitygovern.mvp.contract.LoginContract;
import com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;


/**
 * ================================================
 * Description:
 * <p>
 * ================================================
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    IRepositoryManager mRepositoryManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void login(String loginName, String ps) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("loginName", loginName);
        map.put("loginPsd", ArmsUtils.encodeSHA(ps).toUpperCase());
        NetworkUtils.commonGetNetworkData(mModel.login(map), mErrorHandler,
                mRootView, t -> {
                    UserInfoUtils.getInstance().saveUserInfo(t);
                    UserLoginInfoUtils.getInstance().saveLoginInfo(new LoginInfoBean(loginName, ps));
                    ARouterUtils.navigation(mRootView.getContext(), RouterHub.APP_MAIN_ACTIVITY, null);
                },true);

    }

}
