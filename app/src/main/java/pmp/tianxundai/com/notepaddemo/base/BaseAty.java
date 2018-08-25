package pmp.tianxundai.com.notepaddemo.base;

import android.os.Bundle;

import com.kongzue.baseframework.BaseActivity;

import pmp.tianxundai.com.notepaddemo.MyApplication;
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
    }
}
