package com.busara.android.smartduka.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.fragments.CategoryFragment;

import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Categories");

        if (savedInstanceState == null) {

            bundle = getIntent().getExtras();
            FragmentManager fragmentManager = getSupportFragmentManager();
            final CategoryFragment categoryFragment = new CategoryFragment();
            categoryFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.category_fragment_container, categoryFragment).commit();//.addToBackStack(null).commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_categories) {
            Intent intent = new Intent(this, CategoryActivity.class);
            startActivity(intent);
            return true;

        }

        if (id == R.id.action_video) {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
            return true;

        }

        if (id == R.id.action_account) {
            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);
            return true;

        }
        return super.onOptionsItemSelected(item);


    }
}
