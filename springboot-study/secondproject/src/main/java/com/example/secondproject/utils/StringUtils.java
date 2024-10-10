package com.example.secondproject.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class StringUtils {
    public static String removeTags(String str) {
        if(Strings.isEmpty(str)) {
            return str;
        }

        return Pattern.compile("<.+?>").matcher(str).replaceAll("");
    }

    public static String setDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String ldtStr = now.format(dtFmt);
        return ldtStr;
    }
}
