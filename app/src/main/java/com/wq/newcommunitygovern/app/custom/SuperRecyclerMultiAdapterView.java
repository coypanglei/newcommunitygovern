package com.wq.newcommunitygovern.app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.commonres.adapter.provider.ProviderFactory;
import com.weique.commonres.adapter.provider.ProviderStore;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.wq.newcommunitygovern.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GK
 * @description:
 * @date :2020/9/12 13:49
 */
public class SuperRecyclerMultiAdapterView extends RelativeLayout {


    private RecyclerView recyclerView;
    private ProviderMultiAdapter adapter;

    public SuperRecyclerMultiAdapterView(Context context) {
        this(context, null);
    }

    public SuperRecyclerMultiAdapterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerMultiAdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    public ProviderMultiAdapter getAdapter() {
        return adapter;
    }

    /**
     * 初始化属性信息
     *
     * @param context context
     * @param attrs   attrs
     */

    private void initAttrs(Context context, AttributeSet attrs) {
    }

    private void initView(Context context) {
        try {
            View view = LayoutInflater.from(context).inflate(R.layout.app_super_recycler_multi_adapter_view, this, false);
            addView(view);
            recyclerView = findViewById(R.id.recycler_view);
            adapter = new ProviderMultiAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                    .colorResId(R.color.app_colorPrimary)
                    .sizeResId(R.dimen.dp_4)
                    .build());
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 多布局  RecyclerView
     */
    public void setMultiRecyclerViewData(List<RecordsBean> data) {

        try {
            List<BaseItemProvider> lol = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(data) && data.size() > 0) {
                //供应者商店
                ProviderStore providerStore = new ProviderStore(new ProviderFactory());
                for (RecordsBean bean : data) {
                    lol.add(providerStore.shipment(bean.getParamtype(),bean.getStyle()));
                }
            }
            adapter.addItemProvider(lol);
            adapter.setNewInstance(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
