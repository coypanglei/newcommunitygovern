package com.weique.commonres.adapter.provider;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.SettingStyleBean;
import com.weique.commonres.utils.commonutils.StringUtil;

import org.jetbrains.annotations.NotNull;

import me.jessyan.armscomponent.commonres.R;

/**
 * 编辑 框 带清除的
 *
 * @author Administrator
 */
public class EditTextTopItemProvider extends BaseItemProvider<RecordsBean> {
    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.EDIT_ITEM_TOP;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_top_edit;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean recordsBean) {

        try {
            ProviderMultiAdapter adapter = (ProviderMultiAdapter) getAdapter();
            if (ObjectUtils.isNotEmpty(recordsBean)) {
                assert adapter != null;
                SettingStyleBean styleBean = adapter.getGson().fromJson(recordsBean.getStyle(), SettingStyleBean.class);
                if (ObjectUtils.isNotEmpty(recordsBean.getTitile())) {
                    baseViewHolder.setText(R.id.tv_title, recordsBean.getTitile());
                }
                EditText editText = baseViewHolder.findView(R.id.et_title);
                ImageView clearImg = baseViewHolder.findView(R.id.clear_text);
                assert clearImg != null;
                editText.setHint(Html.fromHtml(recordsBean.getDefaultValue()));
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String keyword = s.toString();
                        recordsBean.setValue(s.toString());
                        if (StringUtil.isNullString(keyword)) {
                            clearImg.setVisibility(View.GONE);
                        } else {

                            if (clearImg.getVisibility() == View.GONE) {
                                clearImg.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
                /*
                 清除选中
                 */
                clearImg.setOnClickListener(v -> editText.setText(""));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
