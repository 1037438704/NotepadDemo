package pmp.tianxundai.com.notepaddemo.aty;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter3;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
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
    private TextView title;
    private EditText editText;
    private String time;
    private Button button_baocun;
    private RecyclerView title_rv, richeng_rv;
    private TextView basic_tv, title_text, day_tv, finish_tv;
    private String string;
    private List<DataBean> list1, list2;
    private TemplateAdapter3 templateAdapter, templateAdapter2;
    private int count;
    private int counitme1;
    private int counitme2;
    private int countitem3;
    private String fanhui;
    private int fanhui2 = 1;
    private View view;
    private EditText dialog_edtext;
    private AlertDialog dialog1, dialog2;
    private TextView tixing;
    private String getTheEventContent, getDataContent;


    @Override
    public void initViews() {
        finish_tv = findViewById(R.id.finish_tv);
        title = findViewById(R.id.title);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        basic_tv = findViewById(R.id.basic_tv);
        tixing = findViewById(R.id.tixing);
        finish_tv = findViewById(R.id.finish_tv);
        button_baocun = findViewById(R.id.button_baocun);
        title_rv = findViewById(R.id.title_rv);
        richeng_rv = findViewById(R.id.richeng_rv);
        title_text = findViewById(R.id.title_text);
        day_tv = findViewById(R.id.day_tv);
        view = getLayoutInflater().inflate(R.layout.dialog_ed, null);
        dialog_edtext = view.findViewById(R.id.dialog_edtext);
        richeng_rv.setLayoutManager(new LinearLayoutManager(me));
        title_rv.setLayoutManager(new LinearLayoutManager(me));
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        if (!paramer.getString("getTheEventContent").equals("")) {
            getTheEventContent = paramer.getString("getTheEventContent");
        }
        if (!paramer.getString("getDataContent").equals("")) {
            getDataContent = paramer.getString("getDataContent");
        }
        Log.d("传过来的数据", "" + getTheEventContent +"==========="+ getDataContent);
    }

    @Override
    public void setEvents() {
        finish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(Main2Activity.class);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(getTheEventContent)){
            Log.d("zdl", "=========模板界面显示========" + getTheEventContent);
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            list1 = gson.fromJson(getTheEventContent, listtype);
            if (list1 == null) {
                return;
            }
            Log.d("zdl", "=========dataBeanlist1========" + list1.toString());
            templateAdapter = new TemplateAdapter3(R.layout.item_date_layout, list1);
            title_rv.setAdapter(templateAdapter);
        }
        if (!TextUtils.isEmpty(getDataContent)){
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            list2 = gson.fromJson(getDataContent, listtype);
            if (list2 == null) {
                return;
            }
            Log.d("zdl", "=========dataBeanlist2========" + list2.toString());
            templateAdapter2 = new TemplateAdapter3(R.layout.item_date_layout, list2);
            richeng_rv.setAdapter(templateAdapter2);
            return;
        }
    }
}
