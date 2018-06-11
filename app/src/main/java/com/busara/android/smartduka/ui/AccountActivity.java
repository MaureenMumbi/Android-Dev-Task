package com.busara.android.smartduka.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.busara.android.smartduka.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.busara.android.smartduka.ui.LoginActivity.USERDETAILS;

/**
 * Created by Mauryn on 6/11/2018.
 */


public class AccountActivity extends AppCompatActivity {
    @BindView(R.id.firstname)
    TextView fnameview;

    @BindView(R.id.email)
    TextView emailview;

    @BindView(R.id.phonenumber)
    TextView phoneview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Context context = getApplicationContext();
        ButterKnife.bind(this);


        SharedPreferences userpreferences = context.getSharedPreferences(USERDETAILS, context.MODE_PRIVATE);

        //USERDETAILS
        String firstname = userpreferences.getString("FirstName", "No Name");
        String lastname = userpreferences.getString("LastName", "No Last Name");
        String email = userpreferences.getString("Email", "No Email");
        String phonenumber = userpreferences.getString("Phonenumber", "No Phone Number");


        fnameview.setText(firstname + " " + lastname);
        emailview.setText(email);
        phoneview.setText(phonenumber);

        getSupportActionBar().setTitle("User Details");
    }
}
