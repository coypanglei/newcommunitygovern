package com.wq.newcommunitygovern.mvp.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.KeyboardUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.utils.commonutils.StringUtil;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerLoginComponent;
import com.wq.newcommunitygovern.mvp.contract.LoginContract;
import com.wq.newcommunitygovern.mvp.presenter.LoginPresenter;


import butterknife.BindView;
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
@Route(path = RouterHub.APP_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_mobile)
    TextInputEditText etMobile;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.app_activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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

    @OnClick({R.id.btn_login})
    public void onClickView(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                try {
                    KeyboardUtils.hideSoftInput(this);
                    String loginName = etMobile.getText().toString();
                    String ps = etPassword.getText().toString();
                    if (StringUtil.isNullString(loginName)) {
                        ArmsUtils.makeText(ArmsUtils.getString(this, "账号不能为空"));
                        return;
                    }
                    if (StringUtil.isNullString(ps)) {
                        ArmsUtils.makeText(ArmsUtils.getString(this, "密码不能为空"));
                        return;
                    }
                    mPresenter.login(loginName, ps);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
