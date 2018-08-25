package pmp.tianxundai.com.notepaddemo.fgt;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kongzue.baseframework.interfaces.Layout;

import java.util.ArrayList;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.adapter.HomeAdapter;
import pmp.tianxundai.com.notepaddemo.adapter.IncidentAdapter;
import pmp.tianxundai.com.notepaddemo.aty.BackgroundAty;
import pmp.tianxundai.com.notepaddemo.aty.MannerAty;
import pmp.tianxundai.com.notepaddemo.aty.RingAty;
import pmp.tianxundai.com.notepaddemo.base.BaseFgt;

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

    @Override
    public void initViews() {
        title_text = findViewById(R.id.title_text);
        title_rv = findViewById(R.id.title_rv);
        title_rv.setLayoutManager(new LinearLayoutManager(me));
        list = new ArrayList<>();
    }

    @Override
    public void initDatas() {
        title_text.setText("设置");
        list.add("背景设置");
        list.add("铃声设置");
        list.add("提醒方式");
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
//                        final Intent pickWallpaper = new Intent(Intent.ACTION_SET_WALLPAPER);
//                        Intent chooser = Intent.createChooser(pickWallpaper, getString(R.string.choose_wallpaper));
//                        startActivity(chooser);
                        jump(BackgroundAty.class);
                        break;
                    case 1:
                        jump(RingAty.class);
                        break;
                    case 2:
                        jump(MannerAty.class);
                        break;
                    default:
                }
            }
        });
    }
}
