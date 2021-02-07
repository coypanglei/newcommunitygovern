package com.wq.newcommunitygovern.app.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wq.newcommunitygovern.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import timber.log.Timber;

/**
 * @author GK
 * @description:
 * @date :2020/9/12 13:49
 */
public class SuperRecyclerSingleAdapterView extends RelativeLayout {

    private boolean showSwipe;
    private SwipeRefreshLayout sr;
    private RecyclerView recyclerView;
    private SuperRecyclerSingleAdapterListener listener;
    private BaseQuickAdapter mBaseQuickAdapter;
    private boolean showAdd;
    /**
     * adapter set data 记录下来 size 后面以这个为标准 判断是否还有数据 控制加载更多控件显示隐藏
     */
    private int initSize;

    public interface SuperRecyclerSingleAdapterListener {
        void onRefresh();

        void onLoadMore();
    }

    public void setListener(SuperRecyclerSingleAdapterListener listener) {
        this.listener = listener;
        if (showSwipe && sr != null && listener != null) {
            sr.setOnRefreshListener(listener::onRefresh);
        } else {
            throw new NullPointerException("SwipeRefreshLayout is null");
        }
    }

    public SuperRecyclerSingleAdapterView(Context context) {
        this(context, null);
    }

    public SuperRecyclerSingleAdapterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperRecyclerSingleAdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView(context);
    }

    /**
     * 初始化属性信息
     *
     * @param context context
     * @param attrs   attrs
     */

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.app_SuperRecyclerSingleAdapterView);
        showSwipe = ta.getBoolean(R.styleable.app_SuperRecyclerSingleAdapterView_app_show_Swipe, false);
        showAdd = ta.getBoolean(R.styleable.app_SuperRecyclerSingleAdapterView_app_show_add, true);

    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_super_recycler_single_adapter_view, this, false);
        addView(view);
        sr = findViewById(R.id.swipe);
        sr.setEnabled(showSwipe);
        recyclerView = findViewById(R.id.recycler_view);
    }

    public void setBaseQuickAdapter(BaseQuickAdapter baseQuickAdapter) {
        if (baseQuickAdapter != null) {
            this.mBaseQuickAdapter = baseQuickAdapter;
            this.mBaseQuickAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> listener.onLoadMore());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                    .colorResId(R.color.app_colorPrimary)
                    .sizeResId(R.dimen.dp_4)
                    .build());
            recyclerView.setAdapter(this.mBaseQuickAdapter);
            mBaseQuickAdapter.addChildClickViewIds();
        }
    }

    /**
     * 单布局  RecyclerView
     */
    public <T> void setRecyclerViewData(List<T> data, boolean isLoadMore) {
        try {
            if (mBaseQuickAdapter == null) {
                Timber.i("baseQuickAdapter is null");
                return;
            }
            if (isLoadMore) {
                mBaseQuickAdapter.addData(data);
            } else {
                initSize = data.size();
                mBaseQuickAdapter.setList(data);
            }
            if (data == null || data.size() == 0
                    || data.size() < initSize) {
                mBaseQuickAdapter.getLoadMoreModule().loadMoreEnd(true);
            } else {
                mBaseQuickAdapter.getLoadMoreModule().loadMoreComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
