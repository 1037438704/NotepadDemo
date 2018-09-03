package pmp.tianxundai.com.notepaddemo.base;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.util.Preferences;
import com.loonggg.lib.alarmmanager.clock.DataUtlis;

import java.lang.reflect.Type;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.MyApplication;
import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter3;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;

/**
 * @author dell-pc
 * @date 2018/8/17
 */

public abstract class BaseAty extends BaseActivity {
    public StudentsBeanDao studentsBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentsBeanDao = MyApplication.getInstance().getStudentsBeanDao();
        if (!isNull(Preferences.getInstance().getString(me, "user", "naoling"))) {
            String string = Preferences.getInstance().getString(me, "user", "naoling");
            DataUtlis.count = string;
        }else {
            DataUtlis.count = "1";
        }
    }
}
