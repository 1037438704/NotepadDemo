package pmp.tianxundai.com.notepaddemo.aty;

import android.view.View;
import android.widget.Button;

import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
@Layout(R.layout.activity_main3)
public class Main3Activity extends BaseAty implements View.OnClickListener {
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;

    @Override
    public void initViews() {
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);

        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
    }

    @Override
    public void initDatas(JumpParameter paramer) {

    }

    @Override
    public void setEvents() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                Preferences.getInstance().set(me, "user", "naoling", "1");
                toast("设置成功");
                finish();
                break;
            case R.id.button_2:
                Preferences.getInstance().set(me, "user", "naoling", "2");
                toast("设置成功");
                finish();
                break;
            case R.id.button_3:
                Preferences.getInstance().set(me, "user", "naoling", "3");
                toast("设置成功");
                finish();
                break;
            case R.id.button_4:
                Preferences.getInstance().set(me, "user", "naoling", "4");
                toast("设置成功");
                finish();
                break;
            default:
        }
    }
}
