package com.weique.commonres.adapter.provider;

import android.view.View;
import android.widget.ImageView;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.base.commonbean.SettingStyleBean;
import org.jetbrains.annotations.NotNull;
import me.jessyan.armscomponent.commonres.R;
import timber.log.Timber;

/**
 * 设置点击使用
 * @author Administrator
 */
public class SettingClickProvider extends BaseItemProvider<RecordsBean> {


    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_setting_click;
    }


    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, RecordsBean recordsBean) {
        try {
            //单独添加点击事件
//            addChildClickViewIds(R.id.iv_left);
            ProviderMultiAdapter adapter = (ProviderMultiAdapter) getAdapter();
            if (ObjectUtils.isNotEmpty(recordsBean)) {
                assert adapter != null;
                SettingStyleBean styleBean = adapter.getGson().fromJson(recordsBean.getStyle(), SettingStyleBean.class);
                if (ObjectUtils.isNotEmpty(styleBean.getImg())) {
                    ImageView imageView = baseViewHolder.getView(R.id.iv_left);
                    imageView.setImageResource(ArmsUtils.getDrawable(context, styleBean.getImg()));
                }
                if (styleBean.isVisibleView()) {
                    baseViewHolder.setVisible(R.id.view, true);
                }
                if (ObjectUtils.isNotEmpty(recordsBean.getTitile())) {
                    baseViewHolder.setText(R.id.tv_title, recordsBean.getTitile());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
