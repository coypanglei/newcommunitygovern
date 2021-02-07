package com.weique.commonres.utils.globalutils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.GsonUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class ObjectToMapUtils {


    public static List Object2Map(Object obj) {
        if (obj == null) {
            return Collections.emptyList();
        }

        return GsonUtils.fromJson(GsonUtils.toJson(obj), (Type) String[].class);
    }


    public static Map<String, String> str2Map(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields(); // 获取对象对应类中的所有属性域
        for (int i = 0; i < fields.length; i++) {
            String varName = fields[i].getName();

            ///将key置为大写，默认为对象的属性
            boolean accessFlag = fields[i].isAccessible(); // 获取原来的访问控制权限
            fields[i].setAccessible(true);// 修改访问控制权限
            try {
                if (fields[i].get(obj) instanceof String || fields[i].get(obj) instanceof Double) {
                    String object = String.valueOf(fields[i].get(obj)); // 获取在对象中属性fields[i]对应的对象中的变量
                    if (object != null) {

                        map.put(varName, object);
                    } else {
                        map.put(varName, null);
                    }
                    fields[i].setAccessible(accessFlag);// 恢复访问控制权限
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Map<String, Object> strObjectMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields(); // 获取对象对应类中的所有属性域
        for (int i = 0; i < fields.length; i++) {
            String varName = fields[i].getName();

            ///将key置为大写，默认为对象的属性
            boolean accessFlag = fields[i].isAccessible(); // 获取原来的访问控制权限
            fields[i].setAccessible(true);// 修改访问控制权限
            try {
                if (fields[i].get(obj) instanceof String || fields[i].get(obj) instanceof Double) {
                    String object = String.valueOf(fields[i].get(obj)); // 获取在对象中属性fields[i]对应的对象中的变量
                    if (object != null) {

                        map.put(varName, object);
                    } else {
                        map.put(varName, null);
                    }
                    fields[i].setAccessible(accessFlag);// 恢复访问控制权限
                } else if (fields[i].get(obj) instanceof List) {

                    map.put(varName, fields[i].get(obj));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取属性名数组
     */
    public static String[] getFiledName(final Class o) {
        Field[] fields = o.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {

                fieldNames[i] = fields[i].getName();
                Timber.e(fields[i].getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldNames;
    }


}
