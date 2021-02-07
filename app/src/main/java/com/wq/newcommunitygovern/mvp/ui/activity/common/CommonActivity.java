package com.wq.newcommunitygovern.mvp.ui.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerCommonComponent;
import com.wq.newcommunitygovern.mvp.contract.CommonContract;
import com.wq.newcommunitygovern.mvp.presenter.CommonPresenter;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:  通用 activity 不包含 刷新  可用作新增 和 编辑
 * 设置界面
 *
 * @author Administrator
 */
@Route(path = RouterHub.APP_COMMON_ACTIVITY, name = "通用业务activity")
public class CommonActivity extends BaseActivity<CommonPresenter> implements CommonContract.View {


    @BindView(R.id.rv)
    RecyclerView rv;

    /**
     * 上个界面跳转的参数
     */
    @Autowired(name = Constants.COMMON_COLLECTION)
    CommonCollectBean commonCollectBean;
    /**
     * 界面
     */
    @BindView(R.id.rl_common)
    RelativeLayout rlCommon;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.app_activity_common;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        try {
            ARouter.getInstance().inject(this);
            Timber.e(commonCollectBean.toString());
            setTitle(commonCollectBean.getTitle());
            assert mPresenter != null;
            mPresenter.initAdapter();
            mPresenter.setInterfaceData(commonCollectBean);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void showLoading(boolean showLoading) {
        if (showLoading) {
            showLoadingDialog();
        }
    }

    @Override
    public void hideLoading() {
        dismissLoading();
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
    public RecyclerView getRecyclerView() {
        return rv;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public RelativeLayout getRelativeLayout() {
        return rlCommon;
    }
}
