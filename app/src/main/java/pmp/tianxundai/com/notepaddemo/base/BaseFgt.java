package pmp.tianxundai.com.notepaddemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongzue.baseframework.BaseFragment;

import pmp.tianxundai.com.notepaddemo.MyApplication;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;

/**
 * Created by dell-pc on 2018/8/17.
 */

public abstract class BaseFgt extends BaseFragment {
    public static StudentsBeanDao studentsBeanDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        studentsBeanDao = MyApplication.getInstance().getStudentsBeanDao();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
