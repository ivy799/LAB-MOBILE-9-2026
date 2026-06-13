package com.example.final_lab_mobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_lab_mobile.R;
import com.example.final_lab_mobile.activity.DetailProductActivity;
import com.example.final_lab_mobile.adapter.ProductAdapter;
import com.example.final_lab_mobile.model.Product;
import com.example.final_lab_mobile.repository.ProductRepository;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ProductAdapter adapter;
    private ProductRepository repository;
    private ProgressBar progressBar;
    private LinearLayout layoutError;
    private TextView tvErrorMsg;
    private RecyclerView rvProducts;
    private TextInputEditText etSearch;
    private ChipGroup chipGroup;
    
    private List<Product> allProducts = new ArrayList<>();
    private String currentCategory = "All";
    private String searchQuery = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        repository = new ProductRepository(requireContext());
        rvProducts = view.findViewById(R.id.rvProducts);
        progressBar = view.findViewById(R.id.progressBar);
        layoutError = view.findViewById(R.id.layoutError);
        tvErrorMsg = view.findViewById(R.id.tvErrorMsg);
        etSearch = view.findViewById(R.id.etSearch);
        chipGroup = view.findViewById(R.id.chipGroup);
        Button btnRefresh = view.findViewById(R.id.btnRefresh);

        adapter = new ProductAdapter(new ArrayList<>(), product -> {
            Intent intent = new Intent(requireContext(), DetailProductActivity.class);
            intent.putExtra("EXTRA_PRODUCT", product);
            startActivity(intent);
        });

        rvProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProducts.setAdapter(adapter);

        setupListeners();
        
        btnRefresh.setOnClickListener(v -> loadData());
        loadData();

        return view;
    }

    private void setupListeners() {
        if (etSearch != null) {
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    searchQuery = s.toString().toLowerCase().trim();
                    applyFilters();
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        if (chipGroup != null) {
            chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
                if (checkedIds.isEmpty()) {
                    currentCategory = "All";
                } else {
                    int checkedId = checkedIds.get(0);
                    if (checkedId == R.id.chipTents) {
                        currentCategory = "Tents";
                    } else if (checkedId == R.id.chipBackpacks) {
                        currentCategory = "Backpacks";
                    } else if (checkedId == R.id.chipFootwear) {
                        currentCategory = "Footwear";
                    } else {
                        currentCategory = "All";
                    }
                }
                applyFilters();
            });
        }
    }

    private void applyFilters() {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : allProducts) {
            String category = product.getCategory() != null ? product.getCategory() : "";
            String title = product.getTitle() != null ? product.getTitle() : "";
            String description = product.getDescription() != null ? product.getDescription() : "";

            boolean matchesCategory = currentCategory.equals("All") || 
                    category.equalsIgnoreCase(currentCategory) ||
                    (currentCategory.equals("Tents") && category.toLowerCase().contains("tenda")) ||
                    (currentCategory.equals("Backpacks") && category.toLowerCase().contains("carrier")) ||
                    (currentCategory.equals("Backpacks") && category.toLowerCase().contains("bag")) ||
                    (currentCategory.equals("Backpacks") && category.toLowerCase().contains("backpack"));
            
            // Fallback: If category field is empty but title contains the keyword
            if (!matchesCategory && !currentCategory.equals("All")) {
                if (currentCategory.equals("Tents") && title.toLowerCase().contains("tenda")) matchesCategory = true;
                else if (currentCategory.equals("Backpacks") && (title.toLowerCase().contains("bag") || title.toLowerCase().contains("carrier") || title.toLowerCase().contains("backpack"))) matchesCategory = true;
                else if (currentCategory.equals("Footwear") && (title.toLowerCase().contains("sepatu") || title.toLowerCase().contains("sandal") || title.toLowerCase().contains("hiking"))) matchesCategory = true;
            }

            boolean matchesSearch = searchQuery.isEmpty() || 
                    title.toLowerCase().contains(searchQuery) ||
                    description.toLowerCase().contains(searchQuery);

            if (matchesCategory && matchesSearch) {
                filteredList.add(product);
            }
        }
        adapter.updateData(filteredList);
    }

    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
        rvProducts.setVisibility(View.GONE);

        repository.fetchProducts((products, error) -> {
            if (!isAdded()) return;
            progressBar.setVisibility(View.GONE);
            if (products != null) {
                rvProducts.setVisibility(View.VISIBLE);
                allProducts = products;
                applyFilters();
            } else {
                layoutError.setVisibility(View.VISIBLE);
                tvErrorMsg.setText(error != null ? error : "Terjadi kesalahan");
            }
        });
    }
}
