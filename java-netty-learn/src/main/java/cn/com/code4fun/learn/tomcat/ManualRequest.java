package cn.com.code4fun.learn.tomcat;

import java.io.InputStream;

/**
 * @className: ManualRequest
 * @desc: 自定义tomcat request
 * @author: niejian
 * @time: 2022/11/6 13:01
 * @version: 0.0.1
 */
public class ManualRequest {
    private String method;
    private String url;

    public ManualRequest(InputStream inputStream) {
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = inputStream.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
