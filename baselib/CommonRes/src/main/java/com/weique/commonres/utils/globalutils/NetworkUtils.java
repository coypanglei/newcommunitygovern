package com.weique.commonres.utils.globalutils;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.http.Api;
import com.weique.commonres.utils.commonutils.StringUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.armscomponent.commonres.BuildConfig;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;
import razerdp.basepopup.BasePopupWindow;

/**
 * @author GK
 * @description:
 * @date :2020/8/16 10:23
 */
public class NetworkUtils {

    /**
     * 每页条数
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 页码
     */
    public static int PAGE_NUMBER = 0;
    /**
     * 界面中有两个  需要上拉加载更多的list
     * 注意第二个列表请求时 要判断 使用这个值
     */
    public static int pageNumberSecond = 1;


    /**
     * IgnoreNumber
     */
    public static final String IGNORE_NUMBER = "ignoreNumber";

    /**
     * PageSize
     */
    public static final String Page_Size = "pageSize";


    /**
     * 进度监控处理  回调
     *
     * @param <T>
     */
    public interface ProgressMonitorHandle<T> {
        /**
         * 从 响应中 获取body 返还界面
         *
         * @param t body
         */
        void getBodyFromObject(T t);
    }

    /**
     * 通用网络请求  没有分页
     *
     * @param observable observable
     * @param handle     handle
     * @param <T>        <T>
     */
    public static <T> void commonGetNetworkDataNoLoading(@NotNull Observable<BaseResponse<T>> observable,
                                                         RxErrorHandler mErrorHandler,
                                                         IView mRootView,
                                                         @Nullable ProgressMonitorHandle<T> handle) {
        try {
            observable.subscribeOn(Schedulers.io())
                    //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                    .retryWhen(new RetryWithDelay(0, 10))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    //使用Rxlifecycle,使Disposable和Activity一起销毁
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new ErrorHandleSubscriber<BaseResponse<T>>(mErrorHandler) {
                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                        }

                        @Override
                        public void onNext(BaseResponse<T> bean) {
                            try {
                                mRootView.hideLoading();
                                if (bean.getCode() >= Api.HTTP200 && bean.getCode() < Api.HTTP300) {
                                    if (bean.getCode() == Api.HTTP201 && StringUtil.isNotNullString(bean.getMessage())) {
                                        ArmsUtils.makeText(bean.getMessage());
                                    }
                                    if (handle != null) {
                                        handle.getBodyFromObject(bean.getData());
                                    }
                                } else if (bean.getCode() >= Api.HTTP400 && bean.getCode() < Api.HTTP500) {
                                    handle400to500(bean.getCode(), bean.getMessage(), mRootView);
                                } else {
                                    handleOtherCode(mRootView);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通用网络请求  没有分页
     *
     * @param observable observable
     * @param handle     handle
     * @param <T>        <T>
     */
    public static <T> void commonGetNetworkData(@NotNull Observable<BaseResponse<T>> observable,
                                                RxErrorHandler mErrorHandler,
                                                IView mRootView,
                                                @Nullable ProgressMonitorHandle<T> handle,boolean showLoading) {
        try {
            observable.subscribeOn(Schedulers.io())
                    //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                    .retryWhen(new RetryWithDelay(0, 10))
                    .doOnSubscribe(disposable -> mRootView.showLoading(showLoading))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(mRootView::hideLoading)
                    //使用Rxlifecycle,使Disposable和Activity一起销毁
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new ErrorHandleSubscriber<BaseResponse<T>>(mErrorHandler) {
                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            mRootView.hideLoading();
                        }

                        @Override
                        public void onNext(BaseResponse<T> bean) {
                            try {
                                mRootView.hideLoading();
                                if (bean.getCode() >= Api.HTTP200 && bean.getCode() < Api.HTTP300) {
                                    if (bean.getCode() == Api.HTTP201 && StringUtil.isNotNullString(bean.getMessage())) {
                                        ArmsUtils.makeText(bean.getMessage());
                                    }
                                    if (handle != null) {
                                        handle.getBodyFromObject(bean.getData());
                                    }
                                } else if (bean.getCode() >= Api.HTTP400 && bean.getCode() < Api.HTTP500) {
                                    handle400to500(bean.getCode(), bean.getMessage(), mRootView);
                                } else {
                                    handleOtherCode(mRootView);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 通用网络请求  没有分页
//     *
//     * @param observable observable
//     * @param handle     handle
//     * @param <T>        <T>
//     */
//    public static <T> void commonGetNetworkData(@NotNull Observable<BaseResponse<T>> observable,
//                                                RxErrorHandler mErrorHandler,
//                                                BasePopupWindow basePopupWindow,
//                                                @Nullable ProgressMonitorHandle<T> handle,boolean showLoading) {
//        try {
//            observable.subscribeOn(Schedulers.io())
//                    //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
//                    .retryWhen(new RetryWithDelay(0, 10))
//                    .doOnSubscribe(disposable -> mRootView.showLoading(showLoading))
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doAfterTerminate(mRootView::hideLoading)
//                    //使用Rxlifecycle,使Disposable和Activity一起销毁
//                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
//                    .subscribe(new ErrorHandleSubscriber<BaseResponse<T>>(mErrorHandler) {
//                        @Override
//                        public void onError(Throwable t) {
//                            super.onError(t);
//                            mRootView.hideLoading();
//                        }
//
//                        @Override
//                        public void onNext(BaseResponse<T> bean) {
//                            try {
//                                mRootView.hideLoading();
//                                if (bean.getCode() >= Api.HTTP200 && bean.getCode() < Api.HTTP300) {
//                                    if (bean.getCode() == Api.HTTP201 && StringUtil.isNotNullString(bean.getMessage())) {
//                                        ArmsUtils.makeText(bean.getMessage());
//                                    }
//                                    if (handle != null) {
//                                        handle.getBodyFromObject(bean.getData());
//                                    }
//                                } else if (bean.getCode() >= Api.HTTP400 && bean.getCode() < Api.HTTP500) {
//                                    handle400to500(bean.getCode(), bean.getMessage(), mRootView);
//                                } else {
//                                    handleOtherCode(mRootView);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



    /**
     * 通用网络请求  有第二个分页 调用这个方法 再传分也是要用pageNumberSecond  作为页码
     *
     * @param observable observable
     * @param handle     handle
     * @param <T>        <T>
     */
    public static <T> void commonGetNetworkDataWithSecondList(@NotNull Observable<BaseResponse<T>> observable,
                                                              RxErrorHandler mErrorHandler,
                                                              IView mRootView,
                                                              @Nullable ProgressMonitorHandle<T> handle,boolean showLoading) {
        try {
            observable.subscribeOn(Schedulers.io())
                    //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                    .retryWhen(new RetryWithDelay(1, 1))
                    .doOnSubscribe(disposable -> mRootView.showLoading(showLoading))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(mRootView::hideLoading)
                    //使用Rxlifecycle,使Disposable和Activity一起销毁
                    .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                    .subscribe(new ErrorHandleSubscriber<BaseResponse<T>>(mErrorHandler) {
                        @Override
                        public void onError(Throwable t) {
                            super.onError(t);
                            mRootView.hideLoading();
                        }

                        @Override
                        public void onNext(BaseResponse<T> bean) {
                            try {
                                mRootView.hideLoading();
                                if (bean.getCode() >= Api.HTTP200 && bean.getCode() < Api.HTTP300) {
                                    if (bean.getCode() == Api.HTTP201 && StringUtil.isNotNullString(bean.getMessage())) {
                                        ArmsUtils.makeText(bean.getMessage());
                                    }
                                    if (handle != null) {
                                        handle.getBodyFromObject(bean.getData());
                                    }
                                } else if (bean.getCode() >= Api.HTTP400 && bean.getCode() < Api.HTTP500) {
                                    handle400to500(bean.getCode(), bean.getMessage(), mRootView);
                                } else {
                                    handleOtherCode(mRootView);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理其它code
     */
    private static void handleOtherCode(IView mRootView) {
        ArmsUtils.makeText("请求异常 - 请联系服务人员");
        mRootView.loadMoreEnd();
    }

    /**
     * 处理400 - 500  之间 的code
     *
     * @param code    code
     * @param message message
     */
    private static void handle400to500(int code, String message, IView mRootView) {
        if ((code == Api.HTTP400 || code == Api.HTTP401) && StringUtil.isNotNullString(message)) {
            ArmsUtils.makeText(message);
        } else if (code == Api.HTTP403) {
            ArmsUtils.makeText("您的账号在其它设备上登录，请重新登录");
        } else if (code == Api.HTTP405) {
            if (BuildConfig.LOG_DEBUG) {
                ArmsUtils.makeText("签名验证失败");
            } else {
                ArmsUtils.makeText("请求异常");
            }
        } else if (code == Api.HTTP406) {
            if (BuildConfig.LOG_DEBUG) {
                ArmsUtils.makeText("请求已超时");
            } else {
                ArmsUtils.makeText("请求异常");
            }
        } else {

            ArmsUtils.makeText("请求异常 - 请联系服务人员");

        }
        mRootView.loadMoreEnd();
    }

    /**
     * 处理分页数据
     *
     * @param pullToRefresh pullToRefresh 是否是下拉刷新(即：第一次加载列表)  false 是加载更多
     * @param isSecondList  是第二个 列表 有时一个界面中会有两个 recyclerView
     */
    public static int handlePaging(boolean pullToRefresh, boolean isSecondList) {
        if (pullToRefresh && !isSecondList) {
            return PAGE_NUMBER = 0;
        } else if (!pullToRefresh && !isSecondList) {
            return PAGE_NUMBER += 1;
        } else if (pullToRefresh && isSecondList) {
            return pageNumberSecond = 1;
        } else if (!pullToRefresh && isSecondList) {
            return pageNumberSecond += 1;
        }
        return 0;
    }

    /**
     * 处理加载更多 view 隐藏
     *
     * @param dataSize  数据
     * @param mRootView mRootView
     */
    public static void handleLoadMore(List dataSize, IView mRootView) {
        if (dataSize == null || PAGE_SIZE > dataSize.size()) {
            mRootView.loadMoreEnd();
        } else {
            mRootView.loadMoreComplete();
        }
    }
}
