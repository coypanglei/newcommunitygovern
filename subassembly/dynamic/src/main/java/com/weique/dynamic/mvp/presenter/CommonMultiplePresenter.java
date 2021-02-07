package com.weique.dynamic.mvp.presenter;

import android.app.Application;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import com.weique.commonres.adapter.provider.ProviderFactory;
import com.weique.commonres.adapter.provider.ProviderStore;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.dynamic.mvp.contract.CommonMultipleContract;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/17/2020 13:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 *
 * @author Administrator
 */
@ActivityScope
public class CommonMultiplePresenter extends BasePresenter<CommonMultipleContract.Model, CommonMultipleContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public CommonMultiplePresenter(CommonMultipleContract.Model model, CommonMultipleContract.View rootView) {
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
     * 根据不同布局 生成列表
     */
    public void setInterfaceData(CommonCollectBean commonCollectBean) {

        try {
            List<RecordsBean> recordsBeans = commonCollectBean.getList();
            List<BaseItemProvider> itemProviderList = new ArrayList<>();
            //供应者商店
            ProviderStore providerStore = new ProviderStore(new ProviderFactory());
            for (RecordsBean bean : recordsBeans) {
                itemProviderList.add(providerStore.shipment(bean.getParamtype(),bean.getStyle()));
            }
            mRootView.getBinderAdapter().addItemProvider(itemProviderList);
            mRootView.getBinderAdapter().setNewInstance(recordsBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
