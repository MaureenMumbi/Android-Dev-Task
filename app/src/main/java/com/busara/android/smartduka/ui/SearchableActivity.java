package com.busara.android.smartduka.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.fragments.SearchResultsFragment;

import butterknife.ButterKnife;

/**
 * Created by Mauryn on 6/10/2018.
 */

public class SearchableActivity extends AppCompatActivity {
    public static final String EXTRA_SEARCH_QUERY = "search_query";

    private String query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.content_searchable);

        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);

        String searchquery = "";
        searchquery = handleIntent(getIntent());

        Log.e("query***", searchquery);

        if (savedInstanceState == null) {

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_SEARCH_QUERY, searchquery);

            FragmentManager fragmentManager = getSupportFragmentManager();
            final SearchResultsFragment searchFragment = new SearchResultsFragment();
            searchFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id
                    .search_fragment_container, searchFragment).addToBackStack(null)
                    .commit();


        }

        getSupportActionBar().setTitle("Search Results");

    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private String handleIntent(Intent intent) {
        String query = "";
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Log.i("Search Query", query);
        }
        return query;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_categories) {
//            Intent intent = new Intent(this, CategoryActivity.class);
//            startActivity(intent);
//            return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//
//
//    }

}

