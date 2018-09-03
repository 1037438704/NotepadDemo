package pmp.tianxundai.com.notepaddemo.fgt;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import pmp.tianxundai.com.notepaddemo.adapter.IncidentAdapter;
import pmp.tianxundai.com.notepaddemo.adapter.TemplateAdapter3;
import pmp.tianxundai.com.notepaddemo.aty.IncidentAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;
import pmp.tianxundai.com.notepaddemo.utils.DataString;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：事件界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */

@Layout(R.layout.fgt_incident)
public class IncidentFgt extends BaseFgt {
    private ImageView add_image;
    private RecyclerView home_rv;
    //事件列表
    private List<String> list;
    private View view;
    private EditText dialog_edtext;
    private IncidentAdapter homeAdapter;
    private AlertDialog dialog;
    private TextView title_text;
    private int count, tag;
    private String coun,itemcontext;
    private LinearLayout ll_layout;

    @Override
    public void initViews() {
        ll_layout = findViewById(R.id.ll_layout);
        title_text = findViewById(R.id.title_text);
        add_image = findViewById(R.id.add_image);
        home_rv = findViewById(R.id.title_rv);
        list = new ArrayList<>();
        home_rv.setLayoutManager(new LinearLayoutManager(me));
        view = getLayoutInflater().inflate(R.layout.dialog_ed, null);
        dialog_edtext = view.findViewById(R.id.dialog_edtext);
    }

    @Override
    public void initDatas() {
        ll_layout.setY(me.getStatusBarHeight());

        title_text.setText("事件列表");
        shujuhuoqu();
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
                        if (tag == 1){
                            long insert = studentsBeanDao.insert(new StudentsBean() {
                                {
                                    String s = new StringBuffer(DataString.StringYue()).append("-").append(DataString.IntDay()).toString();
                                    this.setData(s);
                                    this.setTitle(dialog_edtext.getText().toString().trim());
                                    this.setTime(DataString.StringShiJian());
                                    dialog_edtext.setText("");
                                }
                            });
                            if (insert > 0) {
                                toast("添加成功");
                            } else {
                                toast("添加失败");
                            }
                        }else if (tag == 2) {
                            //修改标题名
                            StudentsBean student = studentsBeanDao
                                    .queryBuilder()
                                    .where(StudentsBeanDao.Properties.Title.eq(itemcontext))
                                    .unique();
                            if (null != student) {
                                student.setTitle(dialog_edtext.getText().toString().trim());
                                studentsBeanDao.update(student);
                                Log.d("xiugaichenggong",""+student.toString());
                                homeAdapter.notifyDataSetChanged();
                            } else {
                                toast("要修改的数据不存在!!");
                            }
                        }
                        shujuhuoqu();
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
        //添加
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = 1;
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isNull(Preferences.getInstance().getString(me, "user", "shang"))) {
            String string = Preferences.getInstance().getString(me, "user", "shang");
            StudentsBean student = studentsBeanDao
                    .queryBuilder()
                    .where(StudentsBeanDao.Properties.Id.eq(count))
                    .unique();
            if (null != student) {
                student.setTheEventContent(string);
                studentsBeanDao.update(student);
                toast("添加成功");
                Preferences.getInstance().set(me, "user", "shang", "");
            } else {
                toast("要修改的数据不存在!!");
            }
        }
        if (!isNull(Preferences.getInstance().getString(me, "user", "xia"))) {
            String string = Preferences.getInstance().getString(me, "user", "xia");
            StudentsBean student = studentsBeanDao
                    .queryBuilder()
                    .where(StudentsBeanDao.Properties.Id.eq(count))
                    .unique();
            if (null != student) {
                student.setDataContent(string);
                studentsBeanDao.update(student);
                toast("修改成功" + student.toString());
                Preferences.getInstance().set(me, "user", "xia", "");
            } else {
                toast("要修改的数据不存在!!");
            }
        }
        shujuhuoqu();
    }

    private void shujuhuoqu() {
        if (studentsBeanDao.queryBuilder().list() != null) {
            final List<StudentsBean> list = studentsBeanDao.queryBuilder().list();
            homeAdapter = new IncidentAdapter(R.layout.item_home, list);
            home_rv.setAdapter(homeAdapter);
            //item 点击事件
            homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String str1 = "";
                    String str2 = "";
                    jump(IncidentAty.class, new JumpParameter()
                                    .put("count", position + 1)//id
                                    .put("getTheEventContent", str1)//事件消息
                                    .put("getDataContent", str2)//天数消息
                            , new OnResponseListener() {
                                @Override
                                public void OnResponse(JumpParameter jumpParameter) {
                                    if (jumpParameter == null) {
                                        toast("未返回任何数据");
                                    } else {
                                        count = Integer.valueOf(String.valueOf(jumpParameter.get("count")));
                                    }
                                }
                            });
                }
            });
            //item长按点击 点击事件
            homeAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    toast("事件触发了");
                    tag = 2;
                    itemcontext = list.get(position).getTitle();
                    dialog.show();
                    return true;
                }
            });
        }
    }
}
