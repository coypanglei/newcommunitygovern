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
package com.weique.commonres.constans;

import android.Manifest;
import android.os.Environment;

import androidx.annotation.IntDef;

import com.weique.commonservice.zongzhi.bean.UserInfoBean;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ================================================
 * CommonSDK 的 Constants 可以定义公用的常量, 比如关于业务的常量或者正则表达式, 每个组件的 Constants 可以定义组件自己的私有常量
 * ================================================
 *
 * @author Administrator
 */
public class Constants {
    /**
     * 电话号码 正则
     */
    public String PHONE_REGULAR = "^1[3-9]\\d{9}$";


    /**
     * 是否是第一次打开
     */
    public static final String IS_FIRST_OPEN = "IS_FIRST_OPEN";

    /**
     * 文件后缀
     */
    public static final String MP3 = "mp3";
    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String MP4 = "mp4";
    public static final String _MP4 = ".mp4";
    public static final String RMVB = "rmvb";
    public static final String PNG = "png";
    public static final String JPG = "jpg";
    public static final String GIF = "gif";
    public static final String JPEG = "jpeg";
    public static final String APK = ".apk";
    public static final String HTTP_HEAD = "<head>" +
            "<meta name=viewport content=width=device-width, initial-scale=1.0, user-scalable=no> " +
            "<style>img{max-width: 100%; width:auto; height:auto!important;}</style>" +
            "<style>video{max-width: 100%;max-height: 35%;}</style>" +
            "<style>p{letter-spacing: 3px !important;line-height:28px " +
            "!important;font-family:Source Han Serif SC;color:rgba(51,51,51,1);font-size:16}</style>" +
            "</head>";

    public static final String YM = "yyyy/MM";
    public static final String YM1 = "yyyy-MM";
    public static final String YMD = "yyyy/MM/dd";
    public static final String YMD1 = "yyyy-MM-dd";
    public static final String YMDH = "yyyy/MM/dd HH";
    public static final String YMDH1 = "yyyy-MM-dd HH";
    public static final String YMDHM = "yyyy/MM/dd HH:mm";
    public static final String YMDHM1 = "yyyy-MM-dd HH:mm";
    public static final String YMDHMS = "yyyy/MM/dd HH:mm:ss";
    public static final String YMDHMS1 = "yyyy-MM-dd HH:mm:ss";

    public static final String Y = "yyyy";

    public static final String M = "MM";
    /**
     * 身份证位数
     */
    public static final int ID_NUMBER = 18;


    /**
     * 隐私政策
     */
    public static final String PRIVACY_POLICY = "/APP/ProtocolForApp/PrivacyProtocolIndex";
    /**
     * 用户协议
     */
    public static final String USER_AGREEMENT = "/APP/ProtocolForApp/UserAgreementIndex";


    /**
     * apk文件路径
     */
    /**
     * 最新版本文件
     *
     * @param versionName versionName
     * @return File
     */
    public static File getNewVersionApkFile(String versionName) {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "politics",
                "politics-lean" + "-" + versionName + APK);
    }

    public static UserInfoBean userinfo = null;

    /**
     * 公共参数
     */
    public static final String TOKEN = "token";
    public static final String VERSION = "VERSION";
    public static final String USER_ID = "UserId";
    public static final String TIMESTAMP = "timestamp";
    public static final String NONCE = "nonce";
    public static final String SIGNATURE = "signature";
    public static final String DEPARTMENT_ID = "DepartmentId";

    /**
     * 下载所需权限
     */
    public static final String[] READ_WRITE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };




    /**
     * 提示框子
     */
    public final static String NOTICE_ITEM = "notice_item";


    /**
     * 图片添加集合
     */
    public final static String IMG_ADD_LIST_ITEM = "img_add_list_item";

    /**
     * 通用采集object
     */
    public static final String COMMON_COLLECTION = "common_collection";


    /**
     * 添加框类型
     */
    public static final int ADD_IMG = 0;
    /**
     * 图片框类型
     */
    public static final int IS_DRAWABLE = 1;
    /**
     * 删除类型
     */
    public static final int IS_VIS_DELETE = 2;


    /**
     * 动态URL
     */
    public static String DYNAMIC_URL = "DYNAMIC_URL";


    /**
     * 读写 相机 录音
     */
    public static final String[] PERMISSIONS_LIST_READ_WRITE_CAMERA = {
//            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.CAPTURE_VIDEO_OUTPUT,
            Manifest.permission.CAMERA
    };
}
