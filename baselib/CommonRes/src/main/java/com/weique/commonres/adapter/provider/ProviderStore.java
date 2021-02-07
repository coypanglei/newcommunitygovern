package com.weique.commonres.adapter.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.weique.commonres.base.commonbean.DynamicFormEnum;

/**
 * @author GK
 * @description: 供应者商店
 * @date :2020/9/11 9:17
 */
public class ProviderStore {

    private ProviderFactory factory;

    public ProviderStore(ProviderFactory factory) {
        this.factory = factory;
    }


    /**
     * 出货
     *
     * @param type 订单
     * @return BaseItemProvider
     */
    public BaseItemProvider shipment(@DynamicFormEnum.ItemFlagEnum int type,String style) {
        BaseItemProvider provider = factory.createProvider(type,style);
        return provider;

    }
}
