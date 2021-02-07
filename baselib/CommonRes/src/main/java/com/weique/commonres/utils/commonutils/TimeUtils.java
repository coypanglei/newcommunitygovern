package com.weique.commonres.utils.commonutils;

import android.annotation.SuppressLint;

import com.weique.commonres.utils.BaseSingleton;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author : GreatKing
 * e-mail : 435136054@163.com
 * @date : 2020/5/11  15:35
 * desc   : 时间工具类
 * version:
 */
public class TimeUtils extends BaseSingleton {
    /**
     * 实例化
     */
    public static TimeUtils getInstance() {
        return getSingleton(TimeUtils.class);
    }

    /**
     * 倒计时
     *
     * @param startTime 初始化的事件
     * @return 被观察者
     */
    @SuppressLint("CheckResult")
    public Observable<Long> downTime(Long startTime) {
        return Observable.create(emitter -> {
            try {
                Flowable.intervalRange(0, startTime, 1, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(aLong -> emitter.onNext(startTime - 1 - aLong))
                        .doOnComplete(() -> {
                            //倒计时完毕置为可点击状态
                        }).subscribe();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
