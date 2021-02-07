package com.weique.commonres.service;

import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.QuestionBean;
import com.weique.commonres.base.commonbean.UploadFileRsponseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author GK
 * @description:
 * @date :2020/8/31 14:29
 */
public interface CommonRequestService {
    /**
     * 上传文件
     *
     * @param multipartBody multipartBody
     * @return Observable
     */
    @POST("APP/Common/UploadFileForApp")
    Observable<BaseResponse<List<UploadFileRsponseBean>>> uploadFile(@Body MultipartBody multipartBody);


    /**
     * 公共请求
     *
     * @param url url
     * @param map map
     * @return Observable
     */
    @FormUrlEncoded
    @POST("{path}")
    Observable<BaseResponse<QuestionBean>> commonRequestGet(@Path(value = "path",encoded = true) String url, @FieldMap Map<String, Object> map);
}
