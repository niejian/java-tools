package cn.com.code4fun.rpc.config;

/**
 * @className: ServerConfig
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 15:10
 * @version: 0.0.1
 */
public class ServerConfig {
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
