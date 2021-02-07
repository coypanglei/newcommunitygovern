package com.weique.commonres.base.commonbean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用 新增编辑
 *
 * @author Administrator
 */
public class CommonCollectBean implements Parcelable {

    /**
     * 数据集合
     */
    private List<RecordsBean> list;

    /**
     * 接口返回值 className;
     */
    private Class className;

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    /**
     * 编辑 或 新增 或 详情接口
     */

    private String path;

    /**
     * 类型  Constant  CommonCollectionEnum  新增 详情 删除 编辑
     */
    private int type;


    /**
     * map 接口需要的参数
     */
    private Map<String, Object> map;

    /**
     * 通用title
     */
    private String title;


    /**
     * 通用 ui 样式
     *
     * @return
     */

    private String style;


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<RecordsBean> getList() {
        return list;
    }

    public void setList(List<RecordsBean> list) {
        this.list = list;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public CommonCollectBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<BaseBinderAdapterBean> getBindBeanList() {
        return bindBeanList;
    }

    public void setBindBeanList(List<BaseBinderAdapterBean> bindBeanList) {
        this.bindBeanList = bindBeanList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private List<BaseBinderAdapterBean> bindBeanList;


    @Override
    public String toString() {
        return "CommonCollectBean{" +
                "list=" + list +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", map=" + map +
                ", title='" + title + '\'' +
                ", style='" + style + '\'' +
                ", bindBeanList=" + bindBeanList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
        dest.writeSerializable(this.className);
        dest.writeString(this.path);
        dest.writeInt(this.type);
        dest.writeMap(map);
        dest.writeString(this.title);
        dest.writeString(this.style);
        dest.writeTypedList(this.bindBeanList);
    }

    protected CommonCollectBean(Parcel in) {
        this.list = in.createTypedArrayList(RecordsBean.CREATOR);
        this.className = (Class) in.readSerializable();
        this.path = in.readString();
        this.type = in.readInt();
        map = new HashMap<>();
        in.readMap(map, getClass().getClassLoader());
        this.title = in.readString();
        this.style = in.readString();
        this.bindBeanList = in.createTypedArrayList(BaseBinderAdapterBean.CREATOR);
    }

    public static final Creator<CommonCollectBean> CREATOR = new Creator<CommonCollectBean>() {
        @Override
        public CommonCollectBean createFromParcel(Parcel source) {
            return new CommonCollectBean(source);
        }

        @Override
        public CommonCollectBean[] newArray(int size) {
            return new CommonCollectBean[size];
        }
    };


}
