package com.huyong.bigdata;

import com.google.common.base.Splitter;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String str = "1980年 J52《中国科学技术协会第二次全国代表大会》邮票 俗称“蓝飞天”\\n保证原胶全品 价格非常低的谨防二胶下水过的 本店保证无二胶处理的\\n\\n\\n拍前务必需与客服联系，由于本地和多平台同步出售，防止售空，\\n\\n如果未经咨询便拍下无货请退款，概不支持以此理由投诉。\\n\\n500元以上的版票发货都带有版号，请提前知晓。\\n\\n\\nJ52 中国科学技术协会第二次全国代表大会\\n【商品单位】枚\\n【全 套 数】1枚\\n【发行日期】1980-03-15\\n【 规　格 】52mm×31mm\\n【齿孔度数】11.5度\\n【设 计 者】李大玮\\n【 版　别 】影写版\\n【印 刷 厂】北京邮票厂\\n【图案面值】\\n \\n \\n \\n序号";
        String[] split = str.split("\t");
        for (final String s : split) {
            System.out.println(s);
        }
        String[] as = "aabad".split("a");
        System.out.println(as[0]=="");
        for (final String s : Splitter.on("a").split("aabad")) {
            System.out.println(s);
        }
        StringTokenizer token = new StringTokenizer("aaaa");
        System.out.println(token.countTokens());
    }
}
