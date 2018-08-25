package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.bean.DataBean;

/**
 * Created by dell-pc on 2018/8/20.
 */

public class TemplateAdapter2 extends BaseQuickAdapter<DataBean,BaseViewHolder>{


    public TemplateAdapter2(int layoutResId, @Nullable List<DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        if (item.getItmedata() != null){
            helper.setText(R.id.text_data,item.getItmedata())
                    .addOnClickListener(R.id.text_data);
        }else {
            helper.addOnClickListener(R.id.text_data);
        }
    }
}
