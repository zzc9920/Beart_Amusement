package online.beartreasure.beart_amusement.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.adapter.Beart_RecommendFragment_RecyclerViewAdapter;
import online.beartreasure.beart_amusement.base.Beart_BaseFragment;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;
import online.beartreasure.beart_amusement.utils.Beart_NetworkInterfaceSatinApi;

public class Beart_RecommendFragment extends Beart_BaseFragment {
    private RecyclerView beart_recycler_image;
    private SmartRefreshLayout beart_smartrerershlayout_image;
    private String beart_return;
    private Beart_SatinApiBean beart_satinapibean;
    private List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean;
    private Beart_RecommendFragment_RecyclerViewAdapter beart_recommendFragment_recyclerViewAdapter;


    @Override
    public int getLayout() {
        return R.layout.fragment_image;
    }

    @Override
    public void initView(View beart_layout_view) {
        Loading();
        bindView(beart_layout_view);
    }

    private void bindView(View beart_layout_view) {
        beart_recycler_image = beart_layout_view.findViewById(R.id.beart_recycler_image);
        beart_smartrerershlayout_image = beart_layout_view.findViewById(R.id.beart_smartrerershlayout_image);
    }

    @Override
    public void initDatas() {
        link();

    }

    private void Loading() {
    }

    private void link() {
        OkHttpUtils.post().url(Beart_NetworkInterfaceSatinApi.Beart_SatinApi_URL).addParams("type", "1").addParams("page", "1").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                beart_return = response;
                Log.e(TAG, "onResponse: " + beart_return);
                Gson gson = new Gson();
                beart_satinapibean = gson.fromJson(beart_return, Beart_SatinApiBean.class);
                if (beart_satinapibean.getCode() == 200) {
                    Log.e(TAG, "onResponse: 成功");
                    beart_listsatinapibeandatabean = beart_satinapibean.getData();
                    beart_recommendFragment_recyclerViewAdapter = new Beart_RecommendFragment_RecyclerViewAdapter(activity, beart_listsatinapibeandatabean);
                    beart_recycler_image.setAdapter(beart_recommendFragment_recyclerViewAdapter);
                    beart_recycler_image.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            }
        });
    }

    @Override
    public void settingView() {
    }
}