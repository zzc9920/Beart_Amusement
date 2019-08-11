package online.beartreasure.beart_amusement.ui.fragment;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.goodview.GoodView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.adapter.Beart_ImagerFragment_RecyclerViewAdapter;
import online.beartreasure.beart_amusement.adapter.Beart_RecommendFragment_RecyclerViewAdapter;
import online.beartreasure.beart_amusement.base.Beart_BaseFragment;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;
import online.beartreasure.beart_amusement.utils.Beart_NetworkInterfaceSatinApi;

public class Beart_ImageFragment extends Beart_BaseFragment {
    private RecyclerView beart_recycler_image;
    private SmartRefreshLayout beart_smartrerershlayout_image;
    private String beart_return;
    private Beart_SatinApiBean beart_satinapibean;
    private List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean;
    private Beart_ImagerFragment_RecyclerViewAdapter beart_imagerFragment_recyclerViewAdapter;
    private int pager = 4;
    private SmartRefreshLayout smartRefreshLayout;

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
        beart_smartrerershlayout_image = beart_layout_view.findViewById(R.id.beart_smartrerershlayout_image);
        smartRefreshLayout = beart_layout_view.findViewById(R.id.beart_smartrerershlayout_image);
    }

    @Override
    public void initDatas() {
        link(pager);
    }

    private void link(int pager) {
        OkHttpUtils.post().url(Beart_NetworkInterfaceSatinApi.Beart_SatinApi_URL).addParams("type", "1").addParams("page", String.valueOf(pager)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                beart_return = response;
                Log.e(TAG, "onResponse: " + beart_return);
                Gson gson = new Gson();
                beart_satinapibean = gson.fromJson(beart_return, Beart_SatinApiBean.class);
                beart_listsatinapibeandatabean = beart_satinapibean.getData();
                beart_imagerFragment_recyclerViewAdapter = new Beart_ImagerFragment_RecyclerViewAdapter(activity, beart_listsatinapibeandatabean);
                beart_recycler_image.setAdapter(beart_imagerFragment_recyclerViewAdapter);
                beart_recycler_image.setLayoutManager(new LinearLayoutManager(getActivity()));
                final GoodView goodView = new GoodView(getActivity());
                beart_imagerFragment_recyclerViewAdapter.setImageOnClickLinsert(new Beart_ImagerFragment_RecyclerViewAdapter.ImageOnClickLinsert() {
                    @Override
                    public void OnClick(View view, int position) {
                        goodView.setText("+1");
                        goodView.show(view);
                    }
                });
                beart_imagerFragment_recyclerViewAdapter.setFenxiangOnClickLinsert(new Beart_ImagerFragment_RecyclerViewAdapter.fenxiangOnClickLinsert() {
                    @Override
                    public void OnClick(View view, int position) {
                        goodView.setText("分享成功");
                        goodView.show(view);
                    }
                });
                beart_imagerFragment_recyclerViewAdapter.setShouchangOnClickLinsert(new Beart_ImagerFragment_RecyclerViewAdapter.shouchangOnClickLinsert() {
                    @Override
                    public void OnClick(View view, int position) {
                        goodView.setText("收藏成功");
                        goodView.show(view);
                    }
                });
            }
        });
    }

    @Override
    public void settingView() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                pager++;
                link(pager);
            }
        });
    }
}
