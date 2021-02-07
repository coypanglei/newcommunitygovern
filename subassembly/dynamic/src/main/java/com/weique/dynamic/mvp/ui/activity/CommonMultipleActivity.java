package com.weique.dynamic.mvp.ui.activity;

import android.content.Context;
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
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.weique.dynamic.R;
import com.weique.dynamic.R2;
import com.weique.dynamic.di.component.DaggerCommonMultipleComponent;
import com.weique.dynamic.mvp.contract.CommonMultipleContract;
import com.weique.dynamic.mvp.presenter.CommonMultiplePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


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

@Route(path = RouterHub.DYNAMIC_COMMON_MULTIPLE_ACTIVITY, name = "通用多布局")
public class CommonMultipleActivity extends BaseActivity<CommonMultiplePresenter> implements CommonMultipleContract.View {
    /**
     * 提供不同布局
     */
    @Inject
    ProviderMultiAdapter providerMultiAdapter;

    /**
     * 上个界面跳转的参数
     */
    @Autowired(name = Constants.COMMON_COLLECTION)
    CommonCollectBean commonCollectBean;
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
    @BindView(R2.id.rv)
    RecyclerView rv;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCommonMultipleComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.dynamic_activity_common_multiple;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        ArmsUtils.configRecyclerView(rv, new LinearLayoutManager(this));
        rv.setClipToPadding(false);
        rv.setAdapter(providerMultiAdapter);
        assert mPresenter != null;
        mPresenter.setInterfaceData(commonCollectBean);
    }

    @Override
    public void showLoading(boolean showLoading) {

    }

    @Override
    public void hideLoading() {

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
    public Context getActivity() {
        return this;
    }

    @Override
    public ProviderMultiAdapter getBinderAdapter() {
        return providerMultiAdapter;
    }


}
