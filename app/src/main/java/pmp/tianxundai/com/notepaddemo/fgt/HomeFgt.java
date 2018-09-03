package pmp.tianxundai.com.notepaddemo.fgt;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnResponseListener;
import com.kongzue.baseframework.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.HomeAdapter;
import pmp.tianxundai.com.notepaddemo.adapter.IncidentAdapter;
import pmp.tianxundai.com.notepaddemo.aty.DayItemAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;
import pmp.tianxundai.com.notepaddemo.utils.DataString;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：首页
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.fgt_home)
public class HomeFgt extends BaseFgt {
    private LinearLayout ll_layout;
    private ImageView add_image;
    private TextView main_text_title;
    private RecyclerView home_rv, home_rv_data;
    private List<String> list2;
    private List<DataBean> list;
    private View view;
    private EditText dialog_edtext;
    private HomeAdapter homeAdapter, homeAdapter2;
    private AlertDialog dialog;
    private int count;
    private IncidentAdapter homeAdapter333;
    private List<StudentsBean> students;
    private String time, context,itemcontext;
    private int tag;


    @Override
    public void initViews() {
        add_image = findViewById(R.id.add_image);
        ll_layout = findViewById(R.id.ll_layout);
        home_rv = findViewById(R.id.title_rv);
        home_rv_data = findViewById(R.id.home_rv_data);
        main_text_title = findViewById(R.id.title_text);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        students = new ArrayList<>();
        home_rv.setLayoutManager(new LinearLayoutManager(me));
        home_rv_data.setLayoutManager(new GridLayoutManager(me, 3));
        view = getLayoutInflater().inflate(R.layout.dialog_ed, null);
        dialog_edtext = view.findViewById(R.id.dialog_edtext);
        dialog_edtext.setText("");
    }

    @Override
    public void initDatas() {
        ll_layout.setY(me.getStatusBarHeight());
        main_text_title.setText(DataString.StringData());
        //对话框
        duihuakuang();
        //时间
        for (int i = 0; i < 3; i++) {
            String s = new StringBuffer(DataString.StringYue()).append("-").append(DataString.IntDay() + i).toString();
            list2.add(s);
        }


        homeAdapter2 = new HomeAdapter(R.layout.item_data_time, list2);
        home_rv_data.setAdapter(homeAdapter2);
    }


    @Override
    public void setEvents() {
        //添加
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = 1;
                dialog.show();
            }
        });

        //首页顶部的时间按钮
        homeAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                toast("先择了" + list2.get(position).toString());
                count = position;
                if (homeAdapter333 != null) {
                    students.clear();
                    shujuhuoqu();
                }
            }
        });

    }


    private void duihuakuang() {
        dialog = new AlertDialog.Builder(me)
                .setTitle("请输入标题名")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog_edtext.getText().toString().trim().equals("")) {
                            toast("标题名不能为空");
                            return;
                        }
                        Log.d("zdl", "" + dialog_edtext.getText().toString().trim().toString());
                        Log.d("ccc", list2.get(count));
                        if (tag == 1) {
                            long insert = studentsBeanDao.insert(new StudentsBean() {
                                {
                                    this.setTitle(dialog_edtext.getText().toString().trim());
                                    this.setTime(DataString.StringShiJian());
                                    this.setData(list2.get(count));
                                    //将输入框清空
                                    dialog_edtext.setText("");
                                }
                            });
                            if (insert > 0) {
                                toast("添加成功");
                            } else {
                                toast("添加失败");
                            }
                            shujuhuoqu();
                        } else if (tag == 2) {
                            //修改标题名
                            StudentsBean student = studentsBeanDao
                                    .queryBuilder()
                                    .where(StudentsBeanDao.Properties.Title.eq(itemcontext))
                                    .unique();
                            if (null != student) {
                                student.setTitle(dialog_edtext.getText().toString().trim());
                                studentsBeanDao.update(student);
                                Log.d("xiugaichenggong",""+student.toString());
                                homeAdapter333.notifyDataSetChanged();
                            } else {
                                toast("要修改的数据不存在!!");
                            }
                        }

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
    public void onResume() {
        super.onResume();
        shujuhuoqu();
        homeAdapter333.notifyDataSetChanged();
        if (time == null) {
            return;
        }
        if (context == null) {
            return;
        }

        Log.d("zdl", "=======================" + time + "=======" + context);
        StudentsBean student = studentsBeanDao
                .queryBuilder()
                .where(StudentsBeanDao.Properties
                        .Time.eq(time))
                .unique();
        if (null != student) {
            student.setTheEventContent(context);
            studentsBeanDao.update(student);
            toast("修改成功" + student.toString());
        } else {
            toast("数据添加失败!");
        }
    }

    private void shujuhuoqu() {
        if (studentsBeanDao.queryBuilder().list() != null) {
            students = studentsBeanDao
                    .queryBuilder()
                    .where(StudentsBeanDao.Properties.Data.eq(list2.get(count).toString()))
                    .list();
            homeAdapter333 = new IncidentAdapter(R.layout.item_home, students);
            home_rv.setAdapter(homeAdapter333);
            //item 点击事件
            homeAdapter333.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    StudentsBean student = studentsBeanDao
                            .queryBuilder()
                            .where(StudentsBeanDao.Properties.Title.eq(students.get(position).getTitle()))
                            .unique();
                    Log.d("student",""+student.toString());
                    String str1 = "";
                    String str2 = "";
                    str1 = student.getTheEventContent() + "";
                    str2 = student.getDataContent() + "";
                    //查询并传输数据

                    if (str1 == null && str2 == null) {
                        toast("目前还没有日志，去事件中添加吧");
                        return;
                    }
                    jump(DayItemAty.class, new JumpParameter()
                                    .put("time", students.get(position).getTime())
                                    .put("getTheEventContent", str1)//事件消息
                                    .put("getDataContent", str2)//天数消息
                            , new OnResponseListener() {
                                @Override
                                public void OnResponse(JumpParameter jumpParameter) {

                                }
                            });
                }
            });
            //item长按点击 点击事件
            homeAdapter333.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    toast("事件触发了");
                    tag = 2;
                    itemcontext = students.get(position).getTitle();
                    dialog.show();
                    return true;
                }
            });
        }
    }
}
