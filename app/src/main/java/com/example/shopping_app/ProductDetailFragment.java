package com.example.shopping_app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.shopping_app.databinding.FragmentProductDetailBinding;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailFragment extends Fragment {

    private FragmentProductDetailBinding binding;
    private Product product;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("product", (Serializable) product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = (Product) getArguments().getSerializable("product");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (product != null) {
            displayProductDetails();
        }

        binding.btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(getContext(), product.getName() + " added to cart",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void displayProductDetails() {
        binding.tvProductName.setText(product.getName());

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        binding.tvProductPrice.setText(formatter.format(product.getPrice()));

        binding.tvProductDescription.setText(product.getDescription());

        // Placeholder image - in real app use Glide
        binding.ivProductImage.setImageResource(R.drawable.product_placeholder);
    }

    public void updateProduct(Product newProduct) {
        this.product = newProduct;
        if (binding != null && isAdded()) {
            displayProductDetails();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
