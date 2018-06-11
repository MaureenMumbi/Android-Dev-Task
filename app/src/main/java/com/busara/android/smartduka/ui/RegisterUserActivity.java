package com.busara.android.smartduka.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.fragments.RegisterUserFragment;

import butterknife.ButterKnife;

/**
 * Created by Mauryn on 6/9/2018.
 */

public class RegisterUserActivity extends AppCompatActivity {
    Bundle bundle = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_user);

        ButterKnife.bind(this);


        if (savedInstanceState == null) {

            bundle = getIntent().getExtras();
            ;
            FragmentManager fragmentManager = getSupportFragmentManager();
            final RegisterUserFragment registerUserFragment = new RegisterUserFragment();
            registerUserFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.register_fragment_container, registerUserFragment).commit();//.addToBackStack(null).commit();

        }


    }
}
