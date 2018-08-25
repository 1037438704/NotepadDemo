package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import pmp.tianxundai.com.notepaddemo.R;
import pmp.tianxundai.com.notepaddemo.bean.StudentsBean;

/**
 * Created by dell-pc on 2018/8/17.
 */

public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public HomeAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.home_text,item);
    }


}
