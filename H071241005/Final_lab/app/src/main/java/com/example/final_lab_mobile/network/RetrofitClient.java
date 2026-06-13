package com.example.final_lab_mobile.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://dummyjson.com/";
    private static Retrofit retrofit;

    public static ApiService getInstance() {
        if (retrofit == null) {
            // Menggunakan MockInterceptor agar data yang muncul adalah data outdoor
            // sesuai permintaan, tetapi tetap menggunakan library Retrofit.
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new MockInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
