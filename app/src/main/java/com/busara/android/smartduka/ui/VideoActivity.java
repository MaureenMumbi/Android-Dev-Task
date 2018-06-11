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
import com.busara.android.smartduka.fragments.VideoFragment;

import butterknife.ButterKnife;

/**
 * Created by Mauryn on 6/9/2018.
 */

public class VideoActivity extends AppCompatActivity {

    Bundle bundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);


        if (savedInstanceState == null) {

            bundle = getIntent().getExtras();
//           Log.e("Access VideoActiv", bundle.getString(ACCESS_TOKEN));

            FragmentManager fragmentManager = getSupportFragmentManager();
            final VideoFragment videoFragment = new VideoFragment();
            videoFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.video_fragment_container, videoFragment).commit();//.addToBackStack(null).commit();

        }

        // getSupportActionBar().set("Maureen Mumbi");
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
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);

    }

}
