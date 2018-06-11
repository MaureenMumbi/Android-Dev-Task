package com.busara.android.smartduka.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.adapters.CategoryAdapter;
import com.busara.android.smartduka.utils.ApiEndPointsInterface;
import com.busara.android.smartduka.utils.Categories;
import com.busara.android.smartduka.utils.CategoryResult;
import com.busara.android.smartduka.utils.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.busara.android.smartduka.ui.LoginActivity.ACCESS_TOKEN;
import static com.busara.android.smartduka.ui.LoginActivity.MYPREFERENCES;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    public static final String ALL_CATEGORIES = "ALL_CATEGORIES";
    public CategoryAdapter categoryAdapter;

    //
    @BindView(R.id.categorylistrv)
    RecyclerView categoryrview;
    ArrayList<CategoryResult> categoryResults;
    LinearLayoutManager linearLayoutManager;
    String newtoken = "";
    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, rootView);

        String refreshtoken = "";
        if (savedInstanceState != null) {
            newtoken = savedInstanceState.getString(ACCESS_TOKEN);

        } else {
            SharedPreferences preferences = this.getActivity().getSharedPreferences(MYPREFERENCES, getContext().MODE_PRIVATE);
            newtoken = preferences.getString(getString(R.string.accesstoken), getString(R.string.accesstoken));

        }

        fetchCategories(newtoken);

        categoryrview.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        categoryrview.setLayoutManager(linearLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(categoryrview.getContext(),
                linearLayoutManager.getOrientation());
        categoryrview.addItemDecoration(mDividerItemDecoration);

        categoryAdapter = new CategoryAdapter();
        categoryrview.setAdapter(categoryAdapter);

        return rootView;
    }

    private void fetchCategories(String newtoken) {

        ApiEndPointsInterface api = RetrofitClient.getApiService(getContext());


        Call<Categories> categories = api.getCategories("Bearer " + newtoken);
        categories.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                Integer statusCode = response.code();
                Categories categories = response.body();

                categoryResults = categories.getResults();
                Bundle categoryBundle = new Bundle();
                categoryBundle.putParcelableArrayList(ALL_CATEGORIES, categoryResults);

                categoryAdapter.setCategoryData(categoryResults, getContext());


            }


            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getContext(), "Error, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(ALL_CATEGORIES, categoryResults);
        currentState.putString(ACCESS_TOKEN, newtoken);
    }

}
