package com.huyong.study.myserver;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author huyong
 * @date 2020-08-19 9:54 下午
 */
public class Response implements IResponse {
    private String body;

    private String data;

    private int status = 200;

    private Map<String, String> headers;

    private OutputStream outputStream;

    public Response(final OutputStream outputStream) {
        headers = new HashMap<>();
        headers.put("Content-type", "text/html;charset=UTF-8");
        this.outputStream = outputStream;
    }


    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(status).append(" OK")
                .append("\r\n")
                .append("Date: ").append(new Date())
                .append("\r\n");
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> item : headers.entrySet()) {
                sb.append(item.getKey()).append(": ").append(item.getValue()).append("\r\n");
            }
        }
        sb.append("\r\n\r\n");
        sb.append(data);
        return sb.toString();
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public void flush() {
        try {
            outputStream.write(getBody().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
