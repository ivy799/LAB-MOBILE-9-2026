package com.example.final_lab_mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.final_lab_mobile.R;
import com.example.final_lab_mobile.model.Product;
import com.example.final_lab_mobile.storage.SharedPrefsManager;
import com.google.android.material.button.MaterialButton;

public class DetailProductActivity extends AppCompatActivity {
    private SharedPrefsManager prefsManager;
    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        prefsManager = new SharedPrefsManager(this);
        Product product = (Product) getIntent().getSerializableExtra("EXTRA_PRODUCT");

        if (product != null) {
            TextView tvName = findViewById(R.id.tvDetailName);
            TextView tvPrice = findViewById(R.id.tvDetailPrice);
            TextView tvDesc = findViewById(R.id.tvDetailDesc);
            TextView tvRating = findViewById(R.id.tvDetailRating);
            ImageView imgDetail = findViewById(R.id.imgDetail);
            MaterialButton btnFav = findViewById(R.id.btnFavorite);
            MaterialButton btnCheckout = findViewById(R.id.btnCheckout);

            tvName.setText(product.getTitle());
            tvPrice.setText("$" + product.getPrice());
            tvDesc.setText(product.getDescription());
            tvRating.setText("⭐ " + product.getRating());

            Glide.with(this).load(product.getThumbnail()).into(imgDetail);

            isFav = prefsManager.isFavorite(product.getId());
            updateFavButton(btnFav);

            btnFav.setOnClickListener(v -> {
                if (isFav) {
                    prefsManager.removeFavorite(product.getId());
                    Toast.makeText(this, "Dihapus dari Favorit", Toast.LENGTH_SHORT).show();
                } else {
                    prefsManager.saveFavorite(product);
                    Toast.makeText(this, "Ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
                }
                isFav = !isFav;
                updateFavButton(btnFav);
            });

            btnCheckout.setOnClickListener(v -> {
                if (prefsManager.isLoggedIn()) {
                    Intent intent = new Intent(this, CheckoutActivity.class);
                    intent.putExtra("EXTRA_PRODUCT", product);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Silakan daftar/masuk terlebih dahulu di menu Profil", Toast.LENGTH_LONG).show();
                }
            });
            
            findViewById(R.id.toolbar).setOnClickListener(v -> finish());
        }
    }

    private void updateFavButton(MaterialButton btn) {
        btn.setText(isFav ? "DI FAVORIT" : "WISHLIST");
    }
}