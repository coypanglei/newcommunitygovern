package com.wq.newcommunitygovern.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.di.component.DaggerMyComponent;
import com.wq.newcommunitygovern.mvp.contract.MyContract;
import com.wq.newcommunitygovern.mvp.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/19/2020 15:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 * @author Administrator
 */
public class MyFragment extends BaseFragment<MyPresenter> implements MyContract.View {


    @BindView(R.id.rv)
    RecyclerView rv;


    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_fragment_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        assert mPresenter != null;
        mPresenter.initAdapter();
        CommonCollectBean commonCollectBean = new CommonCollectBean();
        List<RecordsBean> list = new ArrayList<>();
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.PERSONAL_INFORMATION, "", "", "", "{\"height\":30}","personal_info"));
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30,\"visibleView\":true}",""));
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "打卡签到", "", "", "{\"img\":\"public_my_icon_sign_in\",\"visibleView\":true}",""));
        RecordsBean recordsBean = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "走访", "visit", "", "{\"background\":\"\",\"img\":\"public_icon_visit\",\"visibleView\":true}","list");
        recordsBean.setCategory("走访记录");
        recordsBean.setChangeType("app/InspectionRecord/GetInterViewRecord");
        list.add(recordsBean);
        RecordsBean recordsBeanTwo = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "巡检", "inspection", "", "{\"background\":\"\",\"img\":\"public_patrol_icon\",\"visibleView\":true}","list");
        recordsBeanTwo.setCategory("巡检记录");
        recordsBeanTwo.setChangeType("app/InspectionRecord/GetInspectionRecord");
        list.add(recordsBeanTwo);
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "视频会商", "video", "", "{\"background\":\"\",\"img\":\"public_video_consult\",\"visibleView\":false}","video"));
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30}",""));
        list.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "设置", "", "", "{\"background\":\"\",\"img\":\"public_my_icon_settings\",\"visibleView\":false}","jump"));
        commonCollectBean.setList(list);
        Timber.e(commonCollectBean.toString());
        mPresenter.setInterfaceData(commonCollectBean);
    }


    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    public RecyclerView getRecyclerView() {
        return rv;
    }
}
