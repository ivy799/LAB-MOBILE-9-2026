package com.example.final_lab_mobile.network;

import com.example.final_lab_mobile.model.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("products/search")
    Call<ProductResponse> searchProducts(@Query("q") String query);

    @GET("products")
    Call<ProductResponse> getProducts();
}
