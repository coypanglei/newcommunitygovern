package com.wq.newcommunitygovern.mvp.ui.binds;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.binder.QuickItemBinder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wq.newcommunitygovern.R;
import com.wq.newcommunitygovern.mvp.model.entity.InspectionRecordBean;

import org.jetbrains.annotations.NotNull;

/**
 * 巡检 记录
 *
 * @author Administrator
 */
public class InspectionItemBinder extends QuickItemBinder<InspectionRecordBean> implements Parcelable {


    @Override
    public int getLayoutId() {
        return R.layout.app_item_inspection;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, InspectionRecordBean inspectionRecordBean) {
        try {
            baseViewHolder.setText(R.id.name, inspectionRecordBean.getResourceName());
            baseViewHolder.setText(R.id.address, inspectionRecordBean.getDepartmentFullPath());
            baseViewHolder.setText(R.id.time, inspectionRecordBean.getCreateTime());
            baseViewHolder.setText(R.id.count, String.format("%s次", inspectionRecordBean.getCount()));
//            if (baseViewHolder.getAdapterPosition() == (getAdapter().getData().size() - 1)) {
//                baseViewHolder.setVisible(R.id.view, false);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public InspectionItemBinder() {
    }

    private InspectionItemBinder(Parcel in) {
    }

    public static final Parcelable.Creator<InspectionItemBinder> CREATOR = new Parcelable.Creator<InspectionItemBinder>() {
        @Override
        public InspectionItemBinder createFromParcel(Parcel source) {
            return new InspectionItemBinder(source);
        }

        @Override
        public InspectionItemBinder[] newArray(int size) {
            return new InspectionItemBinder[size];
        }
    };
}
