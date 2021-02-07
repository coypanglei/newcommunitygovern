package com.weique.commonres.base.commonbean;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panglei  序列化 在界面传递
 */
public class RecordsBean implements Parcelable {


    /**
     * 编辑的类型
     */
    private int paramType;
    /**
     * 标题
     */
    private String titile;

    /**
     * field 与后台相关的字段
     * 做映射使用
     */

    private String field;

    /**
     * default 默认值 没有数据时默认显示使用
     */
    private String defaultValue;

    /**
     * 订单排序数字
     */
    private int ordernum;

    /**
     * 所属行为
     */
    private String type;

    /**
     * 分类使用的类型 接口获取时的区分
     */
    private String category;

    /**
     * 是否是编辑
     */
    private boolean edit = true;
    /**
     * 是否必填
     */
    private boolean require;

    /**
     * 特殊需求专用
     */
    private String changeType;

    /**
     * 特殊需求专用
     * 返回值是 object
     */
    private Object carryObject;

    /**
     * 当前数据的集合
     * 当需要嵌套recylerview使用
     */
    private List<RecordsBean> list = new ArrayList<>();


    /**
     * Ui 属性值
     */
    private String style;


    /**
     * 用来界面显示的值
     */
    private String value;

    /**
     * 真正使用的值 例如传递的值
     */
    private String selectValue;

    /**
     * 级别
     */
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParamType() {
        return paramType;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getParamtype() {
        return paramType;
    }

    public void setParamtype(int paramtype) {
        this.paramType = paramtype;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setList(List<RecordsBean> list) {
        this.list = list;
    }

    public RecordsBean() {
    }

    public RecordsBean(int paramtype, String titile, String field, String defaultvalue) {
        this.paramType = paramtype;
        this.titile = titile;
        this.field = field;
        this.defaultValue = defaultvalue;
    }

    /**
     * @param paramtype    类型
     * @param titile       标题
     * @param field        和后台关联参数
     * @param defaultvalue 默认显示值
     * @param require      是否必填
     * @param type         行为
     */
    public RecordsBean(int paramtype, String titile, String field, String defaultvalue, boolean require, String type) {
        this.require = require;
        this.paramType = paramtype;
        this.titile = titile;
        this.field = field;
        this.defaultValue = defaultvalue;
        this.type = type;
    }


    public RecordsBean(int paramType, String titile, String field, String defaultValue, String style, String type) {
        this.paramType = paramType;
        this.titile = titile;
        this.field = field;
        this.defaultValue = defaultValue;
        this.style = style;
        this.type = type;
    }

    public RecordsBean(int paramType, String titile, String field, String defaultValue, String style, boolean require) {
        this.paramType = paramType;
        this.titile = titile;
        this.field = field;
        this.defaultValue = defaultValue;
        this.style = style;
        this.require = require;
    }

    public Object getCarryObject() {
        return carryObject;
    }

    public void setCarryObject(Object carryObject) {
        this.carryObject = carryObject;
    }


    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private int spanSize;

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }


    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean getRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }


    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public boolean isRequire() {
        return require;
    }

    public List<RecordsBean> getList() {
        return list;
    }


    @Override
    public String toString() {
        return "RecordsBean{" +
                "paramType=" + paramType +
                ", titile='" + titile + '\'' +
                ", field='" + field + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", ordernum=" + ordernum +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", edit=" + edit +
                ", require=" + require +
                ", changeType='" + changeType + '\'' +
                ", carryObject=" + carryObject +
                ", list=" + list +
                ", style='" + style + '\'' +
                ", value='" + value + '\'' +
                ", selectValue='" + selectValue + '\'' +
                ", spanSize=" + spanSize +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.paramType);
        dest.writeString(this.titile);
        dest.writeString(this.field);
        dest.writeString(this.defaultValue);
        dest.writeInt(this.ordernum);
        dest.writeString(this.type);
        dest.writeString(this.category);
        dest.writeByte(this.edit ? (byte) 1 : (byte) 0);
        dest.writeByte(this.require ? (byte) 1 : (byte) 0);
        dest.writeString(this.changeType);
        dest.writeParcelable((Parcelable) this.carryObject, flags);
        dest.writeList(this.list);
        dest.writeString(this.style);
        dest.writeString(this.value);
        dest.writeString(this.selectValue);
        dest.writeInt(this.spanSize);
        dest.writeInt(this.level);
    }

    protected RecordsBean(Parcel in) {
        this.paramType = in.readInt();
        this.titile = in.readString();
        this.field = in.readString();
        this.defaultValue = in.readString();
        this.ordernum = in.readInt();
        this.type = in.readString();
        this.category = in.readString();
        this.edit = in.readByte() != 0;
        this.require = in.readByte() != 0;
        this.changeType = in.readString();
        this.carryObject = in.readParcelable(Object.class.getClassLoader());
        this.list = new ArrayList<RecordsBean>();
        in.readList(this.list, RecordsBean.class.getClassLoader());
        this.style = in.readString();
        this.value = in.readString();
        this.selectValue = in.readString();
        this.spanSize = in.readInt();
        this.level =in.readInt();
    }

    public static final Parcelable.Creator<RecordsBean> CREATOR = new Parcelable.Creator<RecordsBean>() {
        @Override
        public RecordsBean createFromParcel(Parcel source) {
            return new RecordsBean(source);
        }

        @Override
        public RecordsBean[] newArray(int size) {
            return new RecordsBean[size];
        }
    };
}
