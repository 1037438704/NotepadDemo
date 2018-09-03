package pmp.tianxundai.com.notepaddemo;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.adapter.FgtAdapter;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.fgt.HomeFgt;
import pmp.tianxundai.com.notepaddemo.fgt.IncidentFgt;
import pmp.tianxundai.com.notepaddemo.fgt.MyFgt;
import pmp.tianxundai.com.notepaddemo.fgt.TemplateFgt;
import pmp.tianxundai.com.notepaddemo.fgt.ViewportFgt;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:534
 * 功能描述：首页
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.activity_main)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class MainActivity extends BaseAty {
    private ViewPager viewPager;
    private TabLayout fragment_tab;
    private List<Fragment> data;
    private List<String> lists;
    private FgtAdapter fgtAdapter;
    private ImageView imageView;

    @Override
    public void initViews() {
        viewPager = findViewById(R.id.viewPager);
        imageView = findViewById(R.id.imageView);
        fragment_tab = findViewById(R.id.fragment_tab);
        lists = new ArrayList<>();
        data = new ArrayList<>();
    }

    @Override
    public void initDatas(JumpParameter paramer) {
//        fragment_tab.setY(getNavbarHeight());
        lists.add("首页");
        lists.add("视图");
        lists.add("事件");
        lists.add("模板");
        lists.add("设置");
        data.add(new HomeFgt());
        data.add(new ViewportFgt());
        data.add(new IncidentFgt());
        data.add(new TemplateFgt());
        data.add(new MyFgt());

        fgtAdapter = new FgtAdapter(me.getSupportFragmentManager(), data, lists);

        viewPager.setAdapter(fgtAdapter);
        // 将ViewPager与TabLayout相关联
        fragment_tab.setupWithViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isNull(Preferences.getInstance().getString(me, "user", "bg"))) {
            String string = Preferences.getInstance().getString(me, "user", "bg");
            Uri uri = Uri.parse(string);
            toast(uri);
            Glide.with(this).load(uri).into(imageView);
        }
    }

    @Override
    public void setEvents() {

    }
}
