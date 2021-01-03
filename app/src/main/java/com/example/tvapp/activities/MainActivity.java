package com.example.tvapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tvapp.R;
import com.example.tvapp.adapters.TVModelAdapter;
import com.example.tvapp.adapters.TVShowsAdapter;
import com.example.tvapp.databinding.ActivityMainBinding;
import com.example.tvapp.listeners.TVModelListener;
import com.example.tvapp.listeners.TVShowListener;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;
import com.example.tvapp.viewmodels.MostPopularTVModelViewModel;
import com.example.tvapp.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowListener, TVModelListener {

    // instance
    private MostPopularTVShowsViewModel viewModel;
    private MostPopularTVModelViewModel mostPopularTVModelViewModel;
    private ActivityMainBinding activityMainBinding;
    private TVShowsAdapter tvShowsAdapter;
    private TVModelAdapter tvModelAdapter;
    private int currentPage = 1;
    private int totalPages = 1;

    // vars
    private List<TVShow> tvShows = new ArrayList<>();
    private List<TVModel> tvModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization() {
        setRecycler();
//        activityMainBinding.rvTvShows.setHasFixedSize(true);
//        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
//        tvShowsAdapter = new TVShowsAdapter(tvShows);
//        activityMainBinding.rvTvShows.setAdapter(tvShowsAdapter);
//        tvShowsAdapter.setListener(this);
//        getMostPopularTVShows();
        activityMainBinding.rvTvShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.rvTvShows.canScrollVertically(1)) {
                    if (currentPage <= totalPages) {
                        currentPage += 1;
                        //getMostPopularTVShows();
                        getPopulars();
                    }
                }
            }
        });
        activityMainBinding.imgWatchList.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, WatchlistActivity.class)));
        activityMainBinding.imgSearch.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));
    }

    private void setRecycler() {
        activityMainBinding.rvTvShows.setHasFixedSize(true);
        mostPopularTVModelViewModel = new ViewModelProvider(this).get(MostPopularTVModelViewModel.class);
        tvModelAdapter = new TVModelAdapter(tvModels);
        activityMainBinding.rvTvShows.setAdapter(tvModelAdapter);
        tvModelAdapter.setListener(this);
        getPopulars();
    }

    private void getMostPopularTVShows() {
        viewModel.getMostPopularTVShow(currentPage).observe(this, tvShowsResponse -> {
            toggleLoading();
            if (tvShowsResponse != null) {
                totalPages = tvShowsResponse.getTotalPages();
                if (tvShowsResponse.getTvShows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(tvShowsResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    private void getPopulars() {
        toggleLoading();
        mostPopularTVModelViewModel.getMostPopularTVShow(currentPage).observe(this, tvShowModelResponse -> {
            toggleLoading();
            if (tvShowModelResponse != null) {
                totalPages = tvShowModelResponse.getTotalPages();
                if (tvShowModelResponse.getTvShows() != null) {
                    int oldCount = tvModels.size();
                    tvModels.addAll(tvShowModelResponse.getTvShows());
                    tvModelAdapter.notifyItemRangeInserted(oldCount, tvModels.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClick(TVShow tvShow) {
        Intent intent = new Intent(MainActivity.this, TVShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

    @Override
    public void onTVShowClick(TVModel tvModel) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("tvShow", tvModel);
        startActivity(intent);
    }
}