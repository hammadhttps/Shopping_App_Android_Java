package com.example.shopping_app;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.shopping_app.databinding.ActivityCategoryBinding;

public class CategoryActivity extends AppCompatActivity implements
        CategoryListFragment.OnCategorySelectedListener,
        ProductListFragment.OnProductSelectedListener {

    private ActivityCategoryBinding binding;
    private boolean isLandscape;
    private ProductListFragment productListFragment;
    private ProductDetailFragment productDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Check orientation
        isLandscape = findViewById(R.id.product_container) != null;

        // Get selected category from MainActivity
        Category selectedCategory = (Category) getIntent().getSerializableExtra("selected_category");

        // Setup fragments based on orientation
        if (isLandscape) {
            setupLandscapeMode(selectedCategory);
        } else {
            setupPortraitMode(selectedCategory);
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!isLandscape && getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
            }
        });
    }

    private void setupLandscapeMode(Category selectedCategory) {
        // Show category list on left
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        categoryListFragment.setOnCategorySelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.category_container, categoryListFragment)
                .commit();

        // Show products of selected category on right
        if (selectedCategory != null) {
            productListFragment = ProductListFragment.newInstance(selectedCategory);
            productListFragment.setOnProductSelectedListener(this);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.product_container, productListFragment)
                    .commit();
        }
    }

    private void setupPortraitMode(Category selectedCategory) {
        if (selectedCategory != null) {
            // Show products of selected category
            productListFragment = ProductListFragment.newInstance(selectedCategory);
            productListFragment.setOnProductSelectedListener(this);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, productListFragment)
                    .commit();
        } else {
            // Show category list
            CategoryListFragment categoryListFragment = new CategoryListFragment();
            categoryListFragment.setOnCategorySelectedListener(this);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, categoryListFragment)
                    .commit();
        }
    }

    @Override
    public void onCategorySelected(Category category) {
        if (isLandscape) {
            // Update product list in right container
            if (productListFragment != null) {
                productListFragment.updateCategory(category);
            } else {
                productListFragment = ProductListFragment.newInstance(category);
                productListFragment.setOnProductSelectedListener(this);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.product_container, productListFragment)
                        .commit();
            }
        } else {
            // Replace with product list
            productListFragment = ProductListFragment.newInstance(category);
            productListFragment.setOnProductSelectedListener(this);

            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, productListFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onProductSelected(Product product) {
        if (isLandscape) {
            // Show/update detail in right container
            if (productDetailFragment != null) {
                productDetailFragment.updateProduct(product);
            } else {
                productDetailFragment = ProductDetailFragment.newInstance(product);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.product_container, productDetailFragment)
                        .commit();
            }
        } else {
            // Replace with product detail
            productDetailFragment = ProductDetailFragment.newInstance(product);

            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, productDetailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
