package pmp.tianxundai.com.notepaddemo.aty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;

import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.DayAdapter;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：日历跳转到此天数据显示
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_day)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class DayAty extends BaseAty {
    private TextView finish;
    private RecyclerView rv_day;

    @Override
    public void initViews() {
        finish = findViewById(R.id.finish);
        rv_day = findViewById(R.id.rv_day);
        rv_day.setLayoutManager(new LinearLayoutManager(me));
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        if (paramer == null) {
            return;
        }
        List<StudentsBean> students = (List<StudentsBean>) paramer.get("students");
        Log.d("zdl", students.toString());
        DayAdapter dayAdapter = new DayAdapter(R.layout.item_layout, students);
        rv_day.setAdapter(dayAdapter);

    }

    @Override
    public void setEvents() {
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
