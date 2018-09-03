package pmp.tianxundai.com.notepaddemo.aty;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.utils.CountInteUtils;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：模板界面的日程模板
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_schedule)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class ScheduleAty extends BaseAty {
    private TextView finish, title_text;
    private RecyclerView basic_rv;
    private List<DataBean> list;
    private TemplateAdapter templateAdapter;
    private ImageView add_image;
    private Button baocun;
    private TimePickerView pvTime;
    private int countitem;
    private int fanhui = 2;
    private AlertDialog dialog;
    private View view;
    private EditText dialog_edtext;
    private int count;

    @Override
    public void initViews() {
        finish = findViewById(R.id.finish);
        title_text = findViewById(R.id.title_text);
        basic_rv = findViewById(R.id.basic_rv);
        add_image = findViewById(R.id.add_image);
        baocun = findViewById(R.id.baocun);
        list = new ArrayList<>();
        basic_rv.setLayoutManager(new LinearLayoutManager(me));
        view = getLayoutInflater().inflate(R.layout.dialog_ed, null);
        dialog_edtext = view.findViewById(R.id.dialog_edtext);
    }

    @Override
    public void initDatas(JumpParameter paramer) {

        title_text.setText("日程模板");
        for (int i = 0; i < 3; i++) {
            list.add(new DataBean(i, null));
        }
        templateAdapter = new TemplateAdapter(R.layout.item_date_layout, list);
        basic_rv.setAdapter(templateAdapter);
//        //在这里先添加第一条数据库的数据  存日常模板
        //时间选择
        tiem();
        //对话框
        dialog = new AlertDialog.Builder(me)
                .setTitle("请输入标题名")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog_edtext.getText().toString().trim().toString() == null) {
                            toast("标题名不能为空");
                            return;
                        }
                        //返回插入数据实体类
                        list.get(count).setItmedata(dialog_edtext.getText().toString().trim());
                        dialog_edtext.setText("");
                        templateAdapter.notifyDataSetChanged();
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
    public void setEvents() {
        //销毁此界面
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //添加item对象
//        add_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                list.add(new DataBean(list.size() + 1, null));
////                templateAdapter.notifyDataSetChanged();
//                list.add(new DataBean(list.size() + 1, null));
//                templateAdapter.notifyDataSetChanged();
//            }
//        });
        //保存
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String str = gson.toJson(list);
                Preferences.getInstance().set(me, "user", "richeng", str);
                setResponse(new JumpParameter().put("fanhui",fanhui));
                finish();
            }
        });
        //item点击事件
        templateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                count = position;
//                dialog.show();
                countitem = position;
                pvTime.show();
            }
        });
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(date);
    }

    private void tiem() {
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                //这里存入集合
                list.get(countitem).setItmedata(time);
                templateAdapter.notifyDataSetChanged();
                toast(time);
            }
        })
                .setType(new boolean[]{false, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确定")
                .isCenterLabel(false)
                .build();
    }


}
