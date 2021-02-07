package com.wq.newcommunitygovern.mvp.ui.activity.signin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.core.RouterHub;
import com.weique.sdk.baidumap.MapShowPointsView;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerSigninComponent;
import com.wq.newcommunitygovern.mvp.contract.SigninContract;
import com.wq.newcommunitygovern.mvp.presenter.SigninPresenter;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:签到界面
 * <p>
 * ================================================
 *
 * @author Administrator
 */
@Route(path = RouterHub.APP_SIGNIN_ACTIVITY, name = "签到界面")
public class SigninActivity extends BaseActivity<SigninPresenter> implements SigninContract.View {
    @BindView(R.id.map_view)
    MapShowPointsView mapShowPointsView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSigninComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        mapShowPointsView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapShowPointsView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapShowPointsView.onDestroy();
        super.onDestroy();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.app_activity_signin;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        showLoading(true);
        //单点
//        LatLng point = new LatLng(39.163175, 116.100244);
//        mapShowPointsView.setPointAndShow(point);
        //路径  和 多点
//        List<LatLng> points = new ArrayList<LatLng>();
//        points.add(new LatLng(39.865, 116.444));
//        points.add(new LatLng(39.825, 116.494));
//        points.add(new LatLng(39.855, 116.534));
//        points.add(new LatLng(39.805, 116.594));
//        mapShowPointsView.setPointLineAndShow(points);
//        mapShowPointsView.updateMapStatus(point, false);
        mapShowPointsView.setListening(new MapShowPointsView.MapShowPointsViewListening() {
            @Override
            public void onTheCodingListener() {
                hideLoading();
            }
        });
    }

    @Override
    public void showLoading(boolean showLoading) {
        showLoadingDialog();
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
}
