package com.weique.commonres.dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.IView;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.utils.globalutils.NetworkUtils;



import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.armscomponent.commonres.R;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.TranslationConfig;

/**
 * 通用  集合弹框 popupWindow
 *
 * @author Administrator
 */
public class CommonRecyclerPopupWindow extends BasePopupWindow {
    private Unbinder unbinder;
    private RxErrorHandler mErrorHandler;
    private IView mRootView;
    private IRepositoryManager iRepositoryManager;

    public CommonRecyclerPopupWindow(Context context) {
        super(context);
    }

    public CommonRecyclerPopupWindow(Context context, RxErrorHandler mErrorHandler, IView mRootView, IRepositoryManager iRepositoryManager) {
        super(context);
        this.mErrorHandler = mErrorHandler;
        this.mRootView = mRootView;
        this.iRepositoryManager = iRepositoryManager;
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.public_common_dialog_select);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.FROM_BOTTOM)
                .toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.TO_BOTTOM)
                .toDismiss();
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
//            Observable<BaseResponse<List<CommonTitleBean>>> observable = iRepositoryManager
//                    .obtainRetrofitService(CenterService.class)
//                    // commonCollectBean.getPath() 接口名称   commonCollectBean.getMap() 接口参数
//                    .getTitles(SignUtil.addParamSign(commonCollectBean.getMap()));
//            NetworkUtils.commonGetNetworkData(observable,
//                    mErrorHandler, mRootView, beans -> {
//                        try {
////                            List<Object> list = beans.getList();
////                            if (ObjectUtils.isNotEmpty(list) && list.size() > 0) {
////                                /*
////                                 *刷新 清空数据
////                                 *
////                                 */
////                                if (pullToRefresh) {
////                                    mRootView.getBinderAdapter().getData().clear();
////                                }
////                                /*
////                                 *  加载数据
////                                 */
////                                for (Object object : list) {
////                                    mRootView.getBinderAdapter().getData().add(gson.fromJson(gson.toJson(object), commonCollectBean.getClassName()));
////                                }
////                                mRootView.getBinderAdapter().setList(mRootView.getBinderAdapter().getData());
////                            } else {
////                                /*
////                                 * 刷新过后如果没有数据 显示 空布局
////                                 */
////                                if (pullToRefresh) {
////                                    mRootView.getBinderAdapter().getEmptyLayout().setVisibility(View.VISIBLE);
////                                }
////                            }
////                            NetworkUtils.handleLoadMore(list, mRootView);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }, pullToRefresh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
