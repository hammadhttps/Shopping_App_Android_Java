package com.example.shopping_app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shopping_app.databinding.FragmentProductListBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    private FragmentProductListBinding binding;
    private ProductAdapter adapter;
    private OnProductSelectedListener listener;
    private Category currentCategory;

    public interface OnProductSelectedListener {
        void onProductSelected(Product product);
    }

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(Category category) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", (Serializable) category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentCategory = (Category) getArguments().getSerializable("category");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();

        if (currentCategory != null) {
            binding.tvCategoryTitle.setText(currentCategory.getName());
            loadProducts(currentCategory.getId());
        }
    }

    private void setupRecyclerView() {
        adapter = new ProductAdapter(new ArrayList<>(), product -> {
            if (listener != null) {
                listener.onProductSelected(product);
            }
        });

        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewProducts.setAdapter(adapter);
    }

    private void loadProducts(int categoryId) {
        // Sample data - in real app, this would come from API
        List<Product> products = new ArrayList<>();

        if (categoryId == 1) { // Electronics
            products.add(new Product(1, "Smartphone", 699.99,
                    "Latest model with 5G", "phone.jpg", 1));
            products.add(new Product(2, "Laptop", 1299.99,
                    "16GB RAM, 512GB SSD", "laptop.jpg", 1));
            products.add(new Product(3, "Headphones", 199.99,
                    "Wireless noise-cancelling", "headphones.jpg", 1));
        } else if (categoryId == 2) { // Clothing
            products.add(new Product(4, "T-Shirt", 29.99,
                    "Cotton, various colors", "tshirt.jpg", 2));
            products.add(new Product(5, "Jeans", 79.99,
                    "Slim fit, blue", "jeans.jpg", 2));
        } else {
            // Default products for other categories
            products.add(new Product(6, "Sample Product 1", 49.99,
                    "Description 1", "sample1.jpg", categoryId));
            products.add(new Product(7, "Sample Product 2", 89.99,
                    "Description 2", "sample2.jpg", categoryId));
        }

        adapter.updateProducts(products);
    }

    public void setOnProductSelectedListener(OnProductSelectedListener listener) {
        this.listener = listener;
    }

    public void updateCategory(Category category) {
        this.currentCategory = category;
        if (binding != null) {
            binding.tvCategoryTitle.setText(category.getName());
            loadProducts(category.getId());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
