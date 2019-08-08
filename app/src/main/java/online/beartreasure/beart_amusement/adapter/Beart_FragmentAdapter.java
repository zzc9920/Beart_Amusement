package online.beartreasure.beart_amusement.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class Beart_FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> beart_listFragment;

    public Beart_FragmentAdapter(FragmentManager fm, List<Fragment> beart_listFragment) {
        super(fm);
        this.beart_listFragment = beart_listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return beart_listFragment.get(position);
    }

    @Override
    public int getCount() {
        return beart_listFragment.size();
    }
}
