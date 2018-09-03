package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

/**
 * Created by dell-pc on 2018/8/24.
 */

public class DayAdapter extends BaseQuickAdapter<StudentsBean, BaseViewHolder> {
    private List<DataBean> list1, list2;
    public DayAdapter(int layoutResId, @Nullable List<StudentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentsBean item) {
        helper.setText(R.id.text_1, "日期:" + item.getData())
                .setText(R.id.text_2, "时间:" + item.getTime())
                .setText(R.id.text_3, "标题:" + item.getTitle());

        RecyclerView item_rv_1 = helper.itemView.findViewById(R.id.item_rv_1);
        RecyclerView item_rv_2 = helper.itemView.findViewById(R.id.item_rv_2);
        item_rv_1.setLayoutManager(new LinearLayoutManager(mContext));
        item_rv_2.setLayoutManager(new LinearLayoutManager(mContext));

        String theEventContent = item.getTheEventContent();

        Log.d("dataBean", "=========dataBean========" + theEventContent);

        Gson gson = new Gson();
        Type listtype = new TypeToken<List<DataBean>>() {
        }.getType();
        list1  = gson.fromJson(theEventContent, listtype);

        TemplateAdapter3 templateAdapter = new TemplateAdapter3(R.layout.item_date_layout, list1);
        item_rv_1.setAdapter(templateAdapter);

        String dataContent = item.getDataContent();
        Log.d("dataBean", "=========dataBean========" + dataContent);


        Gson gson2 = new Gson();
        Type listtype2 = new TypeToken<List<DataBean>>() {
        }.getType();
        list2  = gson2.fromJson(dataContent, listtype2);

        TemplateAdapter3 templateAdapter2 = new TemplateAdapter3(R.layout.item_date_layout, list2);
        item_rv_2.setAdapter(templateAdapter2);
    }
}
