package com.busara.android.smartduka.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.utils.VideoResult;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;

import im.ene.toro.ToroPlayer;
import im.ene.toro.ToroUtil;
import im.ene.toro.exoplayer.SimpleExoPlayerViewHelper;
import im.ene.toro.media.PlaybackInfo;
import im.ene.toro.widget.Container;

/**
 * Created by Mauryn on 6/9/2018.
 */


public class videoAdapter extends RecyclerView.Adapter<videoAdapter.videoAdapterViewHolder> {


    private static final String TAG = videoAdapter.class.getSimpleName();
    private static ArrayList<VideoResult> mVideoData;
//    final private ListItemClickListener mlistItemClickListener;
//
//    public videoAdapter(ListItemClickListener listItemClickListener) {
//        this.mlistItemClickListener = listItemClickListener;
//    }


    public void setVideoData(ArrayList<VideoResult> videoData, Context context) {
        Log.d(videoAdapter.class.getSimpleName(), "IN SETTING VIDEO DATA ");
        mVideoData = videoData;

        notifyDataSetChanged();
    }


    @Override
    public videoAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.video_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);


        return new videoAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(videoAdapterViewHolder holder, int position) {
        VideoResult videoResult = mVideoData.get(position);

        String name = videoResult.getName();
        holder.mNameView.setText(name);

        String category_name = videoResult.getCategory_name();
        holder.mCategory.setText(category_name);

        int timesWatched = videoResult.getNo_times_watched();
        holder.mwatchCountView.setText("No of Views: " + String.valueOf(timesWatched));

        String videoURL = videoResult.getFile_path();

        Uri videoUri = Uri.parse(videoURL);
        holder.bind(videoUri);

        String description = videoResult.getDescription();

        holder.mDescriptionView.setText(description);


    }


    @Override
    public int getItemCount() {
        if (mVideoData == null) {
            return 0;
        } else {
            return mVideoData.size();

        }
    }

    public class videoAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ToroPlayer {
        public final TextView mDescriptionView;
        public final TextView mNameView;
        public final TextView mCategory;
        public final TextView mwatchCountView;


        @Nullable
        SimpleExoPlayerViewHelper helper;
        SimpleExoPlayerView mVideoView;
        @Nullable
        private Uri mediaUri;


        public videoAdapterViewHolder(View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.name);
            mCategory = itemView.findViewById(R.id.category);
            mwatchCountView = itemView.findViewById(R.id.no_times_watched);
            mVideoView = itemView.findViewById(R.id.videoexoplayerView);


            mDescriptionView = itemView.findViewById(R.id.description);


        }

        void bind(Uri item) {
            if (item != null) {
                mediaUri = item;
            }
        }

        @Override
        public void onClick(View view) {
            int adapterposition = getAdapterPosition();
            VideoResult clickedVideo = mVideoData.get(adapterposition);
            //mlistItemClickListener.onListItemClick(clickedVideo);


        }

        @NonNull
        @Override
        public View getPlayerView() {
            return mVideoView;
        }

        @NonNull
        @Override
        public PlaybackInfo getCurrentPlaybackInfo() {
            return helper != null ? helper.getLatestPlaybackInfo() : new PlaybackInfo();
        }

        @Override
        public void initialize(@NonNull Container container, @NonNull PlaybackInfo playbackInfo) {
            if (helper == null) {
                helper = new SimpleExoPlayerViewHelper(container, this, mediaUri);
            }
            helper.initialize(playbackInfo);
        }

        @Override
        public void release() {
            if (helper != null) {
                helper.release();
                helper = null;
            }
        }

        @Override
        public void play() {
            if (helper != null) helper.play();
        }

        @Override
        public void pause() {
            if (helper != null) helper.pause();
        }

        @Override
        public boolean isPlaying() {
            return helper != null && helper.isPlaying();
        }

        @Override
        public boolean wantsToPlay() {
            return ToroUtil.visibleAreaOffset(this, itemView.getParent()) >= 0.85;
        }

        @Override
        public int getPlayerOrder() {
            return getAdapterPosition();
        }

        @Override
        public void onSettled(Container container) {

        }
    }


//    public interface ListItemClickListener {
//        void onListItemClick(VideoResult videoResult);
//    }
}






