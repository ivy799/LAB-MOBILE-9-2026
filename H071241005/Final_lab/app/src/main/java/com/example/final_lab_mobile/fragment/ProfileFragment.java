package com.example.final_lab_mobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_lab_mobile.R;
import com.example.final_lab_mobile.activity.DetailProductActivity;
import com.example.final_lab_mobile.adapter.ProductAdapter;
import com.example.final_lab_mobile.model.Product;
import com.example.final_lab_mobile.storage.SharedPrefsManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    private SharedPrefsManager prefs;
    private LinearLayout layoutAuth, layoutUserInfo;
    private EditText etEmail, etPassword;
    private TextView tvUserEmail, tvProfileTitle, tvNoOrders;
    private MaterialButton btnLogin, btnRegister, btnLogout;
    private RecyclerView rvOrders;
    private ProductAdapter orderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        
        prefs = new SharedPrefsManager(requireContext());
        
        layoutAuth = view.findViewById(R.id.layoutAuth);
        layoutUserInfo = view.findViewById(R.id.layoutUserInfo);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        tvProfileTitle = view.findViewById(R.id.tvProfileTitle);
        tvNoOrders = view.findViewById(R.id.tvNoOrders);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);
        btnLogout = view.findViewById(R.id.btnLogout);
        rvOrders = view.findViewById(R.id.rvOrders);
        MaterialSwitch switchTheme = view.findViewById(R.id.switchTheme);

        // Setup Order History RecyclerView
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        orderAdapter = new ProductAdapter(new ArrayList<>(), product -> {
            Intent intent = new Intent(requireContext(), DetailProductActivity.class);
            intent.putExtra("EXTRA_PRODUCT", product);
            startActivity(intent);
        });
        rvOrders.setAdapter(orderAdapter);

        updateUI();

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(getContext(), "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (prefs.loginUser(email, pass)) {
                prefs.saveLastCredentials(email, pass);
                Toast.makeText(getContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show();
                etEmail.setText("");
                etPassword.setText("");
                updateUI();
            } else {
                Toast.makeText(getContext(), "Email atau password salah", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || pass.length() < 6) {
                Toast.makeText(getContext(), "Email valid dan password minimal 6 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            if (prefs.registerUser(email, pass)) {
                prefs.loginUser(email, pass);
                prefs.saveLastCredentials(email, pass);
                Toast.makeText(getContext(), "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();
                etEmail.setText("");
                etPassword.setText("");
                updateUI();
            } else {
                Toast.makeText(getContext(), "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(v -> {
            prefs.logout();
            updateUI();
        });

        switchTheme.setChecked(prefs.getTheme());
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.setTheme(isChecked);
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        return view;
    }

    private void updateUI() {
        if (prefs.isLoggedIn()) {
            layoutAuth.setVisibility(View.GONE);
            layoutUserInfo.setVisibility(View.VISIBLE);
            tvProfileTitle.setText("Profil Adventurer");
            tvUserEmail.setText(prefs.getUserEmail());
            
            // Load and display user-specific orders
            List<Product> orders = prefs.getOrders();
            if (orders.isEmpty()) {
                rvOrders.setVisibility(View.GONE);
                tvNoOrders.setVisibility(View.VISIBLE);
            } else {
                rvOrders.setVisibility(View.VISIBLE);
                tvNoOrders.setVisibility(View.GONE);
                orderAdapter.updateData(orders);
            }
        } else {
            layoutAuth.setVisibility(View.VISIBLE);
            layoutUserInfo.setVisibility(View.GONE);
            tvProfileTitle.setText("Daftar Akun");
            
            // Auto-fill last used credentials
            etEmail.setText(prefs.getLastEmail());
            etPassword.setText(prefs.getLastPassword());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (prefs != null) {
            updateUI();
        }
    }
}
