package com.weique.commonres.adapter.provider;

import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.weique.commonres.base.commonbean.DynamicFormEnum;
import com.weique.commonres.utils.commonutils.StringUtil;
import com.weique.commonres.utils.globalutils.GlideNewImageLoader;
import com.weique.commonres.utils.globalutils.UserInfoUtils;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;

import org.jetbrains.annotations.NotNull;


import me.jessyan.armscomponent.commonres.R;
import timber.log.Timber;

public class PersonalInformationProvider extends BaseItemProvider {

    @Override
    public int getItemViewType() {
        return DynamicFormEnum.ItemFlagEnum.PERSONAL_INFORMATION;
    }

    @Override
    public int getLayoutId() {
        return R.layout.public_personal_information;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        try {
            ImageView imageView = baseViewHolder.getView(R.id.head_img);
            UserInfoBean userInfoBean = UserInfoUtils.getInstance().getUserInfo();
            if (ObjectUtils.isNotEmpty(userInfoBean)) {
                String roleName = "(" + StringUtil.setText(userInfoBean.getRoleName()) + ")";
                baseViewHolder.setText(R.id.name, StringUtil.setText(userInfoBean.getName() + roleName));
                baseViewHolder.setText(R.id.tag_one, StringUtil.setText(userInfoBean.getFullPath()));
                //如果图片地址为空，加载 蓝背景 和 名字的前两位 不是空加载
                if (ObjectUtils.isNotEmpty(userInfoBean.getHeadUrl())) {
                    GlideNewImageLoader.loadRoundImage(context, userInfoBean.getHeadUrl(), imageView, 5);
                }
                if (userInfoBean.getName().length() > 2) {
                    baseViewHolder.setText(R.id.head_tv, userInfoBean.getName().substring(0, 2));
                } else {
                    baseViewHolder.setText(R.id.head_tv, userInfoBean.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
