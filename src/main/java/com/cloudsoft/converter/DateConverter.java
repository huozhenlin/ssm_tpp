package com.cloudsoft.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hzl on 2017/7/7.
 */
public class DateConverter implements Converter<String, Date> {

    @Override

    public Date convert(String source) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            return simpleDateFormat.parse(source);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return null;

    }

}
