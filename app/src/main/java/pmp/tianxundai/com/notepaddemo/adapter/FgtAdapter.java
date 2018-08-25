package pmp.tianxundai.com.notepaddemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dell-pc on 2018/8/17.
 */

public class FgtAdapter extends FragmentPagerAdapter {
    private List<Fragment> datas;
    private List<String> titles;

    public FgtAdapter(FragmentManager fm, List<Fragment> datas, List<String> titles) {
        super(fm);
        this.datas = datas;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? null : titles.get(position);
    }
}