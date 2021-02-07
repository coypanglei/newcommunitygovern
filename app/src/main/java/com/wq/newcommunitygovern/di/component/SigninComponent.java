package com.wq.newcommunitygovern.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.wq.newcommunitygovern.di.module.SigninModule;
import com.wq.newcommunitygovern.mvp.contract.SigninContract;

import com.jess.arms.di.scope.ActivityScope;
import com.wq.newcommunitygovern.mvp.ui.activity.signin.SigninActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/26/2020 15:52
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = SigninModule.class, dependencies = AppComponent.class)
public interface SigninComponent {
    void inject(SigninActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SigninComponent.Builder view(SigninContract.View view);

        SigninComponent.Builder appComponent(AppComponent appComponent);

        SigninComponent build();
    }
}