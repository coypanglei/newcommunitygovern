package com.weique.commonservice.zongzhi.bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author GK
 * @description: 用户信息
 * @date :2020/6/24 9:33
 */
public class UserInfoBean {


    /**
     * uid : def0ac7e-dd60-46a4-b6ff-dab80d235c05
     * Name : 2020
     * HeadUrl : /Uploads/CustomerData/image/IMG_20200716_10314940637304923110038243.jpeg
     * Gender : 男
     * Sum : 3
     * BirthDate : 1946-07-09
     * roleName : 网格员、管理员、网格书记
     * DepartmentId : 909d7b2c-9cfb-43b9-9198-7e762db42c3d
     * Tel : 15996266683
     * SID : 32032116666666
     * FullPath : 徐州市沛县/苏慕文街道/苏慕文社区/
     * DepartName : 安国镇市场监管
     * PartyDepartmentName : 江苏支部1
     * DepartmentName : 苏慕文社区
     * EnumCommunityLevel : 1
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJJZCI6ImRlZjBhYzdlLWRkNjAtNDZhNC1iNmZmLWRhYjgwZDIzNWM wNSIsIlVzZXJOYW1lIjoiMjAyMCIsIm5vbmNlIjoiM2UwNmYyNDMtNjUwOS00NGM3LWJjZmMtOGU2MGMyMGMxYTM3In0.KFHGvlQSnrNO8ttEt 4yHgP3KYnv7VSeVd-UNHtZcAwc
     */

    private String uid;
    private String Name;
    private String HeadUrl;
    private String Gender;
    private int Sum;
    private String BirthDate;
    private String roleName;
    private String DepartmentId;
    private String Tel;
    private String SID;
    private String FullPath;
    private String DepartName;
    private String PartyDepartmentName;
    private String DepartmentName;
    private int EnumCommunityLevel;
    private String token;

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.toString().replace("null", "").replace(" ", "")
                .equals(obj.toString().replace("null", "").replace(" ", ""));
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String HeadUrl) {
        this.HeadUrl = HeadUrl;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getSum() {
        return Sum;
    }

    public void setSum(int Sum) {
        this.Sum = Sum;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String DepartmentId) {
        this.DepartmentId = DepartmentId;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getFullPath() {
        return FullPath;
    }

    public void setFullPath(String FullPath) {
        this.FullPath = FullPath;
    }

    public String getDepartName() {
        return DepartName;
    }

    public void setDepartName(String DepartName) {
        this.DepartName = DepartName;
    }

    public String getPartyDepartmentName() {
        return PartyDepartmentName;
    }

    public void setPartyDepartmentName(String PartyDepartmentName) {
        this.PartyDepartmentName = PartyDepartmentName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }

    public int getEnumCommunityLevel() {
        return EnumCommunityLevel;
    }

    public void setEnumCommunityLevel(int EnumCommunityLevel) {
        this.EnumCommunityLevel = EnumCommunityLevel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "uid='" + uid + '\'' +
                ", Name='" + Name + '\'' +
                ", HeadUrl='" + HeadUrl + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Sum=" + Sum +
                ", BirthDate='" + BirthDate + '\'' +
                ", roleName='" + roleName + '\'' +
                ", DepartmentId='" + DepartmentId + '\'' +
                ", Tel='" + Tel + '\'' +
                ", SID='" + SID + '\'' +
                ", FullPath='" + FullPath + '\'' +
                ", DepartName='" + DepartName + '\'' +
                ", PartyDepartmentName='" + PartyDepartmentName + '\'' +
                ", DepartmentName='" + DepartmentName + '\'' +
                ", EnumCommunityLevel=" + EnumCommunityLevel +
                ", token='" + token + '\'' +
                '}';
    }
}
