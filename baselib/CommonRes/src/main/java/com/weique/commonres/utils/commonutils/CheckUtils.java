package com.weique.commonres.utils.commonutils;

import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.utils.globalutils.UserInfoUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author Administrator
 */
public class CheckUtils {
    /**
     * 检查数据
     *
     * @param map
     * @param recordsBean
     * @return
     */
    public static boolean checkData(Map<String, Object> map, RecordsBean recordsBean) {
        if ("changePassword".equals(recordsBean.getChangeType())) {
            if (Objects.equals(map.get("Pwd"), map.get("OldPwd"))) {
                ArmsUtils.makeText("新密码不能和旧密码一样");
                return false;
            }
            if (!Objects.equals(map.get("resetPwd"), map.get("Pwd"))) {
                ArmsUtils.makeText("再次输入密码不一致");
                return false;
            }
            map.put("OldPwd", ArmsUtils.encodeSHA((String) map.get("OldPwd")).toUpperCase());
            map.put("Pwd", ArmsUtils.encodeSHA((String) map.get("Pwd")).toUpperCase());
        }
        return true;
    }

    /**
     * 修改个人信息
     *
     * @param recordsBean 保存的数据
     */
    public static void saveUserInfo(RecordsBean recordsBean) {
        /*
         * 保存头像
         */
        if ("HeadUrl".equals(recordsBean.getField())) {
            UserInfoUtils.getInstance().getUserInfo().setHeadUrl(recordsBean.getValue());
            /*
             *保存姓名
             */
        }
    }
}
