package online.beartreasure.beart_amusement.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.adapter.Beart_RecommendFragment_RecyclerViewAdapter;
import online.beartreasure.beart_amusement.base.Beart_BaseFragment;
import online.beartreasure.beart_amusement.base.bear_MyDividerItemDecoration;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;
import online.beartreasure.beart_amusement.utils.Beart_NetworkInterfaceSatinApi;

public class Beart_RecommendFragment extends Beart_BaseFragment {
    private RecyclerView beart_recycler_image;
    private SmartRefreshLayout beart_smartrerershlayout_image;

    private String beart_return;
    private Beart_SatinApiBean beart_satinapibean;
    private List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean;
    private Beart_RecommendFragment_RecyclerViewAdapter beart_recommendFragment_recyclerViewAdapter;
    JZVideoPlayer jzVideoPlayer;
    private SmartRefreshLayout smartRefreshLayout;
    private int page = 1;

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
        smartRefreshLayout = beart_layout_view.findViewById(R.id.beart_smartrerershlayout_image);
    }

    @Override
    public void initDatas() {
        link(page);

    }

    private void Loading() {
    }

    private void link(int page) {
        OkHttpUtils.post().url(Beart_NetworkInterfaceSatinApi.Beart_SatinApi_URL).addParams("type", "1").addParams("page", String.valueOf(page)).build().execute(new StringCallback() {
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
                    beart_recycler_image.addItemDecoration(new bear_MyDividerItemDecoration());
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        jzVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: 子类的Stop");
        jzVideoPlayer.releaseAllVideos();
    }

    @Override
    public void settingView() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                page++;
                link(page);
            }
        });
    }
}