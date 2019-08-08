package online.beartreasure.beart_amusement.ui.activity;

import androidx.annotation.NonNull;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.base.Beart_BaseActivity;
import online.beartreasure.beart_amusement.ui.fragment.Beart_ClassificationFragment;
import online.beartreasure.beart_amusement.ui.fragment.Beart_MyFragment;
import online.beartreasure.beart_amusement.ui.fragment.Beart_NoticeFragment;
import online.beartreasure.beart_amusement.ui.fragment.Beart_RecommendFragment;
import online.beartreasure.beart_amusement.ui.fragment.Beart_RecommendIndexFragment;

public class IndexActivity extends Beart_BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView beart_bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_index;
    }

    @Override
    public void initView() {
        bindView();
    }

    private void bindView() {
        beart_bottomNavigationView = findViewById(R.id.beart_bottomnavigationview_index);
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void settingView() {
        bindViewStyle();
        bindEvnet();
    }

    private void bindViewStyle() {
        beart_bottomNavigationView.setItemTextColor(ColorStateList.valueOf(R.drawable.beart_edittextfillet_style));
    }

    private void bindEvnet() {
        beart_bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.beart_menu_bottom_home://主页
                getSupportFragmentManager().beginTransaction().replace(R.id.beart_framelayout_index, new Beart_RecommendIndexFragment()).commit();
                break;
            case R.id.beart_menu_bottom_classification://分类
                getSupportFragmentManager().beginTransaction().replace(R.id.beart_framelayout_index, new Beart_ClassificationFragment()).commit();
                break;
            case R.id.beart_menu_bottom_notice://消息
                getSupportFragmentManager().beginTransaction().replace(R.id.beart_framelayout_index, new Beart_NoticeFragment()).commit();
                break;
            case R.id.beart_menu_bottom_my://我的
                getSupportFragmentManager().beginTransaction().replace(R.id.beart_framelayout_index, new Beart_MyFragment()).commit();
                break;
        }

        return false;
    }
}