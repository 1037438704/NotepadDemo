package pmp.tianxundai.com.notepaddemo.fgt;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.JumpParameter;
import com.kongzue.baseframework.util.OnResponseListener;
import com.kongzue.baseframework.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.IncidentAdapter;
import pmp.tianxundai.com.notepaddemo.aty.IncidentAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;
import pmp.tianxundai.com.notepaddemo.greendao.StudentsBeanDao;

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
    private int count;
    private String coun;

    @Override
    public void initViews() {
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

        if (!isNull(Preferences.getInstance().getString(me, "user", "shang"))){
            String string = Preferences.getInstance().getString(me, "user", "shang");
            StudentsBean student = studentsBeanDao
                    .queryBuilder()
                    .where(StudentsBeanDao.Properties.Id.eq(count))
                    .unique();
            if (null != student) {
                student.setTheEventContent(string);
                studentsBeanDao.update(student);
                toast("修改成功"+student.toString());
            } else {
                toast("要修改的数据不存在!!");
            }
        }
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
                        long insert = studentsBeanDao.insert(new StudentsBean() {
                            {
                                this.setTitle(dialog_edtext.getText().toString().trim());
                                dialog_edtext.setText("");
                            }
                        });
                        if (insert > 0) {
                            toast("添加成功");
                        } else {
                            toast("添加失败");
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
                dialog.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
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
                    jump(IncidentAty.class, new JumpParameter().put("count", position + 1), new OnResponseListener() {
                        @Override
                        public void OnResponse(JumpParameter jumpParameter) {
                            if (jumpParameter==null){
                                toast("未返回任何数据");
                            }else{
                                count = Integer.valueOf(String.valueOf(jumpParameter.get("count")));
                            }
                        }
                    });
                }
            });
        }
    }
}
