package com.example.shopping_app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shopping_app.databinding.FragmentCategoryListBinding;
import java.util.ArrayList;
import java.util.List;

public class CategoryListFragment extends Fragment {

    private FragmentCategoryListBinding binding;
    private CategoryAdapter adapter;
    private OnCategorySelectedListener listener;

    public interface OnCategorySelectedListener {
        void onCategorySelected(Category category);
    }

    public CategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
        setupRecyclerView();
        loadCategories();
    }

    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new CategoryAdapter(new ArrayList<>(), category -> {
            if (listener != null) {
                listener.onCategorySelected(category);
            }
        });

        binding.recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCategories.setAdapter(adapter);
    }

    private void loadCategories() {
        // Sample data - in real app, this would come from API or database
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Electronics", "📱", 24));
        categories.add(new Category(2, "Clothing", "👕", 56));
        categories.add(new Category(3, "Books", "📚", 32));
        categories.add(new Category(4, "Home & Garden", "🏠", 18));
        categories.add(new Category(5, "Sports", "⚽", 15));
        categories.add(new Category(6, "Toys", "🎮", 28));

        adapter.updateCategories(categories);
    }

    public void setOnCategorySelectedListener(OnCategorySelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
