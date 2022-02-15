package com.pas.pos_system.webServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataSource {

    private static final String BASE_URL = "http://10.0.2.2:8000/api/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService getAPIService() {return retrofit.create(ApiService.class);}

}
