package pmp.tianxundai.com.notepaddemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：数据库
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Entity
public class StudentsBean{
    /**
     * 主id
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * 标题
     */
    @Property()
    private String title;
    /**
     * 日期
     */
    private String data;
    /**
     * 时间
     */
    private String time;
    /**
     * 星期几
     */
    private String week;
    /**
     * 判断是否开启闹铃
     */
    private Boolean aBoolean;
    /**
     * 事件内容
     */
    private String theEventContent;
    /**
     * 天数内容
     * */
    private String dataContent;
    @Generated(hash = 285763601)
    public StudentsBean(Long id, String title, String data, String time,
            String week, Boolean aBoolean, String theEventContent,
            String dataContent) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.time = time;
        this.week = week;
        this.aBoolean = aBoolean;
        this.theEventContent = theEventContent;
        this.dataContent = dataContent;
    }
    @Generated(hash = 2112867845)
    public StudentsBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getData() {
        return this.data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getWeek() {
        return this.week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public Boolean getABoolean() {
        return this.aBoolean;
    }
    public void setABoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
    public String getTheEventContent() {
        return this.theEventContent;
    }
    public void setTheEventContent(String theEventContent) {
        this.theEventContent = theEventContent;
    }
    public String getDataContent() {
        return this.dataContent;
    }
    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    @Override
    public String toString() {
        return "StudentsBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", time='" + time + '\'' +
                ", week='" + week + '\'' +
                ", aBoolean=" + aBoolean +
                ", theEventContent='" + theEventContent + '\'' +
                ", dataContent='" + dataContent + '\'' +
                '}';
    }
}
