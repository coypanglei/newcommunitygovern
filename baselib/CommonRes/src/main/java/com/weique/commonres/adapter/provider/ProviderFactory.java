package com.weique.commonres.adapter.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.weique.commonres.base.commonbean.DynamicFormEnum;

/**
 * @author GK
 * @description: Provider 工厂
 * @date :2020/9/11 9:18
 */
public class ProviderFactory {
    public BaseItemProvider createProvider(@DynamicFormEnum.ItemFlagEnum int flag,String style) {
        switch (flag) {
            case DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_ONE:
                return new SimpleTextItemProvider();
            case DynamicFormEnum.ItemFlagEnum.TEXT_VIEW_SINGLE_SELECT:
//                return new SimpleTextItemSelectProvider();
            case DynamicFormEnum.ItemFlagEnum.IMAGE_LIST:
                return new SimpleImageShowProvider();
            case DynamicFormEnum.ItemFlagEnum.LOGIN_PASSWORD:
                return new TextInputProvider();
            case DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK:
                return new SettingClickProvider();
            case DynamicFormEnum.ItemFlagEnum.SETTING_ITEM_CLICK_TEXT:
                return new SettingNewClickProvider();
            case DynamicFormEnum.ItemFlagEnum.NULL_VIEW:
                return new NullViewProvider();
            case DynamicFormEnum.ItemFlagEnum.PERSONAL_INFORMATION:
                return new PersonalInformationProvider();
            case DynamicFormEnum.ItemFlagEnum.EDIT_ITEM_TOP:
                return new EditTextTopItemProvider();
            case DynamicFormEnum.ItemFlagEnum.EDIT_ITEM:
                return new InfoEditProvider();
            default:
        }
        return null;
    }
}
