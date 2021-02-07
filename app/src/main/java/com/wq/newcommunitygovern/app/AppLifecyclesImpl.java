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
package com.wq.newcommunitygovern.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeBack;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wq.newcommunitygovern.BuildConfig;
import com.wq.newcommunitygovern.mvp.ui.activity.main.SplashActivity;

import static com.billy.android.swipe.SwipeConsumer.DIRECTION_LEFT;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        initBaiduMap(application);
        //leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras()
                .put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ?
                        LeakCanary.install(application) : RefWatcher.DISABLED);

        /*
          侧滑界面关闭
         */
        final int edgeSize = SmartSwipe.dp2px(40, application);
        SmartSwipeBack.activityStayBack(application, activity -> {
            return !(activity instanceof SplashActivity);
        }, edgeSize, 10, DIRECTION_LEFT);
    }

    /**
     * 初始化 百度地图相关
     *
     * @param application application
     */
    private void initBaiduMap(@NonNull Application application) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(application);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
