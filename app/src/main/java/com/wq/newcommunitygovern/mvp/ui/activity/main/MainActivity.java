package com.wq.newcommunitygovern.mvp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ObjectUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.core.RouterHub;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerMainComponent;
import com.wq.newcommunitygovern.mvp.contract.MainContract;
import com.wq.newcommunitygovern.mvp.presenter.MainPresenter;
import com.wq.newcommunitygovern.mvp.ui.fragment.HomeFragment;
import com.wq.newcommunitygovern.mvp.ui.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description: 主界面
 * <p>
 * ================================================
 *
 * @author Administrator
 */
@Route(path = RouterHub.APP_MAIN_ACTIVITY, name = "主界面")
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;

    private FragmentManager fragmentManager;

    private FragmentTransaction transition;

    private List<BaseFragment> fragments;

    private HomeFragment homeFragment;

    private MyFragment myFragment;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.app_activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setItemIconTintList(null);
        fragments = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        fragments.add(homeFragment);
        hideOthersFragment(homeFragment, true);
        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            try {
                switch (menuItem.getItemId()) {
                    case R.id.agenda:
                        hideOthersFragment(homeFragment, false);
//                        messageImg.setImageResource(R.drawable.icon_news_);
//                        scanImg.setImageResource(R.drawable.icon_scan);
//                        scanText.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.white));
//                        messageText.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.white));
//                        title.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.white));
                        break;
                    case R.id.my:
                        if (ObjectUtils.isEmpty(myFragment)) {
                            myFragment = MyFragment.newInstance();
                            fragments.add(myFragment);
                            hideOthersFragment(myFragment, true);
                        } else {
                            hideOthersFragment(myFragment, false);
                        }
//                        messageImg.setImageResource(R.drawable.icon_news_b);
//                        scanImg.setImageResource(R.drawable.icon_scan_bb);
//                        scanText.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.black_333));
//                        messageText.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.black_333));
//                        title.setTextColor(ArmsUtils.getColor(MainActivity.this, R.color.black_333));
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        });

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


    /**
     * 动态显示Fragment
     *
     * @param showFragment 要增加的fragment
     * @param add          true：增加fragment；false：切换fragment
     */
    private void hideOthersFragment(Fragment showFragment, boolean add) {
        transition = fragmentManager.beginTransaction();
        transition.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        if (add) {
            transition.add(R.id.content, showFragment);
        }
        for (Fragment fragment : fragments) {
            if (showFragment.equals(fragment)) {
                transition.show(fragment);
            } else {
                transition.hide(fragment);
            }
        }
        transition.commit();
    }

}
