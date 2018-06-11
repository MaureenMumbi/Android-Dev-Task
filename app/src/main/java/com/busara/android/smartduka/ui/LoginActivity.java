package com.busara.android.smartduka.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.busara.android.smartduka.R;
import com.busara.android.smartduka.utils.ApiEndPointsInterface;
import com.busara.android.smartduka.utils.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mauryn on 6/8/2018.
 */

public class LoginActivity extends AppCompatActivity {


    public static final String MYPREFERENCES = "MyPrefs";
    public static final String USERDETAILS = "UserDetails";
    public static final String ACCESS_TOKEN = "Access_Token";
    SharedPreferences sharedpreferences;
    SharedPreferences userpreferences;
    Bundle bundle;
    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.loginbtn)
    Button mLoginBtn;

    @BindView(R.id.signupbtn)
    Button mSignUpBtn;

    public static String getBase64String(String value) throws UnsupportedEncodingException {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context context = getApplicationContext();
        ButterKnife.bind(this);

        sharedpreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        userpreferences = getSharedPreferences(USERDETAILS, Context.MODE_PRIVATE);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String pwd = password.getText().toString();

                Login(username, pwd);
            }
        });
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterUserActivity.class);

                startActivity(intent);
            }
        });
    }

    public String Login(String username, String password) {
        String clientID = "abH1JvgefcuAC33bigXvL7r75TiQfPh3PNS2qiP4";
        String clientSecret = "uBz14WSGNmFGrIrMtMxFA2XoDDMqjPeVsdBXwnYAD07DK3aTd4yUTtM1a8F5OiJFzTqgDGCLtFF1wgT5aWe6Y83JX6ZXCVaatrWOgIBAi2c3zZnwcwioNNF2zZBmNv2Y";
        String AccessToken = "";
        String auth = "";
        try {
            auth = "Basic " + getBase64String(clientID + ":" + clientSecret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ApiEndPointsInterface loginService = RetrofitClient.getApiService(getApplicationContext());
        Call<ResponseBody> call2 = loginService.
                basicLogin(auth, "password", username, password, " -u", clientID, ":", clientSecret);
        call2.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> calls, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String access_token = obj.getString("access_token");
                        String refresh_token = obj.getString("refresh_token");

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(getString(R.string.accesstoken), access_token);
                        editor.putString(getString(R.string.refreshtoken), refresh_token);

                        editor.commit();
//                        bundle.putString("access_token",access_token);
//                        bundle.putString("refresh_token",refresh_token);

                        Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                        intent.putExtra(ACCESS_TOKEN, access_token);
                        startActivity(intent);


                        getCurrentUser(access_token);
                        Toast.makeText(getApplicationContext(), "SuccessFully logged in ", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // error response, no access to resource?
                    Log.e("Login", "Fail :( " + response);
                    Toast.makeText(getApplicationContext(), "Login Failed " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });


        return AccessToken;

    }

    private void getCurrentUser(String newtoken) {
        ApiEndPointsInterface api = RetrofitClient.getApiService(getApplicationContext());

        Call<ResponseBody> currentUser = api.getCurrentUser("Bearer " + newtoken);
        currentUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    JSONObject obj = null;
                    try {
                        obj = new JSONObject(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String firstname = obj.getString("first_name");
                    String lastname = obj.getString("last_name");
                    String email = obj.getString("email");
                    String phonenumber = obj.getString("phone_number");

                    SharedPreferences.Editor editor = userpreferences.edit();

                    editor.putString("FirstName", firstname);
                    editor.putString("LastName", lastname);
                    editor.putString("Email", email);
                    editor.putString("Phonenumber", phonenumber);

                    editor.commit();

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
                Toast.makeText(getApplicationContext(), "Error, check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

