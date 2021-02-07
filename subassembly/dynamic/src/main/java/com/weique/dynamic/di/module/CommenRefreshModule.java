package com.weique.dynamic.di.module;

import dagger.Binds;
import dagger.Module;

import com.weique.dynamic.mvp.contract.CommonRefreshContract;
import com.weique.dynamic.mvp.model.CommenRefreshModel;


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
@Module
public abstract class CommenRefreshModule {

    @Binds
    abstract CommonRefreshContract.Model bindCommenRefreshModel(CommenRefreshModel model);
}