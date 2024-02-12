package com.example.themovieapp.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit=null;
    private static String Base_Url="https://api.themoviedb.org/3/";

    public static MovieApiService getService(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MovieApiService.class);
    }

}
