package com.johu.spider.spider.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wennan
 * 2018/1/4
 */
public class DateHelper {
    public static String formatDate2Str(Date date){
        if(date !=null){
            SimpleDateFormat daySf = new SimpleDateFormat("yyyyMMdd");
            return daySf.format(date);
        }
        return null;

    }
}
