package com.wq.newcommunitygovern.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.wq.newcommunitygovern.di.module.CommonModule;
import com.wq.newcommunitygovern.mvp.contract.CommonContract;

import com.jess.arms.di.scope.ActivityScope;
import com.wq.newcommunitygovern.mvp.ui.activity.common.CommonActivity;


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
@ActivityScope
@Component(modules = CommonModule.class, dependencies = AppComponent.class)
public interface CommonComponent {
    void inject(CommonActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CommonComponent.Builder view(CommonContract.View view);

        CommonComponent.Builder appComponent(AppComponent appComponent);

        CommonComponent build();
    }
}