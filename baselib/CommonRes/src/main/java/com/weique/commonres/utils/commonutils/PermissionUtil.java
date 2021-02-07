/*
 * Copyright 2017 JessYan
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
package com.weique.commonres.utils.commonutils;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;

import androidx.fragment.app.FragmentActivity;

import com.jess.arms.utils.ArmsUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

/**
 * ================================================
 * 权限请求工具类
 * <p>提供三种 回调方式
 * 1.RequestPermissionOnlySuccess 不管用户拒绝 同意 都正常通过 会提醒用户
 * 1.RequestPermission 不处理 用户的同意或拒绝 调用者自己处理
 * 1.RequestPermissionSuspendable 有成功和中断 两个回调
 * ================================================
 *
 * @author Administrator
 */
public class PermissionUtil {
    public static final String TAG = "Permission";

    private PermissionUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 不管是拒绝还是同意 都不影响 流程
     *
     * @param activity          activity
     * @param requestPermission requestPermission
     * @param errorHandler      errorHandler
     * @param permissions       permissions
     */
    public static void requestPermissionOlySuccess(Activity activity,
                                                   RequestPermissionOnlySuccess requestPermission,
                                                   RxErrorHandler errorHandler,
                                                   String... permissions) {
        try {
            if (permissions == null || permissions.length == 0) {
                return;
            }
            RxPermissions rxPermissions = new RxPermissions((FragmentActivity) activity);
            List<String> needRequest = new ArrayList<>();
            for (String permission : permissions) {
                if (!rxPermissions.isGranted(permission)) {
                    needRequest.add(permission);
                }
            }

            if (needRequest.isEmpty()) {
                requestPermission.onRequestPermissionSuccess();
            } else {//没有申请过,则开始申请
                rxPermissions
                        .requestEach(needRequest.toArray(new String[0]))
                        .buffer(permissions.length)
                        .subscribe(new ErrorHandleSubscriber<List<Permission>>(errorHandler) {
                            @Override
                            public void onNext(@NonNull List<Permission> permissions) {
                                try {
                                    List<String> failurePermissions = new ArrayList<>();
                                    List<String> askNeverAgainPermissions = new ArrayList<>();
                                    for (Permission p : permissions) {
                                        if (!p.granted) {
                                            if (p.shouldShowRequestPermissionRationale) {
                                                failurePermissions.add(p.name);
                                            } else {
                                                askNeverAgainPermissions.add(p.name);
                                            }
                                        }
                                    }
                                    if (failurePermissions.size() > 0) {
                                        new CommonDialog.Builder(activity).setTitle("注意")
                                                .setContent("您拒绝了部分授权,部分功能无法正常使用")
                                                .setPositiveButton("知道了", (v, commonDialog) -> {
                                                })
                                                .setNegativeBtnShow(false).create().show();
                                    }

                                    if (askNeverAgainPermissions.size() > 0) {
                                        new CommonDialog.Builder(activity).setTitle("注意")
                                                .setContent("您拒绝了部分授权,并选择不在询问,部分功能无法正常使用")
                                                .setPositiveButton("去设置", (v, commonDialog) -> {
                                                    String brand = Build.BRAND;//手机厂商
                                                    if (TextUtils.equals(brand.toLowerCase(), "redmi") ||
                                                            TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
                                                        PhoneInfoUtil.gotoMiuiPermission(activity);
                                                    } else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
                                                        PhoneInfoUtil.gotoMeizuPermission(activity);
                                                    } else if (TextUtils.equals(brand.toLowerCase(), "huawei") || TextUtils.equals(brand.toLowerCase(), "honor")) {
                                                        PhoneInfoUtil.gotoHuaweiPermission(activity);
                                                    } else {
                                                        ArmsUtils.startActivity(PhoneInfoUtil.getAppDetailSettingIntent(activity));
                                                    }
                                                }).setNegativeButton("知道了", (v, commonDialog) -> {
                                        }).create().show();
                                    }

                                    if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
                                        Timber.tag(TAG).d("Request permissions success");
                                        requestPermission.onRequestPermissionSuccess();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 拒绝权限时会有 中止回调
     *
     * @param requestPermission
     * @param rxPermissions
     * @param errorHandler
     * @param permissions
     */
    public static void requestPermissionSuspendable(Activity activity,
                                                    RequestPermissionSuspendable requestPermission,
                                                    RxPermissions rxPermissions,
                                                    RxErrorHandler errorHandler,
                                                    String... permissions) {
        try {
            if (permissions == null || permissions.length == 0) {
                return;
            }

            List<String> needRequest = new ArrayList<>();
            for (String permission : permissions) { //过滤调已经申请过的权限
                if (!rxPermissions.isGranted(permission)) {
                    needRequest.add(permission);
                }
            }

            if (needRequest.isEmpty()) {//全部权限都已经申请过，直接执行操作
                requestPermission.onRequestPermissionSuccess();
            } else {//没有申请过,则开始申请
                rxPermissions
                        .requestEach(needRequest.toArray(new String[0]))
                        .buffer(permissions.length)
                        .subscribe(new ErrorHandleSubscriber<List<Permission>>(errorHandler) {
                            @Override
                            public void onNext(@NonNull List<Permission> permissions) {
                                try {
                                    List<String> failurePermissions = new ArrayList<>();
                                    List<String> askNeverAgainPermissions = new ArrayList<>();
                                    for (Permission p : permissions) {
                                        if (!p.granted) {
                                            if (p.shouldShowRequestPermissionRationale) {
                                                failurePermissions.add(p.name);
                                            } else {
                                                askNeverAgainPermissions.add(p.name);
                                            }
                                        }
                                    }
                                    if (failurePermissions.size() > 0) {
                                        new CommonDialog.Builder(activity).setTitle("注意")
                                                .setContent("您拒绝了权限，无法使用当前功能")
                                                .setPositiveButton("知道了", (v, commonDialog) -> {
                                                    requestPermission.onRequestPermissionFailureDiscontinue(failurePermissions);
                                                })
                                                .setNegativeBtnShow(false).create().show();
                                    }
                                    if (askNeverAgainPermissions.size() > 0) {
                                        new CommonDialog.Builder(activity).setTitle("注意")
                                                .setContent("您拒绝了访问授权,并选择不在询问,请前往设置")
                                                .setPositiveButton("去设置", (v, commonDialog) -> {
                                                    String brand = Build.BRAND;//手机厂商
                                                    if (TextUtils.equals(brand.toLowerCase(), "redmi") ||
                                                            TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
                                                        PhoneInfoUtil.gotoMiuiPermission(activity);
                                                    } else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
                                                        PhoneInfoUtil.gotoMeizuPermission(activity);
                                                    } else if (TextUtils.equals(brand.toLowerCase(), "huawei") ||
                                                            TextUtils.equals(brand.toLowerCase(), "honor")) {
                                                        PhoneInfoUtil.gotoHuaweiPermission(activity);
                                                    } else {
                                                        ArmsUtils.startActivity(PhoneInfoUtil.getAppDetailSettingIntent(activity));
                                                    }
                                                }).setNegativeButton("知道了", (v, commonDialog) -> {
                                            requestPermission.onRequestPermissionFailureDiscontinue(failurePermissions);
                                        }).create().show();
                                    }
                                    if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
                                        Timber.tag(TAG).d("Request permissions success");
                                        requestPermission.onRequestPermissionSuccess();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 自己处理回调
     *
     * @param requestPermission
     * @param rxPermissions
     * @param errorHandler
     * @param permissions
     */
    public static void RequestPermission(RequestPermission requestPermission,
                                         RxPermissions rxPermissions,
                                         RxErrorHandler errorHandler,
                                         String... permissions) {
        try {
            if (permissions == null || permissions.length == 0) {
                return;
            }
            List<String> needRequest = new ArrayList<>();
            for (String permission : permissions) { //过滤调已经申请过的权限
                if (!rxPermissions.isGranted(permission)) {
                    needRequest.add(permission);
                }
            }

            if (needRequest.isEmpty()) {//全部权限都已经申请过，直接执行操作
                requestPermission.onRequestPermissionSuccess();
            } else {//没有申请过,则开始申请
                rxPermissions
                        .requestEach(needRequest.toArray(new String[0]))
                        .buffer(permissions.length)
                        .subscribe(new ErrorHandleSubscriber<List<Permission>>(errorHandler) {
                            @Override
                            public void onNext(@NonNull List<Permission> permissions) {
                                try {
                                    List<String> failurePermissions = new ArrayList<>();
                                    List<String> askNeverAgainPermissions = new ArrayList<>();
                                    for (Permission p : permissions) {
                                        if (!p.granted) {
                                            if (p.shouldShowRequestPermissionRationale) {
                                                failurePermissions.add(p.name);
                                            } else {
                                                askNeverAgainPermissions.add(p.name);
                                            }
                                        }
                                    }
                                    if (failurePermissions.size() > 0) {
                                        requestPermission.onRequestPermissionFailure(failurePermissions);
                                    }

                                    if (askNeverAgainPermissions.size() > 0) {
                                        requestPermission.onRequestPermissionFailureWithAskNeverAgain(askNeverAgainPermissions);
                                    }

                                    if (failurePermissions.size() == 0 && askNeverAgainPermissions.size() == 0) {
                                        requestPermission.onRequestPermissionSuccess();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 不管同意或是拒绝 都直接走下去
     */
    public interface RequestPermissionOnlySuccess {

        /**
         * 权限请求成功
         */
        void onRequestPermissionSuccess();

    }

    /**
     * 自己处理 权限的 回调
     */
    public interface RequestPermission {

        /**
         * 权限请求成功
         */
        void onRequestPermissionSuccess();

        /**
         * 用户拒绝了权限请求, 权限请求失败, 但还可以继续请求该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailure(List<String> permissions);

        /**
         * 用户拒绝了权限请求并且用户选择了以后不再询问, 权限请求失败, 这时将不能继续请求该权限, 需要提示用户进入设置页面打开该权限
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions);
    }

    public interface RequestPermissionSuspendable {

        /**
         * 权限请求成功
         */
        void onRequestPermissionSuccess();

        /**
         * 获取权限失败 并中断
         *
         * @param permissions 请求失败的权限名
         */
        void onRequestPermissionFailureDiscontinue(List<String> permissions);
    }
}

