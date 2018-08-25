package pmp.tianxundai.com.notepaddemo.fgt;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnResponseListener;
import com.kongzue.baseframework.util.Preferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter;
import pmp.tianxundai.com.notepaddemo.aty.BasicAty;
import pmp.tianxundai.com.notepaddemo.aty.ScheduleAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;

/**
 * 创建人： Nine tails fox96
 * 创建时间： 2018/8/17 10:38
 * 功能描述：模板界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.fgt_template)
public class TemplateFgt extends BaseFgt {
    private RecyclerView title_rv, richeng_rv;
    private TextView basic_tv, title_text, day_tv;
    private String string;
    List<DataBean> list = new ArrayList<>();
    private TemplateAdapter templateAdapter,templateAdapter2;

    @Override
    public void initViews() {
        basic_tv = findViewById(R.id.basic_tv);
        title_rv = findViewById(R.id.title_rv);
        richeng_rv = findViewById(R.id.richeng_rv);
        title_text = findViewById(R.id.title_text);
        day_tv = findViewById(R.id.day_tv);
        richeng_rv.setLayoutManager(new LinearLayoutManager(me));
        title_rv.setLayoutManager(new LinearLayoutManager(me));
    }

    @Override
    public void initDatas() {
        title_text.setText("模板界面");
    }

    @Override
    public void setEvents() {
        //添加基本信息模板
        basic_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(BasicAty.class);
            }
        });
        //添加日程模板
        day_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump(ScheduleAty.class, new OnResponseListener() {
                    @Override
                    public void OnResponse(JumpParameter jumpParameter) {
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isNull(Preferences.getInstance().getString(me, "user", "richeng"))) {
            string = Preferences.getInstance().getString(me, "user", "richeng");
            Log.d("zdl", "=========模板界面显示========" + string);
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            List<DataBean> list = gson.fromJson(string, listtype);
            Log.d("zdl", "=========dataBean========" + list.toString());
            templateAdapter = new TemplateAdapter(R.layout.item_date_layout, list);
            richeng_rv.setAdapter(templateAdapter);
        }
        if (!isNull(Preferences.getInstance().getString(me, "user", "xinxi"))) {
            string = Preferences.getInstance().getString(me, "user", "xinxi");
            Log.d("zdl", "=========模板界面显示========" + string);
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            List<DataBean> list = gson.fromJson(string, listtype);
            Log.d("zdl", "=========dataBean========" + list.toString());
            templateAdapter2 = new TemplateAdapter(R.layout.item_date_layout, list);
            title_rv.setAdapter(templateAdapter2);
        }
    }
}
