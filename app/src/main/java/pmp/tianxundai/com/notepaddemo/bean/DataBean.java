package pmp.tianxundai.com.notepaddemo.bean;

/**
 * Created by dell-pc on 2018/8/21.
 */

public class DataBean {

    /**
     * count : 0
     * itmedata : 08-22
     */

    private int count;
    private String itmedata;
    private String itemright;

    public DataBean() {
    }

    public DataBean(int count, String itmedata) {
        this.count = count;
        this.itmedata = itmedata;
    }

    public DataBean(int count, String itmedata, String itemright) {
        this.count = count;
        this.itmedata = itmedata;
        this.itemright = itemright;
    }

    public String getItemright() {
        return itemright;
    }

    public void setItemright(String itemright) {
        this.itemright = itemright;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItmedata() {
        return itmedata;
    }

    public void setItmedata(String itmedata) {
        this.itmedata = itmedata;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "count=" + count +
                ", itmedata='" + itmedata + '\'' +
                ", itemright='" + itemright + '\'' +
                '}';
    }
}
