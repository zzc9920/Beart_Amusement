package online.beartreasure.beart_amusement.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dmax.dialog.SpotsDialog;


public abstract class Beart_BaseFragment extends Fragment {
    private View beart_layout_view;
    protected final String TAG = this.getClass().getSimpleName();
    public Activity activity;
    public AlertDialog AlertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beart_layout_view = inflater.inflate(getLayout(), null, false);
        initView(beart_layout_view);
        activity = getActivity();
        AlertDialog = new SpotsDialog.Builder()
                .setContext(getActivity())
                .setMessage("网络不太好等待哦")
                .setCancelable(false)
                .build();
        AlertDialog.show();
        return beart_layout_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: 12321321321");
        initDatas();
        settingView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AlertDialog.dismiss();
            }
        }).start();
        Log.e("onActivityCreated", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("onStart", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("onStop", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        initDatas();
        Log.e("onDestroyView", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("onDetach", "onDetach");
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
