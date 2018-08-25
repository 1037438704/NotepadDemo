package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

/**
 * Created by dell-pc on 2018/8/24.
 */

public class DayAdapter extends BaseQuickAdapter<StudentsBean, BaseViewHolder> {

    public DayAdapter(int layoutResId, @Nullable List<StudentsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentsBean item) {
        helper.setText(R.id.text_1, "日期:" + item.getData())
                .setText(R.id.text_2, "时间:" + item.getTime())
                .setText(R.id.text_3, "标题:" + item.getTitle())
                .setText(R.id.text_4, "内容:" + item.getTheEventContent());
    }
}
