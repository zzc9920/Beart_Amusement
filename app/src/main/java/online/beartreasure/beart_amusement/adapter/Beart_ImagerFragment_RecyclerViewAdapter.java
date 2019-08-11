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

import cn.jzvd.JZVideoPlayerStandard;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;

public class Beart_ImagerFragment_RecyclerViewAdapter extends RecyclerView.Adapter<Beart_ImagerFragment_RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean;
    private LayoutInflater mLayoutInflater;

    public Beart_ImagerFragment_RecyclerViewAdapter(Context mContext, List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean) {
        this.mContext = mContext;
        this.beart_listsatinapibeandatabean = beart_listsatinapibeandatabean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public interface ImageOnClickLinsert {
        void OnClick(View view, int position);
    }

    public interface shouchangOnClickLinsert {
        void OnClick(View view, int position);
    }

    public interface fenxiangOnClickLinsert {
        void OnClick(View view, int position);
    }

    private ImageOnClickLinsert imageOnClickLinsert;
    private shouchangOnClickLinsert shouchangOnClickLinsert;
    private fenxiangOnClickLinsert fenxiangOnClickLinsert;

    public void setImageOnClickLinsert(ImageOnClickLinsert imageOnClickLinsert) {
        this.imageOnClickLinsert = imageOnClickLinsert;
    }

    public void setShouchangOnClickLinsert(Beart_ImagerFragment_RecyclerViewAdapter.shouchangOnClickLinsert shouchangOnClickLinsert) {
        this.shouchangOnClickLinsert = shouchangOnClickLinsert;
    }

    public void setFenxiangOnClickLinsert(Beart_ImagerFragment_RecyclerViewAdapter.fenxiangOnClickLinsert fenxiangOnClickLinsert) {
        this.fenxiangOnClickLinsert = fenxiangOnClickLinsert;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.beart_recycler_item1, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        if (beart_listsatinapibeandatabean.get(position).getText() != null && !TextUtils.isEmpty(beart_listsatinapibeandatabean.get(position).getText())) {
            holder.beart_recycler_item_textviewtitile.setText(beart_listsatinapibeandatabean.get(position).getText());
        } else {
            holder.beart_recycler_item_textviewtitile.setText("楼主太懒了 都不写标题");
        }
        Glide.with(mContext).load(beart_listsatinapibeandatabean.get(position).getImage0()).into(holder.beart_recycler_item_imageviewbody);
//        holder.beart_recycler_item2_JZVideoPlayerStandard.setUp(beart_listsatinapibeandatabean.get(position).getVideouri(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, beart_listsatinapibeandatabean.get(position).getText());
        if (imageOnClickLinsert != null) {
            holder.beart_recycler_item_imageviewgivethe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageOnClickLinsert.OnClick(holder.beart_recycler_item_imageviewgivethe, position);
                }
            });
        }
        if (shouchangOnClickLinsert != null) {
            holder.beart_recycler_item_imageviewinterstellar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shouchangOnClickLinsert.OnClick(holder.beart_recycler_item_imageviewinterstellar, position);
                }
            });
        }
        if (fenxiangOnClickLinsert != null) {
            holder.beart_recycler_item_imageviewshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fenxiangOnClickLinsert.OnClick(holder.beart_recycler_item_imageviewshare, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return beart_listsatinapibeandatabean.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView beart_recycler_item_textviewtitile;
        private ImageView beart_recycler_item_imageviewbody, beart_recycler_item_imageviewinterstellar, beart_recycler_item_imageviewgivethe, beart_recycler_item_imageviewdialogue, beart_recycler_item_imageviewshare;
//        private JZVideoPlayerStandard beart_recycler_item2_JZVideoPlayerStandard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            beart_recycler_item_textviewtitile = itemView.findViewById(R.id.beart_recycler_item_textviewtitile);
            beart_recycler_item_imageviewbody = itemView.findViewById(R.id.beart_recycler_item_imageviewbody);
            beart_recycler_item_imageviewinterstellar = itemView.findViewById(R.id.beart_recycler_item_imageviewinterstellar);
            beart_recycler_item_imageviewgivethe = itemView.findViewById(R.id.beart_recycler_item_imageviewgivethe);
            beart_recycler_item_imageviewdialogue = itemView.findViewById(R.id.beart_recycler_item_imageviewdialogue);
            beart_recycler_item_imageviewshare = itemView.findViewById(R.id.beart_recycler_item_imageviewshare);
//            beart_recycler_item2_JZVideoPlayerStandard = itemView.findViewById(R.id.beart_recycler_item2_JZVideoPlayerStandard);
        }
    }
}
