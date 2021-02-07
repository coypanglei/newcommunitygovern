package com.weique.commonres.adapter.provider;




import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.SettingStyleBean;


import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

/**
 * 设置点击使用
 *
 * @author Administrator
 */
public class InfoEditProvider extends BaseItemProvider<RecordsBean> {


    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.EDIT_ITEM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_setting_click_two;
    }


    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, final RecordsBean recordsBean) {
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
                if (ObjectUtils.isNotEmpty(recordsBean.getDefaultValue())) {
                    baseViewHolder.setText(R.id.tv_two, recordsBean.getDefaultValue());
                }
                if (ObjectUtils.isNotEmpty(recordsBean.getValue())) {
                    baseViewHolder.setText(R.id.tv_two, recordsBean.getValue());
                }
                if (!recordsBean.isEdit()) {
                    baseViewHolder.setEnabled(R.id.mShadowLayout, false);
                    baseViewHolder.setVisible(R.id.iv_right, false);
                }else {
                    baseViewHolder.setEnabled(R.id.mShadowLayout, true);
                    baseViewHolder.setVisible(R.id.iv_right, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
