package online.beartreasure.beart_amusement.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.security.MessageDigest;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import online.beartreasure.beart_amusement.R;
import online.beartreasure.beart_amusement.bean.Beart_SatinApiBean;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;

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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.beart_recycler_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        if (beart_listsatinapibeandatabean.get(position).getText() != null && !TextUtils.isEmpty(beart_listsatinapibeandatabean.get(position).getText())) {
//            holder.beart_recycler_item_textviewtitile.setText(beart_listsatinapibeandatabean.get(position).getText());
//        } else {
//            holder.beart_recycler_item_textviewtitile.setText("楼主太懒了 都不写标题");
//        }
//        if (beart_listsatinapibeandatabean.get(position).getImage0() != null && !TextUtils.isEmpty(beart_listsatinapibeandatabean.get(position).getImage0())) {
//            Glide.with(mContext).load(beart_listsatinapibeandatabean.get(position).getImage0()).into(holder.beart_recycler_item_imageviewbody);
//        }else{
//            Glide.with(mContext).load(beart_listsatinapibeandatabean.get(position).getImage1()).into(holder.beart_recycler_item_imageviewbody);
//        }
        holder.beart_recycler_item2_JZVideoPlayerStandard.setUp(beart_listsatinapibeandatabean.get(position).getVideouri(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, beart_listsatinapibeandatabean.get(position).getText());
        holder.beart_recycler_item2_JZVideoPlayerStandard.startWindowTiny();
        holder.beart_recycler_item2_JZVideoPlayerStandard.startButton.performClick();
    }

    public int getItemCount() {
        return beart_listsatinapibeandatabean.size();
    }

    @SuppressLint("CheckResult")
    public static void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {
        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }

            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
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
