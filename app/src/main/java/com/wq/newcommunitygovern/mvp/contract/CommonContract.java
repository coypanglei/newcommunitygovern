package com.wq.newcommunitygovern.mvp.contract;

import android.content.Context;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;
import com.weique.commonres.base.BaseResponse;
import com.weique.commonres.base.commonbean.CommonCollectBean;
import com.weique.commonres.base.commonbean.CommonTitleBean;
import com.weique.commonres.base.commonbean.interfaces.CommonBean;
import com.weique.commonres.utils.commonutils.BackObjectInterface;
import com.weique.commonservice.zongzhi.bean.UserInfoBean;
import com.wq.newcommunitygovern.mvp.model.entity.basebean.BaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/27/2020 14:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public interface CommonContract {


    interface View extends IView {
        RecyclerView getRecyclerView();

        Context getContext();

        RelativeLayout getRelativeLayout();

    }

    interface Model extends IModel {
        /**
         * 发送后台统一格式数据 返回值
         */
        Observable<BaseResponse<Object>> putCommonList(Map map, String path);

        /**
         * 获取枚举值
         *
         * @param type
         * @return
         */
        Observable<BaseResponse<List<CommonTitleBean>>> getCommonEnums(String type);

        /**
         * 获取枚举值
         *
         * @return
         */
        void getObject(CommonCollectBean commonBean, IView mRootView, BackObjectInterface backObjectInterface);



    }
}
