package com.confucius.datasource;

public class DynamicDataSourceHolder {

    //默认主库
    private static final String MASTER = "master";

    private static final String OUTSIDEDATA = "slave";

    private static final String SLAVE02 = "slave02";

    private static final String SLAVE03 = "slave03";

    private static final String SLAVE04 = "slave04";
    private static final String SLAVE05 = "slave05";
    private static final String SLAVE06 = "slave06";
    private static final String SLAVE07 = "slave07";

    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 设置数据源key
     *
     * @param key
     */
    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     *
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }

    /**
     * 默认库
     */
    public static void markMaster() {
        putDataSourceKey(MASTER);
    }

    public static void outsideData() {
        putDataSourceKey(OUTSIDEDATA);
    }

    public static void slave02() {
        putDataSourceKey(SLAVE02);
    }

    public static void slave03() {
        putDataSourceKey(SLAVE03);
    }

    public static void slave04() {
        putDataSourceKey(SLAVE04);
    }

    public static void slave05() {
        putDataSourceKey(SLAVE05);
    }

    public static void slave06() {
        putDataSourceKey(SLAVE06);
    }

    public static void slave07() {
        putDataSourceKey(SLAVE07);
    }

}
