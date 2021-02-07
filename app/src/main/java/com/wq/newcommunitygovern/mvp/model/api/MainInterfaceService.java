package com.wq.newcommunitygovern.mvp.model.api;

import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;
import com.wq.newcommunitygovern.mvp.model.entity.GlobalUserInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author GK
 * @description: 主界面相关请求
 * @date :2020/11/13 9:49
 */
public interface MainInterfaceService {

    /**
     * 图文验证码 登录
     *
     * @param map map
     * @return Observable
     */
    @FormUrlEncoded
    @POST("app/login/login")
    Observable<BaseResponse<UserInfoBean>> getValidateCodeLogin(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("app/CommunityElement/GetElementTypeForRecordBean")
    Observable<BaseResponse<CommonCollectBean>> getElementTypeForRecordBean(@FieldMap Map<String, Object> map);



    /**
     * 获取用户详情
     *
     * @param map map
     * @return Observable
     */
    @GET("APP/Login/getUserInfo")
    Observable<BaseResponse<GlobalUserInfoBean>> getUserInfoDetail(@QueryMap Map<String, Object> map);




    /**
     * 修改密碼
     *
     */
    String EDIT_USER_INFO ="APP/Login/EditUserInfo";
    /**
     *  修改用戶信息
     */
    String EDIT_EMPLOYEE  ="APP/Login/EditEmployee";

}
