package com.busara.android.smartduka.utils;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mauryn on 6/7/2018.
 */

public interface ApiEndPointsInterface {


    @GET("/oauth/token/")
    Call<ResponseBody> getTokenRequest(@Query("grant_type") String grant_type,
                                       @Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret,
                                       @Query("email") String email,
                                       @Query("password") String password);


    @FormUrlEncoded
    @POST("/oauth/token/")
    Call<ResponseBody> basicLogin(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password,
            @Field(" -u") String u,
            @Field("clientID") String clientID,
            @Field("colon") String colon,
            @Field("clientSecret") String clientSecret

    );


    @GET("/#users-current-user-list")
    Call<ResponseBody> getCurrentUsers(@Header("Authorization") String token);

    @GET("/api/v1/locations/")
    Call<Locations> getLocations();


    @GET("/api/v1/videos/")
    Call<Video> getVideos(@Header("Authorization") String token);


    @POST("/api/v1/users/")
    Call<ResponseBody> createUser(@Body RequestBody params);


    @GET("/api/v1/videos/")
        //Call<Video> getSearchedVideos(@Header("Authorization") String token, @Query("page") Integer page, @Query("page_size") Integer page_size, @Query("search") String search_category_id);
    Call<Video> getSearchedVideos(@Header("Authorization") String token, @Query("category_id") String search_category_id,
                                  @Query("category_name") String search_category_name);

    //?page=1&page_size=10&search=Customer%20Service
    @GET("/api/v1/users/current-user/")
    Call<ResponseBody> getCurrentUser(@Header("Authorization") String token);

    @GET("/api/v1/categories/")
    Call<Categories> getCategories(@Header("Authorization") String token);


//    @GET("group/{id}/users")
//    Call<List<User>> groupList(@Path("search_category_id") int search_category_id, @Query("search") String search);


//    Get details of currently logged in user. You can display logged in user details here e.g. first/last
//    name, location, etc (you creativity)
//    a. <base url>/#users-current-user-list
//3. Get all locations. This is publicly available
//    a. <base url>/#locations-list
//4. Register user. This is publicly available. A user is associated with a location. See get locations
//    API above.
//    a. <base url>/#users-create
//5. List all music/video categories. This is after a successful login
//    a. <base url>/#categories-list
//6. List music/video in a category
//    a. <base url>/#videos-list
//    b. This request requires a search term. Searches can be done by category name and
//    category ID. e.g. <base url>/api/v1/videos/?category_id=search_category_id
}
