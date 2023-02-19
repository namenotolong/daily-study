package com.huyong.study.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-07-14 9:06 下午
 */
public class Entity {

    static final String key = "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";

    static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMillis(500000))
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://api.openai.com/v1/completions";
        String content = "写一个关于《身体》的论文";
        Map<String, Object> map = new HashMap<>();
        map.put("model", "text-davinci-003");
        //map.put("model", "text-ada-001");
        map.put("prompt", content);
        map.put("max_tokens", 4000);
        map.put("temperature", 0);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(JSON.toJSONString(map)))
                .uri(URI.create(url))
                .timeout(Duration.ofMillis(500009))
                .header("content-type", "application/json")
                .header("Authorization", "Bearer " + key)
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = JSONObject.parseObject(response.body());
        if (jsonObject.containsKey("error")) {
            System.out.println("happen error, msg:" + jsonObject.getString("message"));
        } else {
            JSONObject firstData = jsonObject.getJSONArray("choices").getJSONObject(0);
            System.out.println(firstData.getString("text"));
            System.out.println("the rabbit response stop reason: " + firstData.getString("finish_reason"));
        }
    }
}
