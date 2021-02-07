package com.wq.newcommunitygovern.mvp.model.entity.dynamic;

import com.weique.commonres.base.commonbean.DynamicFormEnum;

/**
 * @author GK
 * @description: 动态表单对象单样式 单样式
 * @date :2020/9/9 17:22
 */
public class DynamicFormBeasBean {


    /**
     * 表单名称
     */
    protected String title;
    /**
     * 做什么
     */
    @DynamicFormEnum.Behavior
    protected int whatToDo;
    /**
     * 查看接口
     */
    protected String pathOfCheck;
    /**
     * 删除接口
     */
    protected String pathOfDelete;
    /**
     * 修改接口
     */
    protected String pathOfAlert;
    /**
     * 权限
     */
    @DynamicFormEnum.CommonPermission
    protected int permission;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWhatToDo() {
        return whatToDo;
    }

    public void setWhatToDo(@DynamicFormEnum.Behavior int whatToDo) {
        this.whatToDo = whatToDo;
    }

    public String getPathOfCheck() {
        return pathOfCheck;
    }

    public void setPathOfCheck(String pathOfCheck) {
        this.pathOfCheck = pathOfCheck;
    }

    public String getPathOfDelete() {
        return pathOfDelete;
    }

    public void setPathOfDelete(String pathOfDelete) {
        this.pathOfDelete = pathOfDelete;
    }

    public String getPathOfAlert() {
        return pathOfAlert;
    }

    public void setPathOfAlert(String pathOfAlert) {
        this.pathOfAlert = pathOfAlert;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(@DynamicFormEnum.CommonPermission int permission) {
        this.permission = permission;
    }
}
