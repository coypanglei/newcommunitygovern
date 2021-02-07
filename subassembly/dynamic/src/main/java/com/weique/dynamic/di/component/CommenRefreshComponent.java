package com.weique.dynamic.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.weique.dynamic.di.module.CommenRefreshModule;
import com.weique.dynamic.mvp.contract.CommonRefreshContract;

import com.jess.arms.di.scope.ActivityScope;
import com.weique.dynamic.mvp.ui.activity.CommonRefreshActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 09/04/2020 13:48
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = CommenRefreshModule.class, dependencies = AppComponent.class)
public interface CommenRefreshComponent {
    void inject(CommonRefreshActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CommenRefreshComponent.Builder view(CommonRefreshContract.View view);

        CommenRefreshComponent.Builder appComponent(AppComponent appComponent);

        CommenRefreshComponent build();
    }
}