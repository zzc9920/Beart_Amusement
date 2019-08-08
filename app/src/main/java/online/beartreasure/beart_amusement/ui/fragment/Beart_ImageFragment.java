package online.beartreasure.beart_amusement.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.adapter.Beart_ImagerFragment_RecyclerViewAdapter;
import online.beartreasure.beart_amusement.base.Beart_BaseFragment;
import online.beartreasure.beart_amusement.bean.Beart_KaiYanInterfaceBean;

public class Beart_ImageFragment extends Beart_BaseFragment {
    private RecyclerView beart_recycler_image;

    @Override
    public int getLayout() {
        return R.layout.fragment_image;
    }

    @Override
    public void initView(View beart_layout_view) {
        bindView(beart_layout_view);
    }

    private void bindView(View beart_layout_view) {
        beart_recycler_image = beart_layout_view.findViewById(R.id.beart_recycler_image);
    }

    @Override
    public void initDatas() {
        final Gson gson = new Gson();
        OkHttpUtils.post().url("http://baobab.kaiyanapp.com/api/v2/feed?num=10&udid=26868b32e808498db32fd51fb422d00175e179df").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: " + response);
                Beart_KaiYanInterfaceBean beart_kaiYanInterfaceBean = gson.fromJson(response, Beart_KaiYanInterfaceBean.class);
                List<Beart_KaiYanInterfaceBean.IssueListBean.ItemListBean> data = beart_kaiYanInterfaceBean.getIssueList().get(0).getItemList();
                Beart_ImagerFragment_RecyclerViewAdapter beart_imagerFragment_recyclerViewAdapter = new Beart_ImagerFragment_RecyclerViewAdapter(getActivity(), data);
                beart_recycler_image.setAdapter(beart_imagerFragment_recyclerViewAdapter);
                beart_recycler_image.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });
    }

    @Override
    public void settingView() {
        final Gson gson = new Gson();
        OkHttpUtils.post().url("http://baobab.kaiyanapp.com/api/v2/feed?num=10&udid=26868b32e808498db32fd51fb422d00175e179df").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e(TAG, "onResponse: " + response);
                Beart_KaiYanInterfaceBean beart_kaiYanInterfaceBean = gson.fromJson(response, Beart_KaiYanInterfaceBean.class);
                List<Beart_KaiYanInterfaceBean.IssueListBean.ItemListBean> data = beart_kaiYanInterfaceBean.getIssueList().get(0).getItemList();
                Beart_ImagerFragment_RecyclerViewAdapter beart_imagerFragment_recyclerViewAdapter = new Beart_ImagerFragment_RecyclerViewAdapter(getActivity(), data);
                beart_recycler_image.setAdapter(beart_imagerFragment_recyclerViewAdapter);
                beart_recycler_image.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });
    }
}
