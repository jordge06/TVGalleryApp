package com.example.tvapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.tvapp.R;
import com.example.tvapp.adapters.TVModelAdapter;
import com.example.tvapp.adapters.TVShowsAdapter;
import com.example.tvapp.databinding.ActivitySearchBinding;
import com.example.tvapp.listeners.TVModelListener;
import com.example.tvapp.listeners.TVShowListener;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;
import com.example.tvapp.responses.TVShowsResponse;
import com.example.tvapp.viewmodels.SearchTVShowViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity implements TVModelListener {

    private ActivitySearchBinding activitySearchBinding;
    private SearchTVShowViewModel viewModel;
    private TVModelAdapter tvModelAdapter;
    private List<TVModel> tvModels = new ArrayList<>();
    private int currentPage = 1;
    private int totalPage = 1;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        doInitialization();
    }

    private void doInitialization() {
        activitySearchBinding.imgBack.setOnClickListener(view -> onBackPressed());
        activitySearchBinding.rvTvShows.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(SearchTVShowViewModel.class);
        tvModelAdapter = new TVModelAdapter(tvModels);
        tvModelAdapter.setListener(this);
        activitySearchBinding.rvTvShows.setAdapter(tvModelAdapter);
        activitySearchBinding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().trim().isEmpty()) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                currentPage = 1;
                                totalPage = 1;
                                searchTVShow(editable.toString());
                            });
                        }
                    }, 800);
                } else {
                    tvModels.clear();
                    tvModelAdapter.notifyDataSetChanged();
                }
            }
        });
        activitySearchBinding.rvTvShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activitySearchBinding.rvTvShows.canScrollVertically(1)) {
                    if (!activitySearchBinding.inputSearch.getText().toString().isEmpty()) {
                        if (currentPage <= totalPage) {
                            currentPage += 1;
                            searchTVShow(activitySearchBinding.inputSearch.getText().toString());
                        }
                    }
                }
            }
        });
        activitySearchBinding.inputSearch.requestFocus();
    }

    private void searchTVShow(String query) {
        toggleLoading();
        viewModel.searchTVModel(query, currentPage).observe(this, tvShowsResponse -> {
            toggleLoading();
            if (tvShowsResponse != null) {
                totalPage = tvShowsResponse.getTotalPages();
                if (tvShowsResponse.getTvShows() != null) {
                    int oldCount = tvModels.size();
                    tvModels.addAll(tvShowsResponse.getTvShows());
                    tvModelAdapter.notifyItemRangeInserted(oldCount, tvModels.size());
                }

            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activitySearchBinding.getIsLoading() != null && activitySearchBinding.getIsLoading()) {
                activitySearchBinding.setIsLoading(false);
            } else {
                activitySearchBinding.setIsLoading(true);
            }
        } else {
            if (activitySearchBinding.getIsLoadingMore() != null && activitySearchBinding.getIsLoadingMore()) {
                activitySearchBinding.setIsLoadingMore(false);
            } else {
                activitySearchBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClick(TVModel tvModel) {
        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
        intent.putExtra("tvShow", tvModel);
        startActivity(intent);
    }
}
