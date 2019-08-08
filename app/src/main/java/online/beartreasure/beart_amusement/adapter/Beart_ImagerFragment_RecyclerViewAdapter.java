package online.beartreasure.beart_amusement.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.bean.Beart_KaiYanInterfaceBean;

public class Beart_ImagerFragment_RecyclerViewAdapter extends RecyclerView.Adapter<Beart_ImagerFragment_RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Beart_KaiYanInterfaceBean.IssueListBean.ItemListBean> beart_ListDataBeans;
    private LayoutInflater mLayoutInflater;

    public Beart_ImagerFragment_RecyclerViewAdapter(Context mContext, List<Beart_KaiYanInterfaceBean.IssueListBean.ItemListBean> beart_ListDataBeans) {
        this.mContext = mContext;
        this.beart_ListDataBeans = beart_ListDataBeans;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.beart_recycler_item1, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!TextUtils.isEmpty(beart_ListDataBeans.get(position).getData().getTitle()) && beart_ListDataBeans.get(position).getData().getTitle() != null) {
            holder.beart_recycler_item_textviewtitile.setText(beart_ListDataBeans.get(position).getData().getTitle());
        } else {
            holder.beart_recycler_item_textviewtitile.setText("楼主太懒没写标题");
        }
        try {
            Glide.with(mContext)
                    .load(beart_ListDataBeans.get(position).getData().getTags().get(position).getBgPicture())
                    .into(holder.beart_recycler_item_imageviewbody);
        } catch (NullPointerException e) {
        }
    }

    @Override
    public int getItemCount() {
        return beart_ListDataBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView beart_recycler_item_textviewtitile;
        private ImageView beart_recycler_item_imageviewbody, beart_recycler_item_imageviewinterstellar, beart_recycler_item_imageviewgivethe, beart_recycler_item_imageviewdialogue, beart_recycler_item_imageviewshare;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            beart_recycler_item_textviewtitile = itemView.findViewById(R.id.beart_recycler_item_textviewtitile);
            beart_recycler_item_imageviewbody = itemView.findViewById(R.id.beart_recycler_item_imageviewbody);
            beart_recycler_item_imageviewinterstellar = itemView.findViewById(R.id.beart_recycler_item_imageviewinterstellar);
            beart_recycler_item_imageviewgivethe = itemView.findViewById(R.id.beart_recycler_item_imageviewgivethe);
            beart_recycler_item_imageviewdialogue = itemView.findViewById(R.id.beart_recycler_item_imageviewdialogue);
            beart_recycler_item_imageviewshare = itemView.findViewById(R.id.beart_recycler_item_imageviewshare);
        }
    }
}
