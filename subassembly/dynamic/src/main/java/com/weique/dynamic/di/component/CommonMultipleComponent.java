package com.weique.dynamic.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.weique.dynamic.di.module.CommonMultipleModule;
import com.weique.dynamic.mvp.contract.CommonMultipleContract;

import com.jess.arms.di.scope.ActivityScope;
import com.weique.dynamic.mvp.ui.activity.CommonMultipleActivity;


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
@ActivityScope
@Component(modules = CommonMultipleModule.class, dependencies = AppComponent.class)
public interface CommonMultipleComponent {
    void inject(CommonMultipleActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CommonMultipleComponent.Builder view(CommonMultipleContract.View view);

        CommonMultipleComponent.Builder appComponent(AppComponent appComponent);

        CommonMultipleComponent build();
    }
}