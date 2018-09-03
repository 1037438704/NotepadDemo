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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter2;
import pmp.tianxundai.com.notepaddemo.base.BaseAty;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;
import pmp.tianxundai.com.notepaddemo.utils.DataString;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：基本信息显示
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.aty_basic)
@DarkStatusBarTheme(true)           //开启顶部状态栏图标、文字暗色模式
@DarkNavigationBarTheme(true)       //开启底部导航栏按钮暗色模式
@NavigationBarBackgroundColor(a = 0, r = 0, g = 0, b = 0)
//设置底部导航栏背景颜色（a = 0,r = 0,g = 0,b = 0可透明）
public class BasicAty extends BaseAty {
    private TextView finish, title_text;
    private RecyclerView basic_rv;
    private List<DataBean> list;
    private TemplateAdapter2 templateAdapter;
    private ImageView add_image;
    private Button baocun;
    private AlertDialog dialog;
    private View view;
    private EditText dialog_edtext;
    private int count;

    @Override
    public void initViews() {



        //查询所有数据
        //查询所有数据库中的数据
        List<StudentsBean> studentList = studentsBeanDao.queryBuilder().list();
        toast(studentList.toString());

        finish = findViewById(R.id.finish);
        title_text = findViewById(R.id.title_text);
        basic_rv = findViewById(R.id.basic_rv);
        add_image = findViewById(R.id.add_image);
        baocun = findViewById(R.id.baocun);
        list = new ArrayList<>();
        view = getLayoutInflater().inflate(R.layout.dialog_ed, null);
        dialog_edtext = view.findViewById(R.id.dialog_edtext);
        basic_rv.setLayoutManager(new LinearLayoutManager(me));
        for (int i = 0; i < 3; i++) {
            list.add(new DataBean(i, null));
        }
        templateAdapter = new TemplateAdapter2(R.layout.item_date_layout, list);
        basic_rv.setAdapter(templateAdapter);
    }

    @Override
    public void initDatas(JumpParameter paramer) {
        title_text.setText("信息模板");
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
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(new DataBean(list.size() + 1, null));
                templateAdapter.notifyDataSetChanged();

            }
        });
        //保存
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String str = gson.toJson(list);
                Preferences.getInstance().set(me, "user", "xinxi", str);
                //这里创建数据库
                long insert = studentsBeanDao.insert(new StudentsBean() {
                    {
                        this.setTime("基础");
                        this.setTheEventContent(Preferences.getInstance().getString(me, "user", "xinxi"));
                    }
                });
                if (insert > 0) {
                    Toast.makeText(me, "添加成功" + insert, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(me, "添加失败" + insert, Toast.LENGTH_SHORT).show();
                }
                finish();
//                toast("保存成功");
            }
        });
        //item的点击事件
        templateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                count = position;
                dialog.show();
            }
        });
    }
}
