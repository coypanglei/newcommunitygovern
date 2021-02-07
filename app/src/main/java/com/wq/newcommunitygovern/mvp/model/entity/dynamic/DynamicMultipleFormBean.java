package com.wq.newcommunitygovern.mvp.model.entity.dynamic;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.weique.commonres.base.commonbean.DynamicFormEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GK
 * @description: 动态表单对象 多样式
 * @date :2020/9/9 17:22
 */
public class DynamicMultipleFormBean extends DynamicFormBeasBean {
    private DynamicMultipleFormBean(DynamicMultipleFormBean.Builder builder) {
        formItemBeanList = new ArrayList<>();
        this.title = builder.title;
        this.whatToDo = builder.behavior;
        this.pathOfCheck = builder.pathOfCheck;
        this.pathOfDelete = builder.pathOfDelete;
        this.pathOfAlert = builder.pathOfAlert;
        this.permission = builder.permission;
        this.formItemBeanList = builder.list;
    }


    private List<DynamicFormItemBean> formItemBeanList;


    public List<DynamicFormItemBean> getFormItemBeanList() {
        return formItemBeanList;
    }

    public void addDynamicFormItemBean(DynamicFormItemBean<?> formItemBean) {
        if (formItemBeanList == null) {
            formItemBeanList = new ArrayList<>();
        }
        formItemBeanList.add(formItemBean);
    }

    public DynamicFormItemBean<?> getDynamicFormItemBean(int pos) {
        return (DynamicFormItemBean<?>) formItemBeanList.get(pos);
    }

//    /**
//     * 工厂 仓库  根据订单出货
//     * 获取 动态表单的item
//     */
//    public List<BaseItemProvider> getDynamicFormItemBeansProvider() {
//        if (formItemBeanList.size() > 0) {
//            List<BaseItemProvider> lol = new ArrayList<>();
//            //供应者商店
//            ProviderStore providerStore = new ProviderStore(new ProviderFactory());
//            for (DynamicFormItemBean bean : formItemBeanList) {
//                lol.add(providerStore.shipment(bean.getItemFlag()));
//            }
//            return lol;
//        }
//        return null;
//    }


    public static class Builder {


        public Builder() {
        }

        /**
         * 表单名称
         */
        private String title;
        /**
         * 做什么
         */
        @DynamicFormEnum.Behavior
        private int behavior;
        /**
         * 查看接口
         */
        private String pathOfCheck;
        /**
         * 删除接口
         */
        private String pathOfDelete;
        /**
         * 修改接口
         */
        private String pathOfAlert;
        /**
         * 权限
         */
        @DynamicFormEnum.CommonPermission
        private int permission;


        /**
         * 表单item
         */
        private List<DynamicFormItemBean> list;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder behavior(@DynamicFormEnum.Behavior int behavior) {
            this.behavior = behavior;
            return this;
        }

        public Builder pathOfCheck(String pathOfCheck) {
            this.pathOfCheck = pathOfCheck;
            return this;
        }

        public Builder pathOfDelete(String pathOfDelete) {
            this.pathOfDelete = pathOfDelete;
            return this;
        }

        public Builder pathOfAlert(String pathOfAlert) {
            this.pathOfAlert = pathOfAlert;
            return this;
        }

        public Builder permission(int permission) {
            this.permission = permission;
            return this;
        }

        public Builder formItemBeanList(List<DynamicFormItemBean> formItemBeanList) {
            this.list = formItemBeanList;
            return this;
        }

        public DynamicMultipleFormBean build() {
            return new DynamicMultipleFormBean(this);
        }
    }
}
