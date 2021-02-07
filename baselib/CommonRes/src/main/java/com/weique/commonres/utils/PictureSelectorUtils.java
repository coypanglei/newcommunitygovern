package com.weique.commonres.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.List;

import me.jessyan.armscomponent.commonres.R;


/**
 * 跳转到相册 或 相机
 *
 * @author Administrator
 */
public class PictureSelectorUtils {
    /**
     * 跳转到 相册界面 需要  控制的 属性 自己拉到 参数中 或再创建一个 静态方法
     *
     * @param activity                 activity
     * @param pictureMimeType          先择的资源类型  全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
     * @param max                      max  最大数量
     * @param previewVideo             是否可预览video
     * @param enableCrop               是否可裁剪
     * @param medias                   已选择的图片列表
     * @param onResultCallbackListener onResultCallbackListener
     */
    public static void gotoPhoto(Activity activity,
                                 int pictureMimeType,
                                 int max,
                                 boolean previewVideo,
                                 boolean enableCrop,
                                 List<LocalMedia> medias,
                                 OnResultCallbackListener onResultCallbackListener) {
        try {
            PictureSelector.create(activity)
                    //全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .openGallery(pictureMimeType)
                    //主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .theme(R.style.picture_default_style)
                    // 动态自定义相册主题  注意：此方法最好不要与.theme();同时存在， 二选一
                    .setPictureStyle(SelectStyleUtil.getDefaultStyle(activity))
                    // 动态自定义裁剪主题
                    .setPictureCropStyle(SelectStyleUtil.getPictureCropParameterStyle(activity,
                            SelectStyleUtil.getDefaultStyle(activity)))
                    // 自定义相册启动退出动画
                    .setPictureWindowAnimationStyle(new PictureWindowAnimationStyle())
                    // 外部传入图片加载引擎，必传项   参考Demo MainActivity中代码
                    .imageEngine(GlideEngine.createGlideEngine())
                    // 图片和视频是否可以同选,只在ofAll模式下有效
                    .isWithVideoImage(false)
                    // 是否使用自定义相机，5.0以下请不要使用，可能会出现兼容性问题
                    .isUseCustomCamera(false)
                    // 设置相册Activity方向，不设置默认使用系统
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    // 是否显示原图控制按钮，如果用户勾选了 压缩、裁剪功能将会失效
                    .isOriginalImageControl(true)
                    // 是否开启微信图片选择风格，此开关开启了才可使用微信主题！！！
                    .isWeChatStyle(true)
                    // 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); &&
                    .isAndroidQTransform(false)
                    //有效
                    .isEnableCrop(enableCrop)
                    // 自定义播放回调控制，用户可以使用自己的视频播放界面
                    //.bindCustomPlayVideoCallback(callback)
                    // 多图裁剪时是否支持跳过，默认支持
                    .isMultipleSkipCrop(false)
                    // 多图裁剪底部列表显示动画效果
                    .isMultipleRecyclerAnimation(false)
                    // 设置语言，默认中文
                    //.setLanguage(language)
                    // 最大图片选择数量 int
                    .maxSelectNum(max)
                    // 最小选择数量 int
                    .minSelectNum(1)
                    // 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
                    .minVideoSelectNum(1)
                    // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
                    .maxVideoSelectNum(max)
                    // 每行显示个数 int
                    .imageSpanCount(4)
                    // 未选择数据时点击按钮是否可以返回
                    .isReturnEmpty(true)
                    // 预览图片长按是否可以下载
                    .isNotPreviewDownload(false)
                    // 只查多少M以内的图片、视频、音频  单位M
                    .queryMaxFileSize(25)
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                    //.cameraFileName("test.png") // 重命名拍照文件名、注意这个只在使用相机时可以使用
                    //.renameCompressFile("test.png")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                    //.renameCropFileName("test.png")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                    //.isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    //.setTitleBarBackgroundColor()//相册标题栏背景色
                    //.isChangeStatusBarFontColor()// 是否关闭白色状态栏字体颜色
                    //.setStatusBarColorPrimaryDark()// 状态栏背景色
                    //.setUpArrowDrawable()// 设置标题栏右侧箭头图标
                    //.setDownArrowDrawable()// 设置标题栏右侧箭头图标
                    //.isOpenStyleCheckNumMode()// 是否开启数字选择模式 类似QQ相册
                    // 多选 or 单选 PictureCo.036nfig.MULTIPLE or PictureConfig.SINGLE
                    .selectionMode(PictureConfig.MULTIPLE)
                    // 是否可预览图片 true or false
                    .isPreviewImage(true)
                    // 是否可预览视频 true or false
                    .isPreviewVideo(previewVideo)
                    // 是否可播放音频 true or false
                    .isEnablePreviewAudio(previewVideo)
                    // 是否显示拍照按钮 true or false
                    .isCamera(true)
                    // 拍照保存图片格式后缀,默认jpeg
                    .imageFormat(PictureMimeType.PNG)
                    // 图片列表点击 缩放效果 默认true
                    .isZoomAnim(true)
                    // glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    //.sizeMultiplier(0.5f)
                    // 设置圆形裁剪背景色值
                    //.setCircleDimmedColor()
                    // 设置圆形裁剪边框色值
                    //.setCircleDimmedBorderColor()
                    // 设置圆形裁剪边框粗细
                    //.setCircleStrokeWidth(3)
                    // 是否压缩 true or false
                    .isCompress(true)
                    // int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    //.glideOverride(400, 400)
                    // int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .withAspectRatio(1, 1)
                    // 是否显示uCrop工具栏，默认不显示 true or false
                    .hideBottomControls(true)
                    // 是否显示gif图片 true or false
                    .isGif(false)
                    //压缩图片保存地址
                    //.compressSavePath(getPath())
                    // 裁剪框是否可拖拽 true or false
                    .freeStyleCropEnabled(true)
                    // 是否圆形裁剪 true or false
                    .circleDimmedLayer(false)
                    // 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropFrame(false)
                    // 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .showCropGrid(false)
                    // 是否开启点击声音 true or false
                    .isOpenClickSound(false)
                    // // 是否传入已选图片 List<LocalMedia> list
                    .selectionData(medias)
                    // 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                    .isPreviewEggs(true)
                    // 废弃 改用cutOutQuality()
                    //.cropCompressQuality(90)
                    // 裁剪输出质量 默认100
                    .cutOutQuality(80)
                    .compressQuality(60)
                    // 小于100kb的图片不压缩
                    .minimumCompressSize(100)
                    //同步true或异步false 压缩 默认同步
                    .synOrAsy(true)
                    // 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.cropImageWideHigh(1,1)
                    // 裁剪是否可旋转图片 true or false
                    //.rotateEnabled(true)
                    // 裁剪是否可放大缩小图片 true or false
                    //.scaleEnabled(true)
                    // 视频录制质量 0 or 1 int
                    .videoQuality(1)
                    // 显示多少秒以内的视频or音频也可适用 int
                    .videoMaxSecond(20)
                    // 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(5)
                    //视频秒数录制 默认60s int
                    .recordVideoSecond(19)
                    // 是否可拖动裁剪框(固定)
                    .isDragFrame(false)
                    .forResult(PictureConfig.CHOOSE_REQUEST, onResultCallbackListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单独拍照
     *
     * @param activity                 activity
     * @param onResultCallbackListener onResultCallbackListener
     */
    public static void justTakePhotos(Activity activity,
                                      OnResultCallbackListener onResultCallbackListener) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .forResult(onResultCallbackListener);
    }

    /**
     * 缓存清除
     */
    public static void cacheClearAll(Activity activity) {
        //包括裁剪和压缩后的缓存，要在上传成功后调用，type 指的是图片or视频缓存取决于你设置的ofImage或ofVideo 注意：需要系统sd卡权限
//        PictureFileUtils.deleteCacheDirFile(activity, type);
        // 清除所有缓存 例如：压缩、裁剪、视频、音频所生成的临时文件
        PictureFileUtils.deleteAllCacheDirFile(activity);
    }
}
