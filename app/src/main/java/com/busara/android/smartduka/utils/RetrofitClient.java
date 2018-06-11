package com.busara.android.smartduka.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.busara.android.smartduka.ui.LoginActivity.MYPREFERENCES;


/**
 * Created by Mauryn on 6/7/2018.
 */

public class RetrofitClient {
    private static String BASE_URL = "http://api.smartduka.busaracenterlab.org/";
    private static RetrofitClient instance;

    public static Retrofit getRetrofitInstance(Context context) {

        Gson gson = new GsonBuilder().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);


        final String token = context.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE).getString("access_token", "");
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder().
                        header("token", "Bearer" + " " + token)
                        .addHeader("Content-Type", "application/json")
                        .build();

//                  .method(request.method(), request.body())
//                        .addHeader("Cache-Control", "public, max-age=0")
//                        .addHeader("Content-Type", "application/json")

                Response response = chain.proceed(request);
                response.header("Content-Type", "application/json");

                //Log.e("Response in Client ",response.code() +"------"+ response.body().string()+"------" +response.body().contentType());
                return response;
            }
        });

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClientBuilder.build())
                .build();


    }

    public static ApiEndPointsInterface getApiService(Context context) {
        return getRetrofitInstance(context).create(ApiEndPointsInterface.class);
    }

//    public Call<ResponseBody> getToken(String grant_type, String client_id, String client_secret,
//                                       String email, String password) {
//        return getApiService().getTokenRequest(grant_type, client_id, client_secret, email, password);
//    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            throw new IllegalStateException("API has not been initialized.");
        }
        return instance;
    }


    //    public  void getToken(){
//      //  = api.getAccessToken(GRANT_TYPE, CLIENT_ID, CLIENT_SECRET);
//
//        Call<AccessToken> call=    getApiService().getAccessToken(new TokenRequest("password", "abH1JvgefcuAC33bigXvL7r75TiQfPh3PNS2qiP4", "uBz14WSGNmFGrIrMtMxFA2XoDDMqjPeVsdBXwnYAD07DK3aTd4yUTtM1a8F5OiJFzTqgDGCLtFF1wgT5aWe6Y83JX6ZXCVaatrWOgIBAi2c3zZnwcwioNNF2zZBmNv2Y"));
//
//        call.enqueue(new Callback<AccessToken>() {
//
//
//            @Override
//            public void onResponse(Call<AccessToken> call, retrofit2.Response<AccessToken> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<AccessToken> call, Throwable t) {}
//        });
//
//    }
    class BasicAuthInterceptor implements Interceptor {

        private String credentials;

        public BasicAuthInterceptor(String user, String password) {
            this.credentials = Credentials.basic(user, password);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials).build();
            return chain.proceed(authenticatedRequest);
        }
    }
}
