package cn.com.code4fun.rpc.config;

/**
 * @className: ProviderConfig
 * @desc:
 * @author: niejian
 * @time: 2022/11/18 14:34
 * @version: 0.0.1
 */
public class ProviderConfig {
    private String nozzle;
    private String ref;
    private String alias;

    public void doServiceExport() {
        System.out.format("生产者信息：[接口: %s] [映射：%s] [别名：%s]", getNozzle(), getRef(), getAlias());
    }


    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
