package com.weique.commonres.adapter;


import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.google.gson.Gson;
import com.jess.arms.http.imageloader.ImageLoader;
import com.weique.commonres.base.commonbean.RecordsBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Provider adapter
 */
public class ProviderMultiAdapter extends BaseProviderMultiAdapter<RecordsBean> implements LoadMoreModule {

    ImageLoader mImageLoader;
    Gson gson;


    public ProviderMultiAdapter() {
    }

    public ProviderMultiAdapter( ImageLoader mImageLoader, Gson gson) {
        this.mImageLoader = mImageLoader;
        this.gson = gson;
    }



    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    public void setmImageLoader(ImageLoader mImageLoader) {
        this.mImageLoader = mImageLoader;
    }


    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void addItemProvider(List<? extends BaseItemProvider> list) {
        for (BaseItemProvider provider : list) {
            addItemProvider(provider);
        }
    }

    /**
     * 自行根据数据、位置等内容，返回 item 类型
     *
     * @param data
     * @param position
     * @return
     */
    @Override
    protected int getItemType(@NotNull List<? extends RecordsBean> data, int position) {
        return data.get(position).getParamtype();
    }


}
