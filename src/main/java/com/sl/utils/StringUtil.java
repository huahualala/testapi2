package com.sl.utils;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class StringUtil {
    public static boolean isStringInArray(String str, String[] array){
        for (String val:array){
            if(str.equals(val)){
                return true;
            }
        }
        return false;
    }
}
