package pmp.tianxundai.com.notepaddemo.fgt;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.necer.ncalendar.calendar.MonthCalendar;
import com.necer.ncalendar.listener.OnMonthCalendarChangedListener;
import org.joda.time.LocalDate;
import java.util.List;
import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.aty.DayAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：视图界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.fgt_viewport)
public class ViewportFgt extends BaseFgt {
    private TextView title_text;
    private MonthCalendar monthcalendar;
    private TextView dateText;
    private TextView tv_month;
    private TextView tv_date;
    private String data_string;
    private Button button_day;
    private Button buton_aty;
    private String stringData;
    private LinearLayout viewport_ll;


    @Override
    public void initViews() {
        viewport_ll = findViewById(R.id.viewport_ll);
        title_text = findViewById(R.id.title_text);
        dateText = findViewById(R.id.tv_date);
        tv_month = findViewById(R.id.tv_month);
        tv_date = findViewById(R.id.tv_date);
        button_day = findViewById(R.id.button_day);
        buton_aty = findViewById(R.id.buton_aty);
        monthcalendar = findViewById(R.id.monthcalendar);

        //默认选中与不选中的设置，其他完全一样
        monthcalendar.setDefaultSelect(false);

    }

    @Override
    public void initDatas() {
        viewport_ll.setY(me.getStatusBarHeight());
        title_text.setText("视图界面");
    }

    @Override
    public void setEvents() {
        //适配器的点击事件
        monthcalendar.setOnMonthCalendarChangedListener(new OnMonthCalendarChangedListener() {
            @Override
            public void onMonthCalendarChanged(LocalDate dateTime) {
                tv_month.setText(dateTime.getMonthOfYear() + "月");
                tv_date.setText(dateTime.getYear() + "年" + dateTime.getMonthOfYear() + "月" + dateTime.getDayOfMonth() + "日");
                data_string = dateTime.getMonthOfYear() + "月" + dateTime.getDayOfMonth() + "日";
                buton_aty.setText("跳转到" + data_string + "这天的日志");
                stringData = dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth();
            }
        });
        //回到今天
        button_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthcalendar.toToday();
            }
        });
        //天转到那一天
        buton_aty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(buton_aty.getText().toString().trim());
                Log.d("zdl","================="+stringData);
                List<StudentsBean> students = studentsBeanDao
                        .queryBuilder()
                        .where(StudentsBeanDao.Properties.Data.eq(stringData))
                        .list();
                toast(students.toString());
                jump(DayAty.class,new JumpParameter().put("students",students));
            }
        });
    }
}
