package pmp.tianxundai.com.notepaddemo.fgt;


import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.util.Preferences;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.HomeAdapter;
import pmp.tianxundai.com.notepaddemo.aty.Main2Activity;
import pmp.tianxundai.com.notepaddemo.aty.Main3Activity;
import pmp.tianxundai.com.notepaddemo.aty.MannerAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;

import static android.app.Activity.RESULT_OK;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2018/8/17 10:38
 * 功能描述：我的界面
 * 联系方式：1037438704@qq.com
 *
 * @author dell-pc
 */
@Layout(R.layout.fgt_my)
public class MyFgt extends BaseFgt {
    private TextView title_text;
    private RecyclerView title_rv;
    private List<String> list;
    private HomeAdapter homeAdapter;
    private static final int PICTURE = 200;
    private LinearLayout ll_layout;

    @Override
    public void initViews() {
        ll_layout = findViewById(R.id.ll_layout);
        title_text = findViewById(R.id.title_text);
        title_rv = findViewById(R.id.title_rv);
        title_rv.setLayoutManager(new LinearLayoutManager(me));
        list = new ArrayList<>();
    }

    @Override
    public void initDatas() {
        ll_layout.setY(me.getStatusBarHeight());
        title_text.setText("设置");
        list.add("背景设置");
        list.add("铃声设置");
        homeAdapter = new HomeAdapter(R.layout.item_home, list);
        title_rv.setAdapter(homeAdapter);
    }

    @Override
    public void setEvents() {
        //点击事件
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * View点击事件使用
                 * */
                switch (position) {
                    case 0:
                        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picture, PICTURE);
                        break;
                    case 1:
//                        jump(RingAty.class);
                        jump(Main3Activity.class);
                        break;
                    case 2:
//                        jump(MannerAty.class);
                        break;
                    default:
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE && resultCode == RESULT_OK && data != null) {
            /**
             * 调用图库
             */
            Uri selectedImage = data.getData();
            String str = String.valueOf(selectedImage);
            Preferences.getInstance().set(me, "user", "bg", str);
        }
    }


}
