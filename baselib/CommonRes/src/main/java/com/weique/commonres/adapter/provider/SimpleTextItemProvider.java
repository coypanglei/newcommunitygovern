package com.weique.commonres.adapter.provider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lihang.ShadowLayout;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.ShadowLayoutStyleBean;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

import static com.weique.commonres.utils.commonutils.ViewUtils.setCommonStyle;
import static com.weique.commonres.utils.commonutils.ViewUtils.setShadowLayoutStyle;

/**
 * @author Administrator
 * ShadowLayoutStyleBean 定制样式
 * {"LayoutBackgroundTrue":"#3982f6","LayoutBackground":"#53a8f4","cornerRadius":10,"leftMargin":20,"rightMargin":20,"textColor":"#ffffff"}
 */
public class SimpleTextItemProvider extends BaseItemProvider<RecordsBean> {
    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_text_view_center;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean recordsBean) {

        try {
            ProviderMultiAdapter adapter = (ProviderMultiAdapter) getAdapter();
            ShadowLayout shadowLayout = baseViewHolder.getView(R.id.mShadowLayout);
            TextView textView = baseViewHolder.getView(R.id.tv_title);
            if (ObjectUtils.isNotEmpty(recordsBean)) {
                assert adapter != null;
                ShadowLayoutStyleBean styleBean = adapter.getGson().fromJson(recordsBean.getStyle(), ShadowLayoutStyleBean.class);
                if (ObjectUtils.isNotEmpty(recordsBean.getTitile())) {
                    textView.setText(recordsBean.getTitile());
                }

                if (ObjectUtils.isNotEmpty(styleBean.getTextColor())) {
                    textView.setTextColor(Color.parseColor(styleBean.getTextColor()));
                }
                if (ObjectUtils.isNotEmpty(styleBean.getTextSize()) && 0 != styleBean.getTextSize()) {
                    textView.setTextSize(styleBean.getTextSize());
                }
                setShadowLayoutStyle(styleBean, shadowLayout);
                setCommonStyle(styleBean, shadowLayout);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
