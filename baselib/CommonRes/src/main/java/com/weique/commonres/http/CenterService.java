package com.weique.commonres.http;

import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.CommonTitleBean;
import com.weique.commonres.entity.CommonBackBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface CenterService {
    String path = "app/";
    /**
     * 通用 post 请求
     */
    @FormUrlEncoded
    @POST("{path}")
    Observable<BaseResponse<Object>> putCommonList(@Path(value = "path", encoded = true) String url, @FieldMap Map<String, Object> map);




    /**
     * 根据用户id 获取相同列表返回数据
     *
     * @param map map
     * @return Observable
     */
    @GET("{path}")
    Observable<BaseResponse<CommonBackBean>> getCommonObject(@Path(value = "path", encoded = true) String url, @QueryMap Map<String, Object> map);



    /**
     * 获取枚举数据列表
     *
     * @param paramSign paramSign
     * @return Observable
     */
    @GET(path + "Common/GetEnums")
    Observable<BaseResponse<List<CommonTitleBean>>> getTitles(@QueryMap Map<String, Object> paramSign);

}
