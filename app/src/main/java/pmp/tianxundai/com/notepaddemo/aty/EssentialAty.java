package pmp.tianxundai.com.notepaddemo.aty;

import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/19 8:19
 * 功能描述：日程信息界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_essential)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class EssentialAty extends BaseAty {
    private TextView text_1,text_2,text_3,text_4;

    @Override
    public void initViews() {
        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);
        text_4 = findViewById(R.id.text_4);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        if (paramer == null){
            return;
        }
        StudentsBean students = (StudentsBean) paramer.get("students");

        text_1.setText("日期:"+students.getData());
        text_2.setText("时间:"+students.getTime());
        text_3.setText("标题:"+students.getTitle());
        text_4.setText("内容:"+students.getTheEventContent());
    }

    @Override
    public void setEvents() {

    }
}
