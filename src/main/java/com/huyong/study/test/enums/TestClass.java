package com.huyong.study.test.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-15 11:46 上午
 */
public class TestClass {
    public static void main(String[] args) {
        System.out.println(getString(null));
       // System.out.println(getSetValues("job_mapping_id,job_namespace,job_name,job_id,job_user,job_owner,job_type,job_priority,job_script_id,job_config,create_date,submit_time,start_time,route_result,job_mode"));
    }
    public static String getString(Job job) {
        switch (job) {
            case ONE: return "one";
            case TWO: return "two";
            default: return null;
        }
    }

    public static String getValues(String arr) {
        String[] split = arr.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            String temp = lineToCamel(s);
            sb.append("#{").append(temp).append("},");
        }
        return sb.toString();
    }

    public static String getSetValues(String arr) {
        String[] split = arr.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            String temp = lineToCamel(s);
            sb.append("<if test=\"").append(temp).append(" != null\">").append("\n\t").append(s).append(" = #{").append(temp).append("},\n" +
                    "</if>\n");
        }
        return sb.toString();
    }

    public static String lineToCamel(String data) {
        char[] chars = data.toCharArray();
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '_') {
                result.add(chars[i]);
            } else {
                if ((i + 1) < chars.length) {
                    result.add(Character.toUpperCase(chars[++i]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : result) {
            sb.append(character);
        }
        return sb.toString();
    }
}
