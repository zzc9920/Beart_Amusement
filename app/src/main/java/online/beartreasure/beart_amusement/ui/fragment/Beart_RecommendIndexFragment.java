package online.beartreasure.beart_amusement.ui.fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.adapter.Beart_FragmentAdapter;
import online.beartreasure.beart_amusement.base.Beart_BaseFragment;

public class Beart_RecommendIndexFragment extends Beart_BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager beart_ViewPager;
    private BottomNavigationView beart_topbottomNavigationView;
    private List<Fragment> beart_listFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_recommendindex;
    }

    @Override
    public void initView(View beart_layout_view) {
        bindView(beart_layout_view);
    }

    private void bindView(View beart_layout_view) {
        beart_ViewPager = beart_layout_view.findViewById(R.id.beart_viewpager_recommend);
        beart_topbottomNavigationView = beart_layout_view.findViewById(R.id.beart_topbottomnavigationview_recommend);
    }


    @Override
    public void initDatas() {
        beart_listFragments = new ArrayList<>();
        beart_listFragments.add(new Beart_RecommendFragment());
        beart_listFragments.add(new Beart_VideoFragment());
        beart_listFragments.add(new Beart_ImageFragment());
        beart_listFragments.add(new Beart_WrittenwordFragment());
    }

    @Override
    public void settingView() {
        bindAdapter();
        bindEvent();
    }

    private void bindAdapter() {
        beart_ViewPager.setAdapter(new Beart_FragmentAdapter(getChildFragmentManager(), beart_listFragments));
    }

    private void bindEvent() {
        beart_topbottomNavigationView.setOnNavigationItemSelectedListener(this);
        beart_ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem item = beart_topbottomNavigationView.getMenu().getItem(position);
                item.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_menu_recommend:
                beart_ViewPager.setCurrentItem(0);
                break;
            case R.id.action_menu_video:
                beart_ViewPager.setCurrentItem(1);
                break;
            case R.id.action_menu_image:
                beart_ViewPager.setCurrentItem(2);
                break;
            case R.id.action_menu_writtenword:
                beart_ViewPager.setCurrentItem(3);
                break;
        }
        return false;
    }
}
