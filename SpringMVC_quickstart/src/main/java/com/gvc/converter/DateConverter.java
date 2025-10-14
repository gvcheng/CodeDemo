package com.gvc.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {


    @Override
    //source就是表单传递过来的请求参数： "2025-10-11"
    public Date convert(String source) {
        //将字符串转换成日期对象
        Date date = null;
        try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
