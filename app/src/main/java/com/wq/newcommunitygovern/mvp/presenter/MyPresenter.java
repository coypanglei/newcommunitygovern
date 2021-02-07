package com.wq.newcommunitygovern.mvp.presenter;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.google.gson.Gson;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.adapter.provider.ProviderFactory;
import com.weique.commonres.adapter.provider.ProviderStore;
import com.weique.commonres.base.commonbean.BaseBinderAdapterBean;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.dialog.LookPicDialog;
import com.weique.commonres.utils.globalutils.UserInfoUtils;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.mvp.contract.MyContract;
import com.wq.newcommunitygovern.mvp.model.entity.InspectionRecordBean;
import com.wq.newcommunitygovern.mvp.ui.binds.InspectionItemBinder;

import java.util.ArrayList;
import java.util.List;

import static com.weique.commonres.base.commonbean.DynamicFormEnum.Behavior.LIST;
import static com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService.EDIT_EMPLOYEE;


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
 *
 * @author Administrator
 */
@FragmentScope
public class MyPresenter extends BasePresenter<MyContract.Model, MyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    IRepositoryManager mRepositoryManager;
    @Inject
    Gson gson;

    private LookPicDialog lookPicDialog = null;
    private ProviderMultiAdapter adapter;


    private CommonCollectBean commonCollectBean;

    @Inject
    MyPresenter(MyContract.Model model, MyContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.gson = null;
        this.adapter = null;
        this.lookPicDialog = null;
        this.commonCollectBean = null;
    }

    /**
     * 初始化adapter
     */
    public void initAdapter() {
        adapter = new ProviderMultiAdapter(mImageLoader, gson);
        ArmsUtils.configRecyclerView(mRootView.getRecyclerView(), new LinearLayoutManager(mRootView.getContext()));
        mRootView.getRecyclerView().setClipToPadding(false);
        mRootView.getRecyclerView().setAdapter(adapter);
        adapter.addChildClickViewIds(R.id.mShadowLayout, R.id.name, R.id.tv_info, R.id.ll_info, R.id.headShadowLayout);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            try {
                RecordsBean recordsBean = (RecordsBean) adapter.getData().get(position);
                switch (recordsBean.getType()) {
                    case "list":
                        commonCollectBean = new CommonCollectBean();
                        commonCollectBean.setTitle(recordsBean.getCategory());
                        commonCollectBean.setPath(recordsBean.getChangeType());
                        commonCollectBean.setType(LIST);
                        List<BaseBinderAdapterBean> list = new ArrayList<>();
                        list.add(new BaseBinderAdapterBean(InspectionRecordBean.class, new InspectionItemBinder(), null));
                        commonCollectBean.setBindBeanList(list);
                        commonCollectBean.setClassName(InspectionRecordBean.class);
                        ARouter.getInstance().build(RouterHub.DYNAMIC_COMMON_REFRESH_ACTIVITY)
                                .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                                navigation();
                        break;
                    case "personal_info":
                        UserInfoBean userInfoBean = UserInfoUtils.getInstance().getUserInfo();
                        Timber.e(view.getId() + "");
                        if (view.getId() == R.id.headShadowLayout) {
                            if (ObjectUtils.isNotEmpty(userInfoBean.getHeadUrl())) {
                                if (ObjectUtils.isEmpty(lookPicDialog)) {
                                    lookPicDialog = new LookPicDialog(mRootView.getContext());
                                }
                                lookPicDialog.setListData(userInfoBean.getHeadUrl());

                                if (!lookPicDialog.isShowing()) {
                                    lookPicDialog.showPopupWindow();
                                }
                            } else {
                                /* 跳转 个人信息
                                 *
                                 */

                            }
                        } else {
                            /* 跳转 个人信息
                             *
                             */
                            commonCollectBean = new CommonCollectBean();
                            commonCollectBean.setTitle("我的信息");
                            List<RecordsBean> recordSettinglist = new ArrayList<>();
                            recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30,\"visibleView\":true}", ""));
                            RecordsBean recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "头像", "HeadUrl", "", "{\"topMargin\":30,\"visibleView\":true}", "HeadUrl");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "姓名", "Name", "请填写姓名", "{\"visibleView\":true}", "popup_edit");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "性别", "Gender", "请选择性别", "{\"visibleView\":true}", "popup_select");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordsBean1.setCategory("EnumGender");
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "出生日期", "BirthDate", "请选择出生日期", "{\"visibleView\":true}", "popup_time");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "电话", "Tel", "请填写手机号", "{\"visibleView\":true,\"inputType\":\"phone\"}", "popup_edit");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "身份证号码", "SID", "请填写身份证号码", "{\"visibleView\":false,\"inputType\":\"id_number\"}", "popup_edit");
                            recordsBean1.setChangeType(EDIT_EMPLOYEE);
                            recordsBean1.setEdit(true);
                            recordSettinglist.add(recordsBean1);
                            recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30,\"visibleView\":true}", ""));
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "部门", "DepartName", "", "{\"visibleView\":true,\"inputType\":\"id_number\"}", "popup_edit");
                            recordsBean1.setEdit(false);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "党组织", "PartyDepartmentName", "", "{\"visibleView\":true,\"inputType\":\"id_number\"}", "popup_edit");
                            recordsBean1.setEdit(false);
                            recordSettinglist.add(recordsBean1);
                            recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM, "区域", "DepartmentName", "", "{\"visibleView\":false,\"inputType\":\"id_number\"}", "popup_edit");
                            recordsBean1.setEdit(false);

                            recordSettinglist.add(recordsBean1);
                            try {
                                Timber.e(gson.toJson(commonCollectBean));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            commonCollectBean.setList(recordSettinglist);
                            commonCollectBean.setPath("APP/Login/getUserInfo");
                            ARouter.getInstance().build(RouterHub.APP_COMMON_ACTIVITY)
                                    .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                                    navigation();

                        }
                        break;
                    case "video":
                        commonCollectBean = new CommonCollectBean();
                        commonCollectBean.setTitle("视频会商");
                        List<RecordsBean> recordlist = new ArrayList<>();
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.PERSONAL_INFORMATION, "", "", "", "{\"height\":30}", "personal_info"));
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30,\"visibleView\":true}", ""));
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "打卡签到", "", "", "{\"img\":\"public_my_icon_sign_in\",\"visibleView\":true}", ""));
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "视频会商", "", "", "{\"background\":\"\",\"img\":\"public_video_consult\",\"visibleView\":false}", ""));
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30}", ""));
                        recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK, "设置", "", "", "{\"background\":\"\",\"img\":\"public_my_icon_settings\",\"visibleView\":false}", "jump"));
                        commonCollectBean.setList(recordlist);
                        ARouter.getInstance().build(RouterHub.APP_COMMON_ACTIVITY)
                                .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                                navigation();
                        break;

                    case "jump":
                        commonCollectBean = new CommonCollectBean();
                        commonCollectBean.setTitle("设置");
                        List<RecordsBean> recordSettinglist = new ArrayList<>();
                        recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "版本信息(二维码)", "", AppUtils.getAppVersionName(), "{\"img\":\"public_qrcode\",\"visibleView\":true}", "qr_code"));
                        recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "版本更新", "", "检测", "{\"visibleView\":true}", ""));
                        RecordsBean recordsBean1 = new RecordsBean(DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT, "修改密码", "", "", "{\"visibleView\":false}", "jump");
                        recordsBean1.setChangeType("changePassword");
                        recordSettinglist.add(recordsBean1);
                        recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30}", ""));
                        recordSettinglist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE, "安全退出", "", "", "{\"background\":\"\",\"img\":\"public_my_icon_settings\",\"visibleView\":false}", "logout"));
                        commonCollectBean.setList(recordSettinglist);
                        ARouter.getInstance().build(RouterHub.APP_COMMON_ACTIVITY)
                                .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                                navigation();

                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 根据不同布局 生成列表
     */
    public void setInterfaceData(CommonCollectBean commonCollectBean) {

        try {
            List<RecordsBean> recordsBeans = commonCollectBean.getList();
            List<BaseItemProvider> itemProviderList = new ArrayList<>();
            //供应者商店
            ProviderStore providerStore = new ProviderStore(new ProviderFactory());
            for (RecordsBean bean : recordsBeans) {
                itemProviderList.add(providerStore.shipment(bean.getParamtype(), bean.getStyle()));
            }
            adapter.addItemProvider(itemProviderList);
            adapter.setNewInstance(recordsBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 获取分类
//     */
//    public void getElementType() {
//        Map map = new HashMap(2);
//        map.put("TypeName", "金矿");
//        NetworkUtils.commonGetNetworkData(mRepositoryManager.obtainRetrofitService(MainInterfaceService.class)
//                        .getElementTypeForRecordBean(SignUtil.addParamSign(map)), mErrorHandler,
//                mRootView, t -> {
//                    Timber.e(t.toString());
//                },false);
//
//    }


}
