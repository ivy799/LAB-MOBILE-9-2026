package com.example.final_lab_mobile.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.example.final_lab_mobile.model.Product;
import com.example.final_lab_mobile.model.ProductResponse;
import com.example.final_lab_mobile.network.RetrofitClient;
import com.example.final_lab_mobile.storage.SharedPrefsManager;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private SharedPrefsManager prefsManager;
    private ExecutorService executor;
    private Handler handler;

    public interface RepositoryCallback {
        void onResult(List<Product> products, String error);
    }

    public ProductRepository(Context context) {
        prefsManager = new SharedPrefsManager(context);
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    public void fetchProducts(RepositoryCallback callback) {
        // Sekarang memanggil API yang sudah di-mock dengan 50 data outdoor
        RetrofitClient.getInstance().getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body().getProducts();
                    
                    executor.execute(() -> {
                        prefsManager.saveCache(products);
                        handler.post(() -> callback.onResult(products, null));
                    });
                } else {
                    loadFromCache(callback);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                loadFromCache(callback);
            }
        });
    }

    private void loadFromCache(RepositoryCallback callback) {
        executor.execute(() -> {
            List<Product> cached = prefsManager.getCache();
            handler.post(() -> {
                if (cached != null && !cached.isEmpty()) {
                    callback.onResult(cached, "Menampilkan data offline");
                } else {
                    callback.onResult(null, "Tidak ada koneksi internet");
                }
            });
        });
    }
}
