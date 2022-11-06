package cn.com.code4fun.learn.tomcat;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @className: ManualResponse
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 13:40
 * @version: 0.0.1
 */
public class ManualResponse {
    private OutputStream outputStream;
    public ManualResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String s) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 200 ok\n")
                .append("content-type: text/html; \n")
                .append("\r\n")
                .append(s);
        outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));

    }
}
