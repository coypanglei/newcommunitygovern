package com.wq.newcommunitygovern.mvp.presenter;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;

import javax.inject.Inject;

import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.config.PictureMimeType;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.adapter.provider.ProviderFactory;
import com.weique.commonres.adapter.provider.ProviderStore;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.CommonInterfaceStyle;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.UploadFileRsponseBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.dialog.EditOneDialog;
import com.weique.commonres.dialog.LookPicDialog;
import com.weique.commonres.utils.AppUtils;
import com.weique.commonres.utils.commonutils.CheckUtils;
import com.weique.commonres.utils.commonutils.CommonDialog;
import com.weique.commonres.utils.commonutils.PickerViewUtil;
import com.weique.commonres.utils.commonutils.PictureSelectorUtils;
import com.weique.commonres.utils.commonutils.StringUtil;
import com.weique.commonres.utils.globalutils.NetworkUtils;
import com.weique.commonres.utils.globalutils.ObjectToMapUtils;
import com.weique.commonres.utils.globalutils.UserInfoUtils;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.mvp.contract.CommonContract;
import com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService;
import com.wq.newcommunitygovern.mvp.ui.activity.main.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wq.newcommunitygovern.mvp.model.api.MainInterfaceService.EDIT_USER_INFO;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/27/2020 14:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 *
 * @author Administrator
 */
@ActivityScope
public class CommonPresenter extends BasePresenter<CommonContract.Model, CommonContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    Gson gson;

    private LookPicDialog lookPicDialog;
    private ProviderMultiAdapter adapter;
    private OptionsPickerView pickerView;


    /**
     * 弹框编辑
     */
    private EditOneDialog editOneDialog;

    private CommonCollectBean commonCollectBean;

    @Inject
    IRepositoryManager mRepositoryManager;

    @Inject
    CommonPresenter(CommonContract.Model model, CommonContract.View rootView) {
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
        this.editOneDialog = null;
        this.commonCollectBean = null;
        this.mRepositoryManager = null;
        this.pickerView = null;
    }

    /**
     * 初始化adapter
     */
    public void initAdapter() {
        adapter = new ProviderMultiAdapter(mImageLoader, gson);
        ArmsUtils.configRecyclerView(mRootView.getRecyclerView(), new LinearLayoutManager(mRootView.getContext()));
        mRootView.getRecyclerView().setClipToPadding(false);
        mRootView.getRecyclerView().setAdapter(adapter);
        adapter.addChildClickViewIds(R.id.mShadowLayout, R.id.name, R.id.tv_info, R.id.ll_info, R.id.relativeLayout, R.id.headShadowLayout);
        adapter.setOnItemChildClickListener(this::onItemChildClick);
    }


    /**
     * 根据不同布局 生成列表
     */
    public void setInterfaceData(CommonCollectBean commonCollectBean) {
        setCommonStyle(commonCollectBean);
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
            getInfoById(commonCollectBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用来改变导航栏和 界面背景
     *
     * @param commonCollectBean 上个界面的参数
     */
    private void setCommonStyle(@NotNull CommonCollectBean commonCollectBean) {
        CommonInterfaceStyle styleBean = gson.fromJson(commonCollectBean.getStyle(), CommonInterfaceStyle.class);
        if (ObjectUtils.isNotEmpty(styleBean)) {
            if (ObjectUtils.isNotEmpty(styleBean.getStatusBarColor())) {
                ImmersionBar.with((Activity) mRootView.getContext()).statusBarColor(styleBean.getStatusBarColor()).init();
            } else {
                ImmersionBar.with((Activity) mRootView.getContext()).statusBarColor(R.color.public_white).init();
            }
            if (ObjectUtils.isNotEmpty(styleBean.getBackground())) {
                mRootView.getRelativeLayout().setBackgroundColor(Color.parseColor(styleBean.getBackground()));
            }
        } else {
            ImmersionBar.with((Activity) mRootView.getContext()).statusBarColor(R.color.public_white).init();
        }

    }


    private void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        try {
            RecordsBean recordsBean = (RecordsBean) adapter.getData().get(position);
            List<RecordsBean> recordlist = new ArrayList<>();
            switch (recordsBean.getType()) {
                /*
                 * 退出登录
                 */
                case "logout":
                    new CommonDialog.Builder(mRootView.getContext()).setTitle("确定退出")
                            .setPositiveButton((v, commonDialog) -> {
                                AppUtils.logout(mRootView.getContext());
                                ArmsUtils.killAll(LoginActivity.class);
                            }).create().show();
                    break;
                /*
                 *  跳转到其他页面 ，通过 后台获取的数据刷新页面
                 */
                case "jump":
                    switch (recordsBean.getChangeType()) {
                        case "changePassword":
                            recordlist.clear();
                            commonCollectBean = new CommonCollectBean();
                            commonCollectBean.setTitle("修改密码");
                            commonCollectBean.setStyle("{\"background\":\"#ffffff\"}");
                            recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.NULL_VIEW, "", "", "", "{\"height\":30,\"background\":\"#ffffff\"}", ""));
                            recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM_TOP, "旧密码", "OldPwd", "请输入旧密码", true, ""));
                            recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM_TOP, "新密码", "Pwd", "请输入新密码", true, ""));
                            recordlist.add(new RecordsBean(DynamicFormEnum.ItemFlagEnum.EDIT_ITEM_TOP, "重复新密码", "", "请重复新密码", true, "resetPwd"));
                            RecordsBean changePassword = new RecordsBean(DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE, "修改密码", "", "", "{\"LayoutBackgroundTrue\":\"#3982f6\",\"LayoutBackground\":\"#53a8f4\",\"cornerRadius\":30,\"leftMargin\":60,\"rightMargin\":60,\"textColor\":\"#ffffff\",\"topMargin\":60}", "submit");
                            changePassword.setChangeType(EDIT_USER_INFO);
                            recordlist.add(changePassword);
                            commonCollectBean.setList(recordlist);
                            ARouter.getInstance().build(RouterHub.APP_COMMON_ACTIVITY)
                                    .withParcelable(Constants.COMMON_COLLECTION, commonCollectBean).
                                    navigation();
                            break;
                        default:
                            break;
                    }
                    break;
                    /*
                     行为 编辑弹窗
                     */
                case "popup_edit":
                    if (ObjectUtils.isEmpty(editOneDialog)) {
                        editOneDialog = new EditOneDialog(mRootView.getContext());
                        editOneDialog.setRecordBeanInterface(recordsBean1 -> {
                            /*
                             * 整理提交数据
                             */
                            submit();
                            /*
                             *提交后台 更新 数据
                             */
                            putBackstage(recordsBean1, position);
                        });
                    }
                    editOneDialog.setRecordsBean(recordsBean);
                    if (!editOneDialog.isShowing()) {
                        editOneDialog.showPopupWindow();
                    }
                    break;
                /*
                 *  选择框
                 */
                case "popup_select":
                    getCommonEnums(recordsBean, position);
                    break;
                case "popup_time":
                    getTime(recordsBean, position);
                    break;
                /*
                 *  提交
                 */
                case "submit":
                    /*
                     * 整理提交数据
                     */
                    submit();
                    /*
                      提交后台 更新 数据
                     */
                    putBackstage(recordsBean, position);
                    break;
                /*
                 *  更改 头像 或者查看 头像
                 */
                case "HeadUrl":
                    if (view.getId() == R.id.headShadowLayout) {
                        String head = UserInfoUtils.getInstance().getUserInfo().getHeadUrl();
                        if (ObjectUtils.isNotEmpty(head)) {
                            if (ObjectUtils.isEmpty(lookPicDialog)) {
                                lookPicDialog = new LookPicDialog(mRootView.getContext());
                            }
                            lookPicDialog.setListData(head);
                            if (!lookPicDialog.isShowing()) {
                                lookPicDialog.showPopupWindow();
                            }

                        } else {
                            uploadHead(recordsBean, position);
                        }
                    } else {
                        uploadHead(recordsBean, position);

                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Map<String, Object> map = new HashMap<>();

    /**
     * 处理数据 必填 用来把界面保存的参数放入此集合
     */
    private void submit() {
        try {
            List<RecordsBean> putList = adapter.getData();
            for (RecordsBean bean : putList) {
                if (ObjectUtils.isNotEmpty(bean.getValue())) {
                    if (ObjectUtils.isNotEmpty(bean.getSelectValue()) && !"Gender".equals(bean.getField())) {

                        map.put(bean.getField(), bean.getSelectValue());
                    } else {
                        map.put(bean.getField(), bean.getValue());
                    }

                } else {
                    if (bean.getRequire()) {
                        ToastUtils.showLong(bean.getTitile() + "此项为必填");
                        return;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理提交后台逻辑 返回值相同
     */
    private void putBackstage(RecordsBean recordsBean, int position) {

        try {
            if (map.size() > 0) {
                if (CheckUtils.checkData(map, recordsBean)) {
                    NetworkUtils.commonGetNetworkData(mModel.putCommonList(map, recordsBean.getChangeType()), mErrorHandler,
                            mRootView, t ->
                            {
                                switch (recordsBean.getChangeType()) {
                                    /*
                                     * 修改密码
                                     */
                                    case EDIT_USER_INFO:

                                        new CommonDialog.Builder(mRootView.getContext())
                                                .setContent("密码重置成功 请重新登录")
                                                .setCancelable(false)
                                                .setNegativeBtnShow(false)
                                                .setPositiveButton("重新登录", (v, commonDialog) -> {
                                                    AppUtils.logout(mRootView.getContext());
                                                    ArmsUtils.killAll(LoginActivity.class);
                                                }).create().show();
                                        break;
                                    /*
                                     *  修改个人信息
                                     */
                                    case MainInterfaceService.EDIT_EMPLOYEE:
                                        CheckUtils.saveUserInfo(recordsBean);
                                        adapter.notifyItemChanged(position);
                                        break;
                                    default:
                                        break;
                                }

                            }, true);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 更新头像
     *
     * @param recordsBean 数据
     * @param position    更新的位置
     */
    private void uploadHead(RecordsBean recordsBean, int position) {
        PictureSelectorUtils.openAlbum(1, PictureMimeType.ofImage(), mErrorHandler, mRootView, mRepositoryManager, mRootView.getContext(), uploadFileRsponseBeans -> {
            if (uploadFileRsponseBeans != null && uploadFileRsponseBeans.size() > 0) {
                UploadFileRsponseBean uploadFileRsponseBean = uploadFileRsponseBeans.get(0);
                if (StringUtil.isNullString(uploadFileRsponseBean.getUrl())) {
                    ArmsUtils.makeText("头像地址为空");
                    return;
                }
                // 头像 传入值
                recordsBean.setValue(uploadFileRsponseBean.getUrl());
                submit();
                putBackstage(recordsBean, position);
            } else {
                ArmsUtils.makeText("上传失败");
            }
        });
    }


    /**
     * 获取枚举值 选择框 默认选中 上次选择的
     */
    private void getCommonEnums(RecordsBean recordsBean, int position) {
        NetworkUtils.commonGetNetworkData(mModel.getCommonEnums(recordsBean.getCategory()), mErrorHandler, mRootView, commonTitleBeans -> {
            KeyboardUtils.hideSoftInput((Activity) mRootView.getContext());
            try {
                if (pickerView == null) {
                    pickerView = new OptionsPickerBuilder(mRootView.getContext(), (options1, options2, options3, v) -> {
                        recordsBean.setValue(commonTitleBeans.get(options1).getValue());
                        recordsBean.setSelectValue(commonTitleBeans.get(options1).getKey());
                        /*
                         * 整理提交数据
                         */
                        submit();
                        /*
                         *提交后台 更新 数据
                         */
                        putBackstage(recordsBean, position);
                    })
                            .setTitleBgColor(0xFFFFFFFF).build();
                }
                pickerView.setTitleText(recordsBean.getTitile());
                pickerView.setPicker(commonTitleBeans);

                /*
                  如果已经有值就默认选择
                 */
                for (int i = 0; i < commonTitleBeans.size(); i++) {
                    if (recordsBean.getValue().equals(commonTitleBeans.get(i).getValue())) {
                        pickerView.setSelectOptions(i);
                    }
                }
                if (!pickerView.isShowing()) {
                    pickerView.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, false);

    }

    /**
     * 选择时间
     *
     * @param recordsBean
     * @param position
     */
    private void getTime(RecordsBean recordsBean, int position) {
        KeyboardUtils.hideSoftInput((Activity) mRootView.getContext());
        PickerViewUtil.selectPickerTimeHasDefault((Activity) mRootView.getContext(), Constants.YMD1, recordsBean.getValue(), (date, v12) -> {
            String ymd = TimeUtils.date2String(date, Constants.YMD1);
            recordsBean.setValue(ymd);
            /*
             * 整理提交数据
             */
            submit();
            /*
             *提交后台 更新 数据
             */
            putBackstage(recordsBean, position);

        }).show();
    }


    /**
     * 获取详情
     */
    public void getInfoById(CommonCollectBean commonCollectBean) {
        try {
            if (ObjectUtils.isNotEmpty(commonCollectBean.getPath())) {
                mModel.getObject(commonCollectBean, mRootView, object -> {
                    Map<String, String> map = ObjectToMapUtils.str2Map(object);
                    Timber.e(map.toString());
                    List<RecordsBean> putList = adapter.getData();
                    for (RecordsBean bean : putList) {
                        //通用
                        if (map.containsKey(bean.getField())) {
                            Timber.e(bean.getField());
                            bean.setValue(map.get(bean.getField()));
                        }
                    }
                    adapter.notifyDataSetChanged();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
