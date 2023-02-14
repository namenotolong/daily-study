package com.huyong.study.algorithm.leetcode.middle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchTest {

    static final Pattern FIRST_TITLE = Pattern.compile("[\\s\\S]*?<h2>([\\s\\S]*)?</h2><h2");

    static final Pattern SECOND_TITLE = Pattern.compile("[\\s\\S]*?<h2 class=\"h2\">([\\s\\S]*)?</h2>");

    static final Pattern THREE_TITLE = Pattern.compile("[\\s\\S]*?<div>([\\s\\S]*)?</div>");

    static final Pattern ROOT = Pattern.compile("[\\s\\S]*?<div class=\"mask mask2\"></div>([\\s\\S]*)?<div id=\"console\"[\\s\\S]*");

    static final int SERO = 37994;

    static final String URL_PATTERN = "https://www.hetushu.com/book/53/%d.html";

    static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMillis(5000))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public static void test(int count) throws IOException, InterruptedException {
        String url = String.format(URL_PATTERN, SERO + count);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(5009))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        Matcher matcher = ROOT.matcher(response.body());
        if (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
            Matcher tempMather = FIRST_TITLE.matcher(group);
            if (tempMather.find()) {
                System.out.println(tempMather.group(1));
            }
            tempMather = SECOND_TITLE.matcher(group);
            if (tempMather.find()) {
                System.out.println(tempMather.group(1));
            }

            tempMather = THREE_TITLE.matcher(group);
            if (tempMather.find()) {
                System.out.println(tempMather.group(1)
                        .replaceAll("<div>", "\r\n")
                        .replaceAll("</div>", "")
                );
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //1 - 469
        //current 1
        test(1);
    }
}
