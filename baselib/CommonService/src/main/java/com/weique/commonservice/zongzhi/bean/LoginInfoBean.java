package com.weique.commonservice.zongzhi.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author GK
 * @description: 登录账号密码信息
 * @date :2020/6/22 16:40
 */
public class LoginInfoBean implements Parcelable {
    private String loginName;
    private String loginPassword;

    protected LoginInfoBean(Parcel in) {
        loginName = in.readString();
        loginPassword = in.readString();
    }

    public LoginInfoBean(String loginName, String loginPassword) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public static final Creator<LoginInfoBean> CREATOR = new Creator<LoginInfoBean>() {
        @Override
        public LoginInfoBean createFromParcel(Parcel in) {
            return new LoginInfoBean(in);
        }

        @Override
        public LoginInfoBean[] newArray(int size) {
            return new LoginInfoBean[size];
        }
    };

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loginName);
        dest.writeString(loginPassword);
    }
}
