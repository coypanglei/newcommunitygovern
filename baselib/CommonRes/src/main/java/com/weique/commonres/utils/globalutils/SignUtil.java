package com.weique.commonres.utils.globalutils;

import com.jess.arms.utils.ArmsUtils;
import com.weique.commonres.constans.Constants;
import com.weique.commonres.utils.commonutils.StringUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 验签 工具
 *
 * @author Administrator
 */
public class SignUtil {
//    /**
//     * 通用  添加公共参数 和 验签
//     *
//     * @param map
//     * @return Map
//     */
//    public static Map<String, Object> addParamSign(Map<String, Object> map) {
//
//        if (map == null) {
//            map = new HashMap<>();
//        } else {
//            Iterator<Map.Entry<String, Object>> entryIterator = map.entrySet().iterator();
//            //去除为空的数据
//            while (entryIterator.hasNext()) {
//                Map.Entry<String, Object> next = entryIterator.next();
//                if (next.getValue() == null || StringUtil.isNullString(next.getValue().toString())) {
//                    entryIterator.remove();
//                }
//            }
//        }
//        return map;
//    }

    /**
     * 通用  添加公共参数
     *
     * @param map
     * @return
     */
    public static Map<String, Object> addParamSign(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>(10);
        }
        try {
            if (UserInfoUtils.getInstance().getUserInfo() != null) {
                map.put(Constants.USER_ID, StringUtil.setText(UserInfoUtils.getInstance().getUserInfo().getUid()));
                map.put("UserId", StringUtil.setText(UserInfoUtils.getInstance().getUserInfo().getUid()));
                map.put(Constants.TOKEN, StringUtil.setText(UserInfoUtils.getInstance().getUserInfo().getToken()));
                map.put(Constants.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
                map.put(Constants.NONCE, String.valueOf(Math.random()));

                Iterator<Map.Entry<String, Object>> entryIterator = map.entrySet().iterator();
                boolean hasDepartmentId = false;
                while (entryIterator.hasNext()) {
                    Map.Entry<String, Object> next = entryIterator.next();
                    if (next.getValue() == null) {
                        entryIterator.remove();
                    }
                    if (!hasDepartmentId && Constants.DEPARTMENT_ID.equals(next.getKey())) {
                        if (StringUtil.isNotNullString(next.getValue().toString())) {
                            hasDepartmentId = true;
                        }
                    }
                }
                if (!hasDepartmentId) {
                    map.put(Constants.DEPARTMENT_ID, (UserInfoUtils.getInstance().getUserInfo().getDepartmentId()));
                }
                Map<String, Object> newMap = new TreeMap<>(map);
                String newMapString = newMap.toString().replaceAll(" ", "");
                String sign = ArmsUtils.encodeToMD5(newMapString).toUpperCase();
                newMap.put(Constants.SIGNATURE, sign);
                return newMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
