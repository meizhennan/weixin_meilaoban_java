package com.tencent.wxcloudrun.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeixinUtils {
    public static Map<String,Object> uploadFile( String type) throws Exception {

//        ClassPathResource classPathResource = new ClassPathResource("src/main/resource/static/income_from_operation.png");
//
//        File file = classPathResource.getFile();

        File file = new File("src/main/resource/static/income_from_operation.png");

        // 指定上传的 URL
        URL url = new URL("http://api.weixin.qq.com/cgi-bin/media/upload?type=" + type);

        // 创建 HttpURLConnection 对象
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置请求方法为 POST
        connection.setRequestMethod("POST");

        // 设置请求头信息
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        // 开始组装请求体
        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
        String CRLF = "\r\n";
        StringBuilder requestBodyBuilder = new StringBuilder();

        // 添加文件参数
        requestBodyBuilder.append("--").append(boundary).append(CRLF);
        requestBodyBuilder.append("Content-Disposition: form-data; name=\"media\"; filename=\"").append(file.getName()).append("\"").append(CRLF);
        requestBodyBuilder.append("Content-Type: application/octet-stream").append(CRLF);
        requestBodyBuilder.append(CRLF);

        // 读取文件内容并写入请求体
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            requestBodyBuilder.append(scanner.nextLine()).append(CRLF);
        }

        // 添加请求体结束标志
        requestBodyBuilder.append("--").append(boundary).append("--").append(CRLF);

        // 将请求体转换为字节数组并设置到连接对象中
        byte[] requestBodyBytes = requestBodyBuilder.toString().getBytes();
        connection.setRequestProperty("Content-Length", String.valueOf(requestBodyBytes.length));
        connection.setDoOutput(true);
        connection.getOutputStream().write(requestBodyBytes);

        // 发送请求并获取响应
        int responseCode = connection.getResponseCode();
        String responseBody = new Scanner(connection.getInputStream(), "UTF-8").useDelimiter("\\A").next();

        // 解析响应体并返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("responseCode", responseCode);
        resultMap.put("responseBody", responseBody);

        return resultMap;

    }
}
