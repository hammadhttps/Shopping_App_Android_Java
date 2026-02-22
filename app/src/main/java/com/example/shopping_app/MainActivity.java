package com.example.shopping_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.shopping_app.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupRecyclerView();
        loadCategories();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    private void setupRecyclerView() {
        adapter = new CategoryAdapter(new ArrayList<>(), category -> {
            // Navigate to CategoryActivity
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            intent.putExtra("selected_category", (Parcelable) category);
            startActivity(intent);
        });

        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCategories.setAdapter(adapter);
    }

    private void loadCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Electronics", "📱", 24));
        categories.add(new Category(2, "Clothing", "👕", 56));
        categories.add(new Category(3, "Books", "📚", 32));
        categories.add(new Category(4, "Home & Garden", "🏠", 18));
        categories.add(new Category(5, "Sports", "⚽", 15));
        categories.add(new Category(6, "Toys", "🎮", 28));

        adapter.updateCategories(categories);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
