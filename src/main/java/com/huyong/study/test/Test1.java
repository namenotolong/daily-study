package com.huyong.study.test;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-28 1:51 下午
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        String str = "no_limit_query_1600912095719.tag\tno_limit_query_1600912095719.cnt\tno_limit_query_1600912095719.old_time\tno_limit_query_1600912095719.new_time\tno_limit_query_1600912095719.old_seller_id\tno_limit_query_1600912095719.new_seller_id\tno_limit_query_1600912095719.old_rule_id\tno_limit_query_1600912095719.new_rule_id\tno_limit_query_1600912095719.old_amount\tno_limit_query_1600912095719.new_amount";
        String[] split = str.split("\t");
        System.out.println(Arrays.toString(split));
        for (final String s : split) {
            System.out.println(isTableHeader(s));
        }
        ResultStat resultStat = matchHiveCliRowAndSeconds("Time taken: 0.146 seconds, Fetched: 7 row(s)");
        System.out.println(resultStat);
    }
    public static boolean isTableHeader(String line) {
        if (StringUtils.isBlank(line)) {
            return false;
        }
        String split[] = line.split("\t");
        for (String str : split) {
            if (str.matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)?")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    protected static Pattern hiveCliRowsPattern = Pattern.compile("(\\d+\\.\\d+) seconds(, Fetched: (\\d+(,\\d+)*) row\\(s\\))?");
    public static ResultStat matchHiveCliRowAndSeconds(String line) {
        Matcher rowM = hiveCliRowsPattern.matcher(line);
        if (rowM.find()) {
            int rows = 0;
            String rowV = rowM.group(3);
            if(rowV != null) {
                rows = Integer.parseInt(rowV.replace(",", ""));
            }
            return new ResultStat(rows, Double.parseDouble(rowM.group(1)), ResultStatType.selected);
        }
        return null;
    }
    protected enum ResultStatType { affected, selected }
    protected enum LineType {
        ERROR_LINE, INFO_LINE, ROW_AFFACTED_LINE, ROW_SELECTED_LINE, NORMAL_LINE, AFFTER_ERROR_LINE
    }
    protected static class ResultStat {
        int rows = 0;
        double seconds = 0;
        ResultStatType resultStatType = null;

        public ResultStat(int rows, double seconds, String resultStatType) {
            this(rows, seconds, ResultStatType.valueOf(resultStatType));
        }

        @Override
        public String toString() {
            return "ResultStat{" +
                    "rows=" + rows +
                    ", seconds=" + seconds +
                    ", resultStatType=" + resultStatType +
                    '}';
        }

        public ResultStat(int rows, double seconds, ResultStatType resultStatType) {
            this.rows = rows;
            this.seconds = seconds;
            this.resultStatType = resultStatType;
        }

        public LineType lineType() {
            switch (resultStatType) {
                case selected:
                    return LineType.ROW_SELECTED_LINE;
                case affected:
                default:
                    return LineType.ROW_AFFACTED_LINE;
            }
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ResultStat that = (ResultStat) o;
            return rows == that.rows && Double.compare(that.seconds, seconds) == 0 && resultStatType == that.resultStatType;
        }

        @Override public int hashCode() {

            return Objects.hash(rows, seconds, resultStatType);
        }
    }
}
