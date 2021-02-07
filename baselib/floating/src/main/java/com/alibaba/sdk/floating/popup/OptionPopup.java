package com.alibaba.sdk.floating.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.floating.CommonEnum;
import com.alibaba.sdk.floating.R;
import com.alibaba.sdk.floating.adapter.OptionPopupAdapter;
import com.alibaba.sdk.floating.bean.interfaces.StringAndBoolean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * @author GK
 * @description: 字符串加选中状态的  popup 支持多选单选  默认选中第一个  至少选中一个
 * @date :2020/12/4 16:04
 */
public class OptionPopup<T extends StringAndBoolean> extends BasePopupWindow implements View.OnClickListener {


    private OptionPopupAdapter<T> mAdapter;


    private int AUTO_VPT = 8;

    private TextView name;
    private TextView sure;
    private RecyclerView optionRecycler;
    private TextView ove;

    public interface OnSureClickListener<T> {
        /**
         * 确定点击事件
         *
         * @param bean bean
         */
        void onSureClick(T bean);
    }

    private OnSureClickListener onSureClickListener;

    public void setOnSureClickListener(OnSureClickListener onSureClickListener) {
        this.onSureClickListener = onSureClickListener;
    }

    /**
     * 设置阈值
     *
     * @param AUTO_VPT autoVPT
     */
    public void setAUTO_VPT(int AUTO_VPT) {
        this.AUTO_VPT = AUTO_VPT;
    }

    private String titleName;
    /**
     * 选择方式
     */
    @CommonEnum.ChoiceModelEnum
    private int choiceMode = CommonEnum.ChoiceModelEnum.SOLE;
    /**
     * 布局方式
     */
    @CommonEnum.LayoutModelEnum
    private int layoutMode = CommonEnum.LayoutModelEnum.AUTO;
    private List<T> list;

    /**
     * 支持延迟加载的PopupWindow
     *
     * @param context    context
     * @param titleName  titleName
     * @param choiceMode 选择模式
     * @param layoutMode 布局 模式
     * @param list       数据
     */
    public OptionPopup(Context context, String titleName, @CommonEnum.ChoiceModelEnum int choiceMode,
                       @CommonEnum.LayoutModelEnum int layoutMode, List<T> list) {
        super(context);
        this.titleName = titleName;
        this.choiceMode = choiceMode;
        this.layoutMode = layoutMode;
        this.list = list;
        initPopup();
    }

    /**
     * 支持延迟加载的PopupWindow
     *
     * @param context   context
     * @param titleName titleName
     * @param list      list
     */
    public OptionPopup(Context context, String titleName, List<T> list) {
        super(context);
        this.titleName = titleName;
        this.list = list;
        initPopup();
    }


    /**
     * 初始化popup
     */
    private void initPopup() {
        try {
            setPopupGravity(Gravity.BOTTOM);
            //setBlurBackgroundEnable(true);
            setBackgroundColor(getContext().getResources().getColor(R.color.transparent48));
            setOutSideDismiss(true);
            setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            ove = findViewById(R.id.ove);
            ove.setOnClickListener(this);
            name = findViewById(R.id.name);
            name.setText(titleName);
            name.setOnClickListener(this);
            sure = findViewById(R.id.sure);
            sure.setOnClickListener(this);
            optionRecycler = findViewById(R.id.option_recycler);
            initAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {
        try {
            mAdapter = new OptionPopupAdapter<>(list);
            switch (layoutMode) {
                case CommonEnum.LayoutModelEnum.ERECT:
                    setErect();
                    break;
                case CommonEnum.LayoutModelEnum.FLOW:
                    setFlow();
                    break;
                case CommonEnum.LayoutModelEnum.AUTO:
                    if (list.size() > AUTO_VPT) {
                        setFlow();
                    } else {
                        setErect();
                    }
                    break;
                default:
            }
            mAdapter.setChoiceModel(choiceMode);
            optionRecycler.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                    mAdapter.setClickItemPos(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置竖列布局
     */
    private void setErect() throws Exception {
        optionRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        optionRecycler.setHasFixedSize(true);
        optionRecycler.setItemAnimator(new DefaultItemAnimator());
        optionRecycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.eeeeee)
                .sizeResId(R.dimen.dp_1)
                .build());
        mAdapter.setLayoutModel(CommonEnum.LayoutModelEnum.ERECT);
    }

    /**
     * 设置流式布局
     */
    private void setFlow() throws Exception {
        //2，增加谷歌流式布局
        FlexboxLayoutManager manager = new FlexboxLayoutManager(getContext());
        //设置主轴排列方式
        manager.setFlexDirection(FlexDirection.ROW);
        //设置是否换行
        manager.setFlexWrap(FlexWrap.WRAP);
        manager.setAlignItems(AlignItems.STRETCH);
        manager.setJustifyContent(JustifyContent.FLEX_START);
        optionRecycler.setLayoutManager(manager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        optionRecycler.setHasFixedSize(true);
        optionRecycler.setItemAnimator(new DefaultItemAnimator());
        optionRecycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.lucency)
                .sizeResId(R.dimen.dp_6)
                .build());
        optionRecycler.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext())
                .colorResId(R.color.lucency)
                .sizeResId(R.dimen.dp_6)
                .build());
        mAdapter.setLayoutModel(CommonEnum.LayoutModelEnum.FLOW);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_option_layout);
    }
}
