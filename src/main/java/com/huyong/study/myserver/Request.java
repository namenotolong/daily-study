package com.huyong.study.myserver;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-19 9:54 下午
 */
public class Request implements IRequest {
    private RequestType requestType;
    private String url;
    private String host;
    private InputStream inputStream;
    private Map<String, String> cookies = new HashMap<>();
    private Map<String, String> params = new HashMap<>();

    public Request() {
    }

    public Request(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
        byte[] content = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        if ((len = inputStream.read(content)) > 0) {
            sb.append(new String(content, 0, len));
        }
        String requestContent = URLDecoder.decode(sb.toString(), StandardCharsets.UTF_8.name());
        String[] arr = requestContent.split("\r\n");
        for (int i = 0; i < arr.length; i++) {
            if ("".equals(arr[i].trim())) {
                continue;
            }
            if (i == arr.length - 1 && requestType == RequestType.POST) {
                if (arr.length > 3 && "".equals(arr[arr.length - 2].trim())) {
                    String[] contentParams = arr[arr.length - 1].split("&");
                    for (final String contentParam : contentParams) {
                        String[] keyValue = contentParam.split("=");
                        params.put(keyValue[0], keyValue[1]);
                    }
                }
                break;
            }
            if (i == 0) {
                if (arr[0].startsWith("GET")) {
                    requestType = RequestType.GET;
                    String[] heads = arr[0].split(" ");
                    if (heads.length > 1) {
                        String fullUrl = heads[1];
                        String[] urlAndParams = fullUrl.split("\\?");
                        url = urlAndParams[0];
                        if (urlAndParams.length > 1) {
                            String[] contentParams = urlAndParams[1].split("&");
                            for (final String contentParam : contentParams) {
                                String[] keyValue = contentParam.split("=");
                                params.put(keyValue[0], keyValue[1]);
                            }
                        }
                    } else {
                        url = "/";
                    }
                } else {
                    requestType = RequestType.POST;
                    String[] heads = arr[0].split(" ");
                    if (heads.length > 1) {
                        url = heads[1];
                    } else {
                        url = "/";
                    }
                }
            } else {
                arr[i] = arr[i].replaceAll(" ", "");
                String[] items = arr[i].split(":");
                if (items.length < 2) {
                    continue;
                }
                String value = items[1];
                String key = items[0];
                if (key.startsWith("Host")) {
                    host = value;
                } else if (key.startsWith("Cookie")) {
                    String[] cookieStr = value.split(";");
                    for (final String cookie : cookieStr) {
                        String[] cookieArr = cookie.split("=");
                        cookies.put(cookieArr[0], cookieArr[1]);
                    }
                }
            }
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(final RequestType requestType) {
        this.requestType = requestType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public String getParam(final String name) {
        return params.get(name);
    }

    public void setParams(final Map<String, String> params) {
        this.params = params;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(final Map<String, String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", url='" + url + '\'' +
                ", host='" + host + '\'' +
                ", cookies=" + cookies +
                ", params=" + params +
                '}';
    }

}
