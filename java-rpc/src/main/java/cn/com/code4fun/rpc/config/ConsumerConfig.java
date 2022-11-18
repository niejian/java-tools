package cn.com.code4fun.rpc.config;

/**
 * @className: ConsumerConfig
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 15:06
 * @version: 0.0.1
 */
public class ConsumerConfig<T> {
    private String nozzle;
    private String alias;

    public synchronized T refer() {
        System.out.format("消费者信息：[接口：%s] [别名：%s]", getNozzle(), getAlias());
        return null;
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
