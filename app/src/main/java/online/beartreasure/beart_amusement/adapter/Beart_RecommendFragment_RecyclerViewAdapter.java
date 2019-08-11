package online.beartreasure.beart_amusement.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wx.goodview.GoodView;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;

public class Beart_RecommendFragment_RecyclerViewAdapter extends RecyclerView.Adapter<Beart_RecommendFragment_RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean;
    private LayoutInflater mLayoutInflater;
    private static final String TAG = "Beart_RecommendFragment";

    public Beart_RecommendFragment_RecyclerViewAdapter(Context mContext, List<Beart_SatinApiBean.DataBean> beart_listsatinapibeandatabean) {
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
    private ImageOnClickLinsert shouchangOnClickLinsert;
    private ImageOnClickLinsert fenxiangOnClickLinsert;

    public void setShouchangOnClickLinsert(ImageOnClickLinsert shouchangOnClickLinsert) {
        this.shouchangOnClickLinsert = shouchangOnClickLinsert;
    }

    public void setFenxiangOnClickLinsert(ImageOnClickLinsert fenxiangOnClickLinsert) {
        this.fenxiangOnClickLinsert = fenxiangOnClickLinsert;
    }

    public void setImageOnClickLinsert(ImageOnClickLinsert imageOnClickLinsert) {

        this.imageOnClickLinsert = imageOnClickLinsert;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.beart_recycler_item2, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.beart_recycler_item2_JZVideoPlayerStandard.setUp(beart_listsatinapibeandatabean.get(position).getVideouri(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, beart_listsatinapibeandatabean.get(position).getText());
        holder.beart_recycler_item2_JZVideoPlayerStandard.startWindowTiny();
//        loadVideoScreenshot(mContext, beart_listsatinapibeandatabean.get(position).getVideouri(), holder.beart_recycler_item2_JZVideoPlayerStandard.thumbImageView, 1000);
        Glide.with(mContext).load(beart_listsatinapibeandatabean.get(position).getCdn_img()).into(holder.beart_recycler_item2_JZVideoPlayerStandard.thumbImageView);
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

    public int getItemCount() {
        return beart_listsatinapibeandatabean.size();
    }

    //    @SuppressLint("CheckResult")
//    public void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {
//        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
//        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
//        requestOptions.transform(new BitmapTransformation() {
//            @Override
//            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//                return toTransform;
//            }
//
//            @Override
//            public void updateDiskCacheKey(MessageDigest messageDigest) {
//                try {
//                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
//    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.e(TAG, "onDetachedFromRecyclerView: 12321321321321");

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView beart_recycler_item_textviewtitile;
        private ImageView beart_recycler_item_imageviewbody, beart_recycler_item_imageviewinterstellar, beart_recycler_item_imageviewgivethe, beart_recycler_item_imageviewdialogue, beart_recycler_item_imageviewshare;
        public JZVideoPlayerStandard beart_recycler_item2_JZVideoPlayerStandard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            beart_recycler_item_textviewtitile = itemView.findViewById(R.id.beart_recycler_item_textviewtitile);
            beart_recycler_item_imageviewbody = itemView.findViewById(R.id.beart_recycler_item_imageviewbody);
            beart_recycler_item_imageviewinterstellar = itemView.findViewById(R.id.beart_recycler_item_imageviewinterstellar);
            beart_recycler_item_imageviewgivethe = itemView.findViewById(R.id.beart_recycler_item_imageviewgivethe);
            beart_recycler_item_imageviewdialogue = itemView.findViewById(R.id.beart_recycler_item_imageviewdialogue);
            beart_recycler_item_imageviewshare = itemView.findViewById(R.id.beart_recycler_item_imageviewshare);
            beart_recycler_item2_JZVideoPlayerStandard = itemView.findViewById(R.id.beart_recycler_item2_JZVideoPlayerStandard);
        }
    }
}
