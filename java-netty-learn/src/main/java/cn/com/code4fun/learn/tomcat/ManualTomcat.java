package cn.com.code4fun.learn.tomcat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @className: ManualTomcat
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 13:51
 * @version: 0.0.1
 */
public class ManualTomcat {
    private Integer port = 8088;
    private ServerSocket serverSocket;
    private Map<String, ManualServlet> servletMap = new HashMap<>(16);
    private Properties webProperties = new Properties();

    private void init() throws Exception {
        String path = this.getClass().getResource("/").getPath();
        FileInputStream fis = new FileInputStream(path + "web.properties");
        webProperties.load(fis);
        Set<Object> keySet = webProperties.keySet();
        for (Object o : keySet) {
            String key = o.toString();
            if (key.endsWith(".url")) {
                String servletName = key.replaceAll("\\.url$", "");
                String url = webProperties.getProperty(key);
                String className = webProperties.getProperty(servletName + ".className");
                ManualServlet servlet = (ManualServlet)Class.forName(className).newInstance();
                servletMap.put(url, servlet);
            }
        }

    }

    public void start() {
        try {
            init();
            serverSocket = new ServerSocket(port);
            System.out.println("Manaual tomcat 已启动。。。");
            while (true) {
                Socket client = serverSocket.accept();
                processClientRequest(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理客户端请求
     * @param client
     * @throws Exception
     */
    public void processClientRequest(Socket client) throws Exception {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();
        ManualRequest request = new ManualRequest(inputStream);
        ManualResponse response = new ManualResponse(outputStream);

        String url = request.getUrl();
        if (servletMap.containsKey(url)) {
            servletMap.get(url).service(request, response);
        }else {
            response.write("404- NOT FOUND");
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
        client.close();
    }
}
