package com.weique.commonres.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.text.NoCopySpan;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.core.RouterHub;
import com.weique.commonres.utils.commonutils.ARouterUtils;
import com.weique.commonres.utils.globalutils.GlideNewImageLoader;
import com.weique.commonres.utils.globalutils.UserInfoUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import io.reactivex.Observable;

public class AppUtils {


    /**
     * 退出登录
     */
    public static void logout(Context context) {
        try {
            UserInfoUtils.getInstance().loginOut();
            GlideNewImageLoader.clearImageAllCache(AppManager.getAppManager().getApplication());
            ARouterUtils.navigation(context, RouterHub.APP_LOGIN_ACTIVITY, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ClickableSpan 产生的 泄露  解决  不适用
     */
    public static class NoRefCopySpan extends ClickableSpan implements NoCopySpan {

        @Override
        public void onClick(@NonNull View widget) {

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
        }

    }

    /**
     * 创建二维码
     * @param url 网址
     * @return 图片
     */
    private Bitmap createQRcodeImage(String url) {

        int w = 250;
        int h = 250;
        Bitmap bitmap = null;
//        try {
//            //判断URL合法性
//            if (url == null || "".equals(url) || url.length() < 1) {
//                ArmsUtils.makeText("网址不合法");
//                return null;
//            }
//            Hashtable<EncodeHintType, String> hints = new Hashtable<>();
//            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            //图像数据转换，使用了矩阵转换
//            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints);
//            int[] pixels = new int[w * h];
//            //下面这里按照二维码的算法，逐个生成二维码的图片，
//            //两个for循环是图片横列扫描的结果
//            for (int y = 0; y < h; y++) {
//                for (int x = 0; x < w; x++) {
//                    if (bitMatrix.get(x, y)) {
//                        pixels[y * w + x] = 0xff000000;
//                    } else {
//                        pixels[y * w + x] = 0xffffffff;
//                    }
//                }
//            }
//            //生成二维码图片的格式，使用ARGB_8888
//            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
//            //显示到我们的ImageView上面
//            return bitmap;
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
        return null;
    }


    /**
     * 判断 url地址是否可以访问
     *
     * @param address address
     * @return Boolean
     */
    public static Observable<Boolean> checkUrl(String address) {
        return Observable.create(emitter -> {
            try {
                URL url = new URL(address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false);
                conn.setInstanceFollowRedirects(true);
                conn.setConnectTimeout(15 * 1000);
                conn.setReadTimeout(15 * 1000);
                conn.connect();
                int code = conn.getResponseCode();
                if ((code >= 100) && (code < 400)) {
                    emitter.onNext(true);
                } else {
                    emitter.onNext(false);
                }
                conn.disconnect();
                conn = null;
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onNext(false);
            }
        });
    }

    /**
     * 判断定位服务是否开启
     *
     * @param
     * @return true 表示开启
     */
    public static boolean isLocationEnabled(Context context) {
        try {
            LocationManager locationManager
                    = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            return gps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}

