package com.weique.commonres.utils.commonutils;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.IView;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.QuestionBean;
import com.weique.commonres.base.commonbean.UploadFileRsponseBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.service.CommonRequestService;
import com.weique.commonres.utils.globalutils.NetworkUtils;
import com.weique.commonres.utils.globalutils.SignUtil;
import com.weique.commonres.utils.globalutils.UserInfoUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author GK
 * @description: 所有公共网络请求写在这里
 * @date :2020/8/16 10:26
 * ！！！ 公共请求的  返回对象放到   com.weique.commonres.base.commonbean 文件夹下
 */
public class CommonNetworkRequest {

    /**上传文件
     * @param mErrorHandler      mErrorHandler 错误回调
     * @param mRootView          mRootView
     * @param mRepositoryManager mRepositoryManager
     * @param handle             handle
     */
    public static void upLoadFile(
                                  List<String> compressPaths,
                                  RxErrorHandler mErrorHandler,
                                  IView mRootView,
                                  IRepositoryManager mRepositoryManager,
                                  @Nullable NetworkUtils.ProgressMonitorHandle<List<UploadFileRsponseBean>> handle) {
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart(Constants.USER_ID, StringUtil.setText(UserInfoUtils.getInstance().getUserInfo().getUid()));
            for (String s : compressPaths) {
                File file = new File(s);
                String parse = "";
                if (s.endsWith(Constants.PNG)) {
                    parse = "image/png";
                } else if (s.endsWith(Constants.JPG)) {
                    parse = "image/JPG";
                } else if (s.endsWith(Constants.JPEG)) {
                    parse = "image/jpeg";
                } else {
                    parse = "application/octet-stream";
                }
                RequestBody requestFile = RequestBody.create(MediaType.parse(parse), file);
                builder.addFormDataPart("file", file.getName(), requestFile);
            }

            Observable<BaseResponse<List<UploadFileRsponseBean>>> baseBeanObservable = mRepositoryManager.
                    obtainRetrofitService(CommonRequestService.class).
                    uploadFile(builder.build());
            NetworkUtils.commonGetNetworkData(baseBeanObservable, mErrorHandler, mRootView, handle,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param map                公共请求
     * @param path               动态的url
     * @param mErrorHandler      mErrorHandler 错误处理
     * @param mRootView          mRootView Viw对象
     * @param mRepositoryManager mRepositoryManager 接口调用管理者
     * @param handle             handle 回调
     */
    public static void commonRequestOne(Map<String, Object> map,
                                        String path,
                                        RxErrorHandler mErrorHandler,
                                        IView mRootView,
                                        IRepositoryManager mRepositoryManager,
                                        @Nullable NetworkUtils.ProgressMonitorHandle<QuestionBean> handle,boolean showLoading) {
        try {
            Observable<BaseResponse<QuestionBean>> observable = mRepositoryManager
                    .obtainRetrofitService(CommonRequestService.class)
                    .commonRequestGet(path, SignUtil.addParamSign(map));
            NetworkUtils.commonGetNetworkData(observable, mErrorHandler, mRootView, handle,showLoading);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
