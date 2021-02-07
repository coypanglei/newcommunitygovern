package com.weique.commonres.adapter.provider;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.gson.Gson;
import com.weique.commonres.base.commonbean.CommonStyle;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

import static com.weique.commonres.utils.commonutils.ViewUtils.setCommonStyle;

/**
 * @author Administrator
 * 空 view
 */
public class NullViewProvider extends BaseItemProvider<RecordsBean> {
    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.NULL_VIEW;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_null_view;
    }


    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean recordsBean) {
        try {
            View view = baseViewHolder.getView(R.id.null_view);
            Gson gson = new Gson();
            if (ObjectUtils.isNotEmpty(recordsBean)) {
                CommonStyle styleBean = gson.fromJson(recordsBean.getStyle(), CommonStyle.class);
                if ("color".equals(styleBean.getBackgroundType())) {
                    if (ObjectUtils.isNotEmpty(styleBean.getBackground())) {
                        view.setBackgroundColor(Color.parseColor(styleBean.getBackground()));
                    }
                }
                setCommonStyle(styleBean, view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
