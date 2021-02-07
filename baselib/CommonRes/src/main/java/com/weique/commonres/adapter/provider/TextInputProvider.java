package com.weique.commonres.adapter.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

/**
 * @author GK
 * @description: 简单的 text view item 提供者
 * @date :2020/9/10 10:09
 */
public class TextInputProvider extends BaseItemProvider<RecordsBean> {
    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.LOGIN_PASSWORD;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_item_text_input;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean stringDynamicFormItemBean) {
        TextInputEditText textInputEditText = baseViewHolder.getView(R.id.textInputEdit);
        TextInputLayout textInputLayout =baseViewHolder.getView(R.id.textInputLayout);
        textInputLayout.setHint(stringDynamicFormItemBean.getTitile());

    }


}
