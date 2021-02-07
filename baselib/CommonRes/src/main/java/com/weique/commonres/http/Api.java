/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weique.commonres.http;


import me.jessyan.armscomponent.commonres.BuildConfig;

/**
 * ================================================
 * CommonSDK 的 Api 可以定义公用的关于 API 的相关常量,
 * 比如说请求地址, 错误码等, 每个组件的 Api 可以定义组件自己的私有常量
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = BuildConfig.SERVER_URL;

    /**
     * 成功响应码 200 时  message  不给用户展示
     */
    public int HTTP200 = 200;
    /**
     * 成功响应码 201 时  message  给用户展示
     */
    public int HTTP201 = 201;
    /**
     * 重定向相关
     */
    public int HTTP300 = 300;
    /**
     * 客户端错误永固   不给用户展示
     */
    public int HTTP400 = 400;
    /**
     * 给用户展示
     */
    public int HTTP401 = 401;
    public int HTTP404 = 404;
    /**
     * token 验证失效 需要重新登录
     */
    public int HTTP403 = 403;
    /**
     * 签名验证失败
     */
    public int HTTP405 = 405;
    /**
     * 链接 已超时 失效
     */
    public int HTTP406 = 406;
    /**
     * 初登录外  其它接口 服务端没有token时  生成新的token  返回到 407
     */
    public int HTTP407 = 407;
    /**
     * 服务器错误
     */
    public int HTTP500 = 500;
}
