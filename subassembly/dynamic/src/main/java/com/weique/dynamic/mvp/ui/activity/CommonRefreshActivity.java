package com.weique.dynamic.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.adapter.ProviderBinderAdapter;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.customview.loadmore.VerticalSwipeRefreshLayout;
import com.weique.dynamic.R;
import com.weique.dynamic.R2;
import com.weique.dynamic.di.component.DaggerCommenRefreshComponent;
import com.weique.dynamic.mvp.contract.CommonRefreshContract;
import com.weique.dynamic.mvp.presenter.CommonRefreshPresenter;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * 适用于列表 activity   只有 列表 和 刷新
 * ================================================
 *
 * @author Administrator
 */
@Route(path = RouterHub.DYNAMIC_COMMON_REFRESH_ACTIVITY, name = "通用刷新")
public class CommonRefreshActivity extends BaseActivity<CommonRefreshPresenter> implements CommonRefreshContract.View {

    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R2.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R2.id.right_text)
    TextView rightText;
    @BindView(R2.id.right_layout)
    RelativeLayout rightLayout;
    @BindView(R2.id.public_toolbar)
    Toolbar publicToolbar;
    @BindView(R2.id.swipe_refresh)
    VerticalSwipeRefreshLayout swipeRefresh;


    private ProviderBinderAdapter baseBinderAdapter;


    /**
     * 上个界面跳转的参数
     */
    @Autowired(name = Constants.COMMON_COLLECTION)
    CommonCollectBean commonCollectBean;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommenRefreshComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.dynamic_activity_commen_refresh;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        setTitle(commonCollectBean.getTitle());
        initRv();
    }

    /**
     * 初始化 recyclerview
     */
    private void initRv() {
        try {
            baseBinderAdapter = new ProviderBinderAdapter();
            ArmsUtils.configRecyclerView(rv, new LinearLayoutManager(this));
            rv.setAdapter(baseBinderAdapter);
            /*
             *  获取数据赋值
             */
            assert mPresenter != null;
            /*
             *  初始化数据
             */
            mPresenter.addItemBinders(commonCollectBean);
            mPresenter.getAllData(commonCollectBean, true);
            /*
             *  刷新
             */
            swipeRefresh.setOnRefreshListener(() -> mPresenter.getAllData(commonCollectBean, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadMoreComplete() {
        baseBinderAdapter.getLoadMoreModule().loadMoreComplete();
    }

    @Override
    public void loadMoreEnd() {
        baseBinderAdapter.getLoadMoreModule().loadMoreEnd(true);
    }

    @Override
    public void showLoading(boolean showLoading) {
        if(showLoading) {
            showLoadingDialog();
        }
    }

    @Override
    public void hideLoading() {
        dismissLoading();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public ProviderBinderAdapter getBinderAdapter() {
        return baseBinderAdapter;
    }
}
