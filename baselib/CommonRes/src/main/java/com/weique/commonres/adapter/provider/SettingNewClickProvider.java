package com.weique.commonres.adapter.provider;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.SettingStyleBean;
import com.weique.commonres.utils.commonutils.StringUtil;
import com.weique.commonres.utils.globalutils.GlideNewImageLoader;
import com.weique.commonres.utils.globalutils.UserInfoUtils;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;
import timber.log.Timber;

/**
 * 设置点击使用
 *
 * @author Administrator
 */
public class SettingNewClickProvider extends BaseItemProvider<RecordsBean> {


    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_setting_click_two;
    }


    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean recordsBean) {
        try {
            ProviderMultiAdapter adapter = (ProviderMultiAdapter) getAdapter();
            if (ObjectUtils.isNotEmpty(recordsBean)) {
                assert adapter != null;
                SettingStyleBean styleBean = adapter.getGson().fromJson(recordsBean.getStyle(), SettingStyleBean.class);
                if (styleBean.isVisibleView()) {
                    baseViewHolder.setVisible(R.id.view, true);
                }
                if (ObjectUtils.isNotEmpty(recordsBean.getTitile())) {
                    baseViewHolder.setText(R.id.tv_title, recordsBean.getTitile());
                }
                if (ObjectUtils.isNotEmpty(styleBean.getImg())) {
                    ArmsUtils.getDrawable(context, styleBean.getImg());
                    Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), ArmsUtils.getDrawable(context, styleBean.getImg()), null);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    TextView textView = baseViewHolder.findView(R.id.tv_two);
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                if ("HeadUrl".equals(recordsBean.getType())) {
                    baseViewHolder.setVisible(R.id.view_head, true);
                    UserInfoBean userInfoBean = UserInfoUtils.getInstance().getUserInfo();
                    if (ObjectUtils.isNotEmpty(userInfoBean)) {
                        //如果图片地址为空，加载 蓝背景 和 名字的前两位 不是空加载
                        if (userInfoBean.getName().length() > 2) {
                            baseViewHolder.setText(R.id.head_tv, userInfoBean.getName().substring(0, 2));
                        } else {
                            baseViewHolder.setText(R.id.head_tv, userInfoBean.getName());
                        }
                        if (ObjectUtils.isNotEmpty(userInfoBean.getHeadUrl())) {

                            ImageView imageView = baseViewHolder.getView(R.id.head_img);
                            GlideNewImageLoader.loadRoundImage(context, userInfoBean.getHeadUrl(), imageView, 5);
                        }
                    }
                }else {
                    if (ObjectUtils.isNotEmpty(recordsBean.getDefaultValue())) {
                        baseViewHolder.setText(R.id.tv_two, recordsBean.getDefaultValue());
                    }
                    if (ObjectUtils.isNotEmpty(recordsBean.getValue())) {
                        baseViewHolder.setText(R.id.tv_two, recordsBean.getValue());
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
