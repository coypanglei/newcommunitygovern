package com.weique.commonres.adapter.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

/**
 * @author GK
 * @description: 简单的 text view item 提供者
 * @date :2020/9/10 10:09
 */
public class SimpleImageShowProvider extends BaseItemProvider<RecordsBean> {
    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.IMAGE_LIST;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_simple_image_list;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean stringDynamicFormItemBean) {
//        baseViewHolder.setText(R.id.item_text, stringDynamicFormItemBean.getDisplayValue());
    }


}
