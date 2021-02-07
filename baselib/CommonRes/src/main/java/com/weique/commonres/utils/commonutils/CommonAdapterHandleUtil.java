package com.weique.commonres.utils.commonutils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.customview.loadmore.CustomLoadMoreView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import me.jessyan.armscomponent.commonres.R;

/**
 * @author GK
 * @description: adapter 处理类
 * @date :2020/8/24 16:04
 */
public class CommonAdapterHandleUtil {
    /**
     * 通用的 adapter 配置 有分割线  有 上来加载更多
     *
     * @param context      context
     * @param adapter      adapter
     * @param recyclerView recyclerView
     * @param itemViewId   item view 点击 有才会添加
     */
    public static void commonUseConfig(Context context, BaseQuickAdapter adapter, RecyclerView recyclerView, int... itemViewId) {
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.public_gray_eee)
                .margin(ArmsUtils.dip2px(16f), ArmsUtils.dip2px(16f))
                .sizeResId(R.dimen.public_dp_1)
                .build());
        adapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        adapter.setAnimationEnable(true);
        adapter.setAnimationFirstOnly(true);
        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn);
        ArmsUtils.configRecyclerView(recyclerView, new LinearLayoutManager(context));
        if (itemViewId.length > 0) {
            adapter.addChildClickViewIds(itemViewId);
        }
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.public_null_content_min);
    }

}
