package com.alibaba.sdk.floating.adapter;

import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.floating.CommonEnum;
import com.alibaba.sdk.floating.R;
import com.alibaba.sdk.floating.bean.interfaces.StringAndBoolean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author GK
 * @description:
 * @date :2020/12/7 10:07
 */
public class OptionPopupAdapter<T extends StringAndBoolean> extends BaseQuickAdapter<T, BaseViewHolder> {

    @CommonEnum.LayoutModelEnum
    private int layoutModel = CommonEnum.LayoutModelEnum.ERECT;

    @CommonEnum.ChoiceModelEnum
    private int choiceModel = CommonEnum.ChoiceModelEnum.SOLE;

    private LinkedHashSet<Integer> checkPoss;
    private int checkPos = 0;

    public OptionPopupAdapter(@Nullable List<T> data) {
        super(R.layout.popup_option_item, data);
        checkPoss = new LinkedHashSet<>();
    }

    /**
     * 设置选项模式
     *
     * @param choiceModel choiceModel
     */
    public void setChoiceModel(@CommonEnum.ChoiceModelEnum int choiceModel) {
        this.choiceModel = choiceModel;
    }


    /**
     * 设置布局方式
     *
     * @param layoutModel layoutModel
     */
    public void setLayoutModel(@CommonEnum.LayoutModelEnum int layoutModel) {
        this.layoutModel = layoutModel;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, T t) {
        try {
            int adapterPosition = helper.getAdapterPosition();
            TextView textItem = helper.getView(R.id.text_item);
            switch (layoutModel) {
                case CommonEnum.LayoutModelEnum.ERECT:
                    ViewGroup.LayoutParams layoutParams = textItem.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    textItem.setLayoutParams(layoutParams);
                    if (t.getCheckState()) {
                        helper.setTextColor(R.id.text_item, getContext().getResources().getColor(R.color.blue_4D8FF7));
                        if (choiceModel == CommonEnum.ChoiceModelEnum.SOLE) {
                            checkPos = adapterPosition;
                        } else {
                            checkPoss.add(adapterPosition);
                        }
                    } else {
                        helper.setTextColor(R.id.text_item, getContext().getResources().getColor(R.color.black_333));
                        if (choiceModel == CommonEnum.ChoiceModelEnum.MULTI) {
                            checkPoss.remove(adapterPosition);
                        }
                    }
                    break;
                case CommonEnum.LayoutModelEnum.FLOW:
                    if (t.getCheckState()) {
                        helper.setTextColor(R.id.text_item, getContext().getResources().getColor(R.color.blue_4D8FF7));
                        helper.setBackgroundResource(R.id.text_item, R.drawable.shape_b_white_c4_19);
                        if (choiceModel == CommonEnum.ChoiceModelEnum.SOLE) {
                            checkPos = adapterPosition;
                        } else {
                            checkPoss.add(adapterPosition);
                        }
                    } else {
                        helper.setTextColor(R.id.text_item, getContext().getResources().getColor(R.color.black_333));
                        helper.setBackgroundResource(R.id.text_item, R.drawable.shape_b_lucency_c4_19);
                        if (choiceModel == CommonEnum.ChoiceModelEnum.MULTI) {
                            checkPoss.remove(adapterPosition);
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置点击位置
     *
     * @param position position
     */
    public void setClickItemPos(int position) {
        switch (choiceModel) {
            case CommonEnum.ChoiceModelEnum.SOLE:
                if (position == checkPos) {
                    return;
                } else {
                    getData().get(checkPos).setCheckState(false);
                    notifyItemChanged(checkPos);
                    getData().get(position).setCheckState(true);
                    notifyItemChanged(position);
                }
                break;
            case CommonEnum.ChoiceModelEnum.MULTI:
                if (checkPoss.size() <= 1) {
                    Toast.makeText(getContext(), "至少选中一个选项", Toast.LENGTH_LONG).show();
                    return;
                }
                T t = getData().get(position);
                t.setCheckState(!t.getCheckState());
                notifyItemChanged(position);
                break;
            default:
        }
    }
}
