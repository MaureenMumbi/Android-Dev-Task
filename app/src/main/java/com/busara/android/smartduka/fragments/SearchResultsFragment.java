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
import static com.busara.android.smartduka.ui.SearchableActivity.EXTRA_SEARCH_QUERY;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultsFragment extends Fragment {


    public static final String SEARCHED_VIDEOS = "SEARCHED_VIDEO";
    public videoAdapter video_Adapter;
    @BindView(R.id.searchplayer_container)
    Container rview;
    LinearLayoutManager layoutManager;
    String newtoken = "";
    ArrayList<VideoResult> videoResults;
    public SearchResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, rootView);

        if (savedInstanceState != null) {
            newtoken = savedInstanceState.getString(ACCESS_TOKEN);


        } else {
            SharedPreferences preferences = this.getActivity().getSharedPreferences(MYPREFERENCES, getContext().MODE_PRIVATE);
            newtoken = preferences.getString(getString(R.string.accesstoken), getString(R.string.accesstoken));

        }

        String searchTerm = "";
        if (getArguments() != null) {//|| intent != null && intent.hasExtra(EXTRA_SEARCH_QUERY)
            searchTerm = getArguments().getString(EXTRA_SEARCH_QUERY);

        } else {

        }

        fetchSearchedVideos(newtoken, searchTerm);


        rview.setHasFixedSize(true);
        // rview.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        layoutManager = new LinearLayoutManager(getContext());
        rview.setLayoutManager(layoutManager);
        video_Adapter = new videoAdapter();
        rview.setAdapter(video_Adapter);

        return rootView;

    }


    public void fetchSearchedVideos(String new_token, String searchTerm) {
        ApiEndPointsInterface videoapi = RetrofitClient.getApiService(getContext());

        Call<Video> video = videoapi.getSearchedVideos("Bearer " + new_token, searchTerm, searchTerm);
        video.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                Integer statusCode = response.code();

                Video video = response.body();


                videoResults = video.getResults();
                Bundle videoBundle = new Bundle();
                videoBundle.putParcelableArrayList(SEARCHED_VIDEOS, videoResults);
                video_Adapter.setVideoData(videoResults, getContext());

            }


            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getContext(), "Error while searching, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(SEARCHED_VIDEOS, videoResults);
        currentState.putString(ACCESS_TOKEN, newtoken);
    }

}
