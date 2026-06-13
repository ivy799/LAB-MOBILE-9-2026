package com.example.final_lab_mobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_lab_mobile.R;
import com.example.final_lab_mobile.activity.DetailProductActivity;
import com.example.final_lab_mobile.adapter.ProductAdapter;
import com.example.final_lab_mobile.model.Product;
import com.example.final_lab_mobile.storage.SharedPrefsManager;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private ProductAdapter adapter;
    private SharedPrefsManager prefsManager;
    private RecyclerView rvFavorites;
    private LinearLayout layoutEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        
        rvFavorites = view.findViewById(R.id.rvFavorites);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);
        view.findViewById(R.id.btnStartShopping).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.nav_home);
        });

        prefsManager = new SharedPrefsManager(requireContext());
        List<Product> favorites = prefsManager.getFavorites();
        
        adapter = new ProductAdapter(favorites, product -> {
            Intent intent = new Intent(requireContext(), DetailProductActivity.class);
            intent.putExtra("EXTRA_PRODUCT", product);
            startActivity(intent);
        });

        rvFavorites.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvFavorites.setAdapter(adapter);
        
        updateVisibility(favorites);
        
        return view;
    }

    private void updateVisibility(List<Product> products) {
        if (products == null || products.isEmpty()) {
            rvFavorites.setVisibility(View.GONE);
            layoutEmpty.setVisibility(View.VISIBLE);
        } else {
            rvFavorites.setVisibility(View.VISIBLE);
            layoutEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null && prefsManager != null) {
            List<Product> favorites = prefsManager.getFavorites();
            adapter.updateData(favorites);
            updateVisibility(favorites);
        }
    }
}
