package pmp.tianxundai.com.notepaddemo.aty;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.utils.DataString;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：首页item点击事件详情
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_day_item)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class DayItemAty extends BaseAty {
    private TextView finish_tv;
    private TextView title;
    private EditText editText;
    private String time;
    private Button button_baocun;

    @Override
    public void initViews() {
        finish_tv = findViewById(R.id.finish_tv);
        title = findViewById(R.id.title);
        editText = findViewById(R.id.editText);
        button_baocun = findViewById(R.id.button_baocun);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        title.setText(DataString.StringData());
        time = paramer.getString("time");
    }

    @Override
    public void setEvents() {
        finish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResponse(new JumpParameter().put("time", time).put("context", editText.getText().toString().trim()));
                finish();
            }
        });

    }
}
