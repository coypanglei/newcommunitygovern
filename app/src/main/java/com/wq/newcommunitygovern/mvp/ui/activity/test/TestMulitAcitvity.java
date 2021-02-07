package com.wq.newcommunitygovern.mvp.ui.activity.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.weique.commonres.core.RouterHub;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.app.custom.SuperRecyclerMultiAdapterView;
import com.wq.newcommunitygovern.mvp.model.entity.dynamic.DynamicFormItemBean;
import com.wq.newcommunitygovern.mvp.model.entity.User;

import org.simple.eventbus.Subscriber;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@Route(path = RouterHub.APP_TESTMULITACITVITY, name = "测试")
public class TestMulitAcitvity extends AppCompatActivity {

    @BindView(R.id.super_recycler_adapter_view)
    SuperRecyclerMultiAdapterView superRecyclerMultiAdapterView;
    @BindView(R.id.layout)
    LinearLayout layout;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_test_mulit_acitvity);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarColor(R.color.public_white).init();
        initHeardView();
        MultipleProviderImpl();
    }

    private void initHeardView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.public_include_title, null);
        layout.addView(inflate, 0);
        setTitle("title");
    }


    /**
     * 多样式
     */
    private void MultipleProviderImpl() {
        //多样式
        List<DynamicFormItemBean> data = new User().packObjectToVoItem(RouterHub.APP_TESTMULITACITVITY);
        superRecyclerMultiAdapterView.setMultiRecyclerViewData(null);
        superRecyclerMultiAdapterView.getAdapter().setOnItemChildClickListener((adapter, view, position) -> {
            DynamicFormItemBean bean = (DynamicFormItemBean) adapter.getItem(position);
            switch (bean.getItemFlag()) {
                default:
                    break;
            }
        });
        superRecyclerMultiAdapterView.getAdapter().setOnItemClickListener((adapter, view, position) -> {
            DynamicFormItemBean bean = (DynamicFormItemBean) adapter.getItem(position);
        });
    }


    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }

    @Subscriber(tag = RouterHub.APP_TESTMULITACITVITY)
    public void eventBusCallback() {

    }
}
