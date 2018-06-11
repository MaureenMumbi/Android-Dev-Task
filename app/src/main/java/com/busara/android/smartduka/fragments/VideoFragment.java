package com.busara.android.smartduka.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.adapters.videoAdapter;
import com.busara.android.smartduka.utils.ApiEndPointsInterface;
import com.busara.android.smartduka.utils.RetrofitClient;
import com.busara.android.smartduka.utils.Video;
import com.busara.android.smartduka.utils.VideoResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.ene.toro.widget.Container;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.busara.android.smartduka.ui.LoginActivity.ACCESS_TOKEN;
import static com.busara.android.smartduka.ui.LoginActivity.MYPREFERENCES;
import static com.busara.android.smartduka.ui.LoginActivity.USERDETAILS;


public class VideoFragment extends Fragment {

    public static final String ALL_VIDEOS = "ALL_VIDEO";

    public videoAdapter video_Adapter;
    @BindView(R.id.player_container)
    Container rview;
    LinearLayoutManager layoutManager;
    ArrayList<VideoResult> videoResults;
    String newtoken = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, rootView);

        String firstname = "";
        String lastname = "";
        if (savedInstanceState != null) {
            newtoken = savedInstanceState.getString(ACCESS_TOKEN);
        } else {
            SharedPreferences preferences = this.getActivity().getSharedPreferences(MYPREFERENCES, getContext().MODE_PRIVATE);
            newtoken = preferences.getString(getString(R.string.accesstoken), getString(R.string.accesstoken));

            SharedPreferences userpreferences = this.getActivity().getSharedPreferences(USERDETAILS, getContext().MODE_PRIVATE);

            //USERDETAILS
            firstname = userpreferences.getString("FirstName", "No Name");
            lastname = userpreferences.getString("LastName", "No Name");
        }

        fetchVideos(newtoken);


        rview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layoutManager);
        video_Adapter = new videoAdapter();
        rview.setAdapter(video_Adapter);

        return rootView;

    }


    //  GridLayoutManager gridlayoutManager = new GridLayoutManager(getContext(), 1);
//        mRecyclerView.setLayoutManager(gridlayoutManager);
//        mRecyclerView.setHasFixedSize(true);
    //        video_Adapter = new videoAdapter(this);
//        mRecyclerView.setAdapter(video_Adapter);


//        container = rootView.findViewById(R.id.player_container);
//        layoutManager = new LinearLayoutManager(getContext());
//        container.setLayoutManager(layoutManager);
//        video_Adapter = new videoAdapter(this);
//        container.setAdapter(video_Adapter);


    public void fetchVideos(String new_token) {
        ApiEndPointsInterface videoapi = RetrofitClient.getApiService(getContext());

        Call<Video> video = videoapi.getVideos("Bearer " + new_token);
        video.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                Integer statusCode = response.code();

                Video video = response.body();

                videoResults = video.getResults();
                Bundle videoBundle = new Bundle();
                videoBundle.putParcelableArrayList(ALL_VIDEOS, videoResults);

                video_Adapter.setVideoData(videoResults, getContext());


            }


            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getContext(), "Error, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(ALL_VIDEOS, videoResults);
        currentState.putString(ACCESS_TOKEN, newtoken);
    }
//    @Override
//    public void onListItemClick(VideoResult videoResult) {
//        Intent intent = new Intent(getContext(), VideoDetailActivity.class)
//                .putExtra("VideoResult", videoResult);
//        startActivity(intent);
//    }
}
