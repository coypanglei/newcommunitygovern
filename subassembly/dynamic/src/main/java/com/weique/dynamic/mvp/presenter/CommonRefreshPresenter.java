package com.weique.dynamic.mvp.presenter;

import android.app.Application;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BasePresenter;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.BaseBinderAdapterBean;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.utils.globalutils.NetworkUtils;
import com.weique.commonres.utils.globalutils.SignUtil;
import com.weique.dynamic.R;
import com.weique.dynamic.mvp.contract.CommonRefreshContract;
import com.weique.commonres.http.CenterService;
import com.weique.commonres.entity.CommonBackBean;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * ================================================
 * Description:
 * 管理 数据  接口参数
 *
 * @author Administrator
 */
@ActivityScope
public class CommonRefreshPresenter extends BasePresenter<CommonRefreshContract.Model, CommonRefreshContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    IRepositoryManager iRepositoryManager;
    @Inject
    Gson gson;

    @Inject
    CommonRefreshPresenter(CommonRefreshContract.Model model, CommonRefreshContract.View rootView) {
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


    /**
     * 根据不同的接口获取数据 生成列表
     */
    public void getAllData(CommonCollectBean commonCollectBean, boolean pullToRefresh) {


        try {
            int pageNumber = NetworkUtils.handlePaging(pullToRefresh, false);
            /*
             *  外部参数添加分页
             */
            commonCollectBean.getMap().put(NetworkUtils.IGNORE_NUMBER, pageNumber);
            commonCollectBean.getMap().put(NetworkUtils.Page_Size, NetworkUtils.PAGE_SIZE);
            /*
             *  接口获取 需要传入 接口类型
             */
            Observable<BaseResponse<CommonBackBean>> observable = iRepositoryManager
                    .obtainRetrofitService(CenterService.class)
                    // commonCollectBean.getPath() 接口名称   commonCollectBean.getMap() 接口参数
                    .getCommonObject(commonCollectBean.getPath(), SignUtil.addParamSign(commonCollectBean.getMap()));
            NetworkUtils.commonGetNetworkData(observable,
                    mErrorHandler, mRootView, beans -> {
                        try {
                            List<Object> list = beans.getList();
                            if (ObjectUtils.isNotEmpty(list) && list.size() > 0) {
                                /*
                                 *刷新 清空数据
                                 *
                                 */
                                if (pullToRefresh) {
                                    mRootView.getBinderAdapter().getData().clear();
                                }
                                /*
                                 *  加载数据
                                 */
                                for (Object object : list) {
                                    mRootView.getBinderAdapter().getData().add(gson.fromJson(gson.toJson(object), commonCollectBean.getClassName()));
                                }
                                mRootView.getBinderAdapter().setList(mRootView.getBinderAdapter().getData());
                            } else {
                                /*
                                 * 刷新过后如果没有数据 显示 空布局
                                 */
                                if (pullToRefresh) {
                                    Objects.requireNonNull(mRootView.getBinderAdapter().getEmptyLayout()).setVisibility(View.VISIBLE);
                                }
                            }
                            NetworkUtils.handleLoadMore(list, mRootView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    },pullToRefresh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加布局
     *
     * @param commonCollectBean  公共采集参数
     */
    public void addItemBinders(CommonCollectBean commonCollectBean) {
        try {
            /*
               获取BindBeanList
             */
            List<BaseBinderAdapterBean> list = commonCollectBean.getBindBeanList();
            /*
              Adapter循环加入Binder
             */
            for (int i = 0; i < list.size(); i++) {
                mRootView.getBinderAdapter().addItemBinder(list.get(i).getaClass(), list.get(i).getBaseItemBinder(), list.get(i).getDiffItem());
            }
            mRootView.getBinderAdapter().getLoadMoreModule().setOnLoadMoreListener(() -> getAllData(commonCollectBean, false));
            mRootView.getBinderAdapter().setEmptyView(R.layout.public_null_content_layout);
            Objects.requireNonNull(mRootView.getBinderAdapter().getEmptyLayout()).setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
