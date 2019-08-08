package online.beartreasure.beart_amusement.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class Beart_BaseFragment extends Fragment {
    private View beart_layout_view;
    protected final String TAG = this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beart_layout_view = inflater.inflate(getLayout(), container, false);
        initView(beart_layout_view);
        initDatas();
        return beart_layout_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        settingView();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 获取布局
     *
     * @第四組:组长
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     *
     * @第四組:组长
     */
    public abstract void initView(View beart_layout_view);

    /**
     * 初始化数据
     *
     * @第四組:组长
     */
    public abstract void initDatas();

    /**
     * 设置控件
     *
     * @第四組:组长
     */
    public abstract void settingView();
}
