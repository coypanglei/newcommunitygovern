package com.wq.newcommunitygovern.mvp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.utils.commonutils.ARouterUtils;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerSplashComponent;
import com.wq.newcommunitygovern.mvp.contract.SplashContract;
import com.wq.newcommunitygovern.mvp.presenter.SplashPresenter;

import javax.inject.Inject;

import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * ================================================
 *
 * @author Administrator
 */
@Route(path = RouterHub.APP_SPLASHACTIVITY, name = "闪屏页")
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {
    @Inject
    Gson gson;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.app_activity_splash;

    }

    @OnClick({R.id.click, R.id.click1})
    public void onViewClick(View v) {
        if (v.getId() == R.id.click) {
            ARouterUtils.navigation(this, RouterHub.APP_LOGIN_ACTIVITY, null);
        } else {
            ARouterUtils.navigation(this, RouterHub.APP_SIGNIN_ACTIVITY, null);
            /*CommonCollectBean commonCollectBean = new CommonCollectBean();
            commonCollectBean.setTitle("问题自查");
            commonCollectBean.setPath("app/weique/cloud/suggestion/get/page/list");
            commonCollectBean.setType(DynamicFormEnum.Behavior.DETAIL);
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("id", "1");
            List<RecordsBean> list = new ArrayList<>();

            list.add(new RecordsBean(EDIT_ITEM, "企业名称", "Name", "请填写企业名称(必填)"));
            commonCollectBean.setList(list);

            commonCollectBean.setMap(stringObjectMap);
            Timber.e(commonCollectBean.toString());
            Gson gson = new Gson();

            ARouter.getInstance().build(RouterHub.DYNAMIC_COMMON_MULTIPLE_ACTIVITY)
                    .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                    navigation();*/
        }
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarColor("#ffffff").init();

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
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
