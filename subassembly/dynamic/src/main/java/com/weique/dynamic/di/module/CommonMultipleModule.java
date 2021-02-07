package com.weique.dynamic.di.module;

import androidx.fragment.app.FragmentActivity;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.weique.commonres.adapter.ProviderMultiAdapter;
import com.weique.dynamic.mvp.contract.CommonMultipleContract;
import com.weique.dynamic.mvp.model.CommonMultipleModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/17/2020 13:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class CommonMultipleModule {

    @Binds
    abstract CommonMultipleContract.Model bindCommonMultipleModel(CommonMultipleModel model);

    /**
     * 提供权限
     *
     * @param view
     * @return
     */
    @ActivityScope
    @Provides
    static ProviderMultiAdapter provideMultiAdapter(CommonMultipleContract.View view) {
        return new ProviderMultiAdapter();
    }


}