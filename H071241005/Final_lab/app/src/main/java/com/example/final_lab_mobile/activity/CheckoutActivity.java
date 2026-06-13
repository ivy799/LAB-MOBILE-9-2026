package com.example.final_lab_mobile.activity;

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

public class CheckoutActivity extends AppCompatActivity {
    private SharedPrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        prefsManager = new SharedPrefsManager(this);
        Product product = (Product) getIntent().getSerializableExtra("EXTRA_PRODUCT");

        if (product != null) {
            ImageView imgCheckout = findViewById(R.id.imgCheckout);
            TextView tvName = findViewById(R.id.tvCheckoutName);
            TextView tvPrice = findViewById(R.id.tvCheckoutPrice);
            TextView tvTotal = findViewById(R.id.tvTotalPayment);
            MaterialButton btnPay = findViewById(R.id.btnPay);

            Glide.with(this).load(product.getThumbnail()).into(imgCheckout);
            tvName.setText(product.getTitle());
            tvPrice.setText("$" + product.getPrice());
            tvTotal.setText("$" + product.getPrice());

            btnPay.setOnClickListener(v -> {
                prefsManager.saveOrder(product);
                Toast.makeText(this, "Pembayaran Berhasil! Pesanan Anda sedang diproses.", Toast.LENGTH_LONG).show();
                finish();
            });
        }
    }
}
