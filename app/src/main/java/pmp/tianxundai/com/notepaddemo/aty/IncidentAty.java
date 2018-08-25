package pmp.tianxundai.com.notepaddemo.aty;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnResponseListener;
import com.kongzue.baseframework.util.Preferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter3;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：点击事件后跳转到该界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_incident)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class IncidentAty extends BaseAty {
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

    @Override
    public void initViews() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        basic_tv = findViewById(R.id.basic_tv);
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
        count = (int) paramer.get("count");

        //对话框
        duihuakuang();
        shuju();
    }


    @Override
    public void setEvents() {
        //返回上一页
        finish_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                        if (jumpParameter==null){
                            toast("未返回任何数据");
                        }else{
                            fanhui = String.valueOf(jumpParameter.get("fanhui"));
                            fanhui2 = Integer.valueOf(fanhui);
                        }
                    }
                });
            }
        });
        //保存
        button_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                if (list1.get(0).getItemright() != null || list2.get(0).getItemright() != null) {
                    String str = gson.toJson(list1);
                    Log.d("zdl", "==============list1==================" + str.toString());
                    Preferences.getInstance().set(me, "user", "shang", str);
                    Gson gson2 = new Gson();
                    String str2 = gson2.toJson(list2);
                    Log.d("zdl", "==============list1==================" + str2.toString());
                    Preferences.getInstance().set(me, "user", "xia", str2);
                }
                setResponse(new JumpParameter().put("count",count));
                finish();
            }
        });

    }

    private void shipeiqi1() {
        //适配器点击事件
        templateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                counitme1 = position;
                countitem3 = 1;
                dialog1.show();
            }
        });
    }

    private void shipeiqi2() {
        //适配器点击事件
        templateAdapter2.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                counitme2 = position;
                countitem3 = 2;
                dialog1.show();
            }
        });
    }

    private void shuju() {

    }

    private void duihuakuang() {
        dialog1 = new AlertDialog.Builder(me)
                .setTitle("请输入标题名")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog_edtext.getText().toString().trim().toString() == null) {
                            toast("标题名不能为空");
                            return;
                        }
                        if (countitem3 == 1) {
                            //返回插入数据实体类
                            list1.get(counitme1).setItemright(dialog_edtext.getText().toString().trim());
                            templateAdapter.notifyDataSetChanged();
                        }
                        if (countitem3 == 2) {
                            list2.get(counitme2).setItemright(dialog_edtext.getText().toString().trim());
                            templateAdapter2.notifyDataSetChanged();
                        }
                        dialog_edtext.setText("");

                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("你点击了取消");
                    }
                })
                .create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isNull(Preferences.getInstance().getString(me, "user", "xinxi"))) {
            string = Preferences.getInstance().getString(me, "user", "xinxi");
            Log.d("zdl", "=========模板界面显示========" + string);
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            list1 = gson.fromJson(string, listtype);
            Log.d("zdl", "=========dataBean========" + list1.toString());
            templateAdapter = new TemplateAdapter3(R.layout.item_date_layout, list1);
            title_rv.setAdapter(templateAdapter);
            shipeiqi1();
        }
        if (!isNull(Preferences.getInstance().getString(me, "user", "richeng"))) {
            string = Preferences.getInstance().getString(me, "user", "richeng");
            Log.d("zdl", "=========模板界面显示========" + string);
            Gson gson = new Gson();
            Type listtype = new TypeToken<List<DataBean>>() {
            }.getType();
            list2 = gson.fromJson(string, listtype);
            Log.d("zdl", "=========dataBean========" + list2.toString());
            templateAdapter2 = new TemplateAdapter3(R.layout.item_date_layout, list2);
            richeng_rv.setAdapter(templateAdapter2);
            shipeiqi2();
        }
    }
}
