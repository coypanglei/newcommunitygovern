package com.weique.commonres.utils.commonutils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.IView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.weique.commonres.base.commonbean.UploadFileRsponseBean;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.utils.GlideEngine;
import com.weique.commonres.utils.SelectStyleUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.jessyan.armscomponent.commonres.R;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import retrofit2.Retrofit;

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
     * @param onResultCallbackListener onResultCallbackListener
     */
    public static void gotoPhoto(Activity activity, int pictureMimeType, int max,
                                 boolean previewVideo, boolean enableCrop,
                                 OnResultCallbackListener onResultCallbackListener) {
        try {
            int maxFileSize;
            if (PictureMimeType.ofImage() == pictureMimeType) {
                maxFileSize = 5;
            } else {
                maxFileSize = 25;
            }
            PictureSelector.create(activity)
                    .openGallery(pictureMimeType)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                    .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                    .isWeChatStyle(true)// 是否开启微信图片选择风格
                    .isUseCustomCamera(false)// 是否使用自定义相机
//                    .setLanguage(language)// 设置语言，默认中文
                    .setPictureStyle(SelectStyleUtil.getDefaultStyle(activity))// 动态自定义相册主题
                    .setPictureWindowAnimationStyle(new PictureWindowAnimationStyle())// 自定义相册启动退出动画
                    .isWithVideoImage(true)// 图片和视频是否可以同选
                    .maxSelectNum(max)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .minVideoSelectNum(1)// 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
//                    .maxVideoSelectNum(1) // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
                    .imageSpanCount(4)// 每行显示个数
                    .isReturnEmpty(true)// 未选择数据时点击按钮是否可以返回
                    //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对.isCompress(false); && .isEnableCrop(false);有效,默认处理
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                    .isOriginalImageControl(true)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                    //.cameraFileName("test.png")    // 重命名拍照文件名、注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                    //.renameCompressFile("test.png")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                    //.renameCropFileName("test.png")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .isSingleDirectReturn(true)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                    .isPreviewImage(previewVideo)// 是否可预览图片
                    .isPreviewVideo(previewVideo)// 是否可预览视频
                    //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                    .isEnablePreviewAudio(true) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                    .imageFormat(pictureMimeTypeStr)// 拍照保存图片格式后缀,默认jpeg
                    .isEnableCrop(enableCrop)// 是否裁剪
                    .isCompress(true)// 是否压缩
                    .compressQuality(80)// 图片压缩后输出质量 0~ 100
                    .synOrAsy(true)//同步false或异步true 压缩 默认同步
                    .queryMaxFileSize(maxFileSize)// 只查多少M以内的图片、视频、音频  单位M
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                    //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .isOpenClickSound(false)// 是否开启点击声音
                    .selectionData(null)// 是否传入已选图片
                    .isDragFrame(false)// 是否可拖动裁剪框(固定)
                    .videoMaxSecond(25)
                    .videoMinSecond(5)
                    .isPreviewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    .cutOutQuality(100)// 裁剪输出质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .videoQuality(1)// 视频录制质量 0 or 1
                    .recordVideoSecond(19)//录制视频秒数 默认60s
                    //.forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                    .forResult(PictureConfig.CHOOSE_REQUEST, onResultCallbackListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link Retrofit} 自定义配置接口
     */
    public interface upLoadFileInterface {
        void setFileList(List<UploadFileRsponseBean> uploadFileRsponseBeans);
    }


    /**
     * 打开图片 视频  相册
     *
     * @param max
     * @param type
     */
    public static void openAlbum(int max, int type, RxErrorHandler mErrorHandler,
                          IView mRootView,
                          IRepositoryManager mRepositoryManager, Context context, upLoadFileInterface upLoadFileInterface) {
        PermissionUtil.requestPermissionOlySuccess((Activity) context, () -> {
            PictureSelectorUtils.gotoPhoto((Activity) context, type,
                    max, false, true, new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(List<LocalMedia> result) {
                            try {
                                List<String> strings = new ArrayList<>();
                                for (LocalMedia media : result) {
                                    if (StringUtil.isNotNullString(media.getCompressPath())) {
                                        strings.add(media.getCompressPath());
                                    } else {
                                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                                            strings.add(PictureFileUtils.getPath(context, Uri.parse(media.getPath())));
                                        } else {
                                            if (StringUtil.isNotNullString(media.getPath()) && new File(media.getPath()).exists()) {
                                                strings.add(media.getPath());
                                            }
                                        }
                                    }
                                }
                                CommonNetworkRequest.upLoadFile(strings,
                                        mErrorHandler, mRootView, mRepositoryManager,
                                        uploadFileRsponseBeans -> upLoadFileInterface.setFileList(uploadFileRsponseBeans));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        }, mErrorHandler, Constants.PERMISSIONS_LIST_READ_WRITE_CAMERA);
    }

}
