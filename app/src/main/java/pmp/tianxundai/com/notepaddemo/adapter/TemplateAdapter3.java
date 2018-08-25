package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;

/**
 * @author dell-pc
 * @date 2018/8/20
 */
public class TemplateAdapter3 extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public TemplateAdapter3(int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        TextView text_data = helper.itemView.findViewById(R.id.text_data);
        TextView text_xiaoxi = helper.itemView.findViewById(R.id.text_xiaoxi);
        helper.addOnClickListener(R.id.text_data).addOnClickListener(R.id.text_xiaoxi);
        text_data.setText(item.getItmedata());
        if (item.getItemright() != null){
            text_xiaoxi.setText(item.getItemright());
        }
    }
}
