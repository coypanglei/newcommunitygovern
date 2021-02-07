package com.weique.commonres.utils.globalutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.http.Api;
import com.weique.commonres.utils.commonutils.ACache;
import com.weique.commonres.utils.commonutils.StringUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URLEncoder;

import me.jessyan.armscomponent.commonres.R;
import timber.log.Timber;


/**
 * ================================================
 * 作    者：dd
 * 版    本：1.0
 * 创建日期：18/4/25
 * 描    述：给imageview设置glide显示网络url
 * 修订历史：
 * ================================================
 *
 * @author Administrator
 */
public class GlideNewImageLoader {


    /**
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImage(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.public_null_content)
                    .error(R.drawable.public_null_content)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 不使用缓存
     *
     * @param context
     * @param imageView
     * @param imagePath
     */

    public static void displayImageNoCache(Context context, ImageView imageView, String imagePath) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.public_null_content)
                .error(R.drawable.public_null_content)
                .dontAnimate()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(imagePath)
                .apply(options)
                .into(imageView);
    }

    /**
     * 不使用缓存
     *
     * @param context
     * @param imageView
     * @param url
     */

    public static void displayImageNoCache(View context, ImageView imageView, String url) {
        url = handleUrl( url);
        RequestOptions options = new RequestOptions()
                .dontAnimate()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 不使用缓存 也没有默认
     *
     * @param context
     * @param imageView
     * @param imagePath
     */

    public static void displayImageNoCacheNoDefault(Context context, ImageView imageView, Object imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
                    .dontAnimate()
//                .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }
    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius    圆角大小
     */
    public static void loadRoundImage(Context context,int url, ImageView imageView, int radius) {
        try {
            RequestOptions requestOptions = new RequestOptions()
                    .priority(Priority.HIGH)
                    //                .placeholder(R.drawable.null_content)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transforms(new CenterCrop(), new RoundedCorners(radius));

            Glide.with(context)
                    .load(url)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius    圆角大小
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, int radius) {
        try {
            url = handleUrl( url);
            RequestOptions requestOptions = new RequestOptions()
                    .priority(Priority.HIGH)
                    //                .placeholder(R.drawable.null_content)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transforms(new CenterCrop(), new RoundedCorners(radius));

            Glide.with(context)
                    .load(url)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 统一处理 url  t添加 http 协议地址  有缓存就取缓存
     *
     * @param url
     * @return
     */
    @NotNull
    public static String handleUrl( String url) {
        try {
            if (StringUtil.isNullString(url) ||
                    StringUtil.isNotNullString(url) && url.startsWith(Constants.HTTP)) {
            } else {
                String asString = UserLoginInfoUtils.getInstance().getGlobalUrl();
                if (StringUtil.isNotNullString(asString)) {
                    if (url.startsWith("/")) {
                        url = asString + url;
                    } else {
                        url = asString + "/" + url;
                    }
                } else {
                    url = Api.APP_DOMAIN + url;
                }
            }
            url = URLEncoder.encode(url, "utf-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll(" ", "")
                    .replaceAll("%3A", ":")
                    .replaceAll("%2F", "/");
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 不使用缓存 默认头像
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImageHeadNoCache(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            if (ObjectUtils.isNotEmpty(imagePath)) {
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.public_null_content)
                        .error(R.drawable.public_null_content)
                        .dontAnimate()
//                .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(context)
                        .load(imagePath)
                        .apply(options)
                        .into(imageView);
            } else {
//                imageView.setImageResource(R.drawable.icon_head_default);
            }
        }
    }

    /**
     * 不使用缓存 默认店主头像
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayOwnerImageHeadNoCache(Context context, ImageView imageView, String imagePath, int error) {
        if (context != null) {
            if (ObjectUtils.isNotEmpty(imagePath)) {
                RequestOptions options = new RequestOptions()
                        .placeholder(error)//
                        .error(error)//
                        .dontAnimate()
//                .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(context)
                        .load(imagePath)
                        .apply(options)
                        .into(imageView);
            } else {
                imageView.setImageResource(error);
            }
        }
    }

    /**
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void displayImageNoDefault(Context context, ImageView imageView, String imagePath) {
        if (context != null) {
            RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.ico_default_icon)//
//                .error(R.mipmap.ico_default_icon)//
                    .dontAnimate()
//                .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context)
                    .load(imagePath)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * @param context
     * @param imageView
     * @param outputUri
     */
    public static void displayImage(Context context, ImageView imageView, Uri outputUri) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.public_null_content)//
                .error(R.drawable.public_null_content)//
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(outputUri)
                .apply(options)
                .into(imageView);
    }


    //在不加载图片情况下获取图片大小
    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new int[]{options.outWidth, options.outHeight};
    }

    /**
     * 清除图片磁盘缓存
     */
    private static void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(() -> Glide.get(context).clearDiskCache()).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    private static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除图片所有缓存
     */
    public static void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        String ImageExternalCatchDir = context.getExternalCacheDir() + ExternalPreferredCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        deleteFolderFile(ImageExternalCatchDir, true);
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    private static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
