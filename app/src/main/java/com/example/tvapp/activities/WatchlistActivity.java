package com.example.tvapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tvapp.R;
import com.example.tvapp.adapters.NewWatchListAdapter;
import com.example.tvapp.databinding.ActivityWatchlistBinding;
import com.example.tvapp.listeners.WatchlistListener2;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.utilities.TempDataHolder;
import com.example.tvapp.viewmodels.WatchlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity implements WatchlistListener2 {

    private ActivityWatchlistBinding activityWatchlistBinding;
    private WatchlistViewModel viewModel;
    private NewWatchListAdapter adapter;
    private List<TVModel> watchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_watchlist);
        doInitialization();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        activityWatchlistBinding.imgBack.setOnClickListener(view -> onBackPressed());
        watchlist = new ArrayList<>();
        loadWatchlist();
    }

    private void loadWatchlist() {
        activityWatchlistBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.loadWatchList().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShows -> {
                    activityWatchlistBinding.setIsLoading(false);
                    if (watchlist.size() > 0) {
                        watchlist.clear();
                    }
                    watchlist.addAll(tvShows);
                    adapter = new NewWatchListAdapter(watchlist);
                    adapter.setListener(this);
                    activityWatchlistBinding.rvWatchlist.setAdapter(adapter);
                    activityWatchlistBinding.rvWatchlist.setVisibility(View.VISIBLE);
                    compositeDisposable.dispose();
                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TempDataHolder.IS_WATCHLIST_UPDATED) {
            loadWatchlist();
            TempDataHolder.IS_WATCHLIST_UPDATED = false;
        }
    }

    @Override
    public void onClick(TVModel tvShow) {
        Intent intent = new Intent(WatchlistActivity.this, DetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }

    @Override
    public void removeItem(TVModel tvShow, int pos) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.removeTVShowFromWatchlist(tvShow)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    watchlist.remove(pos);
                    adapter.notifyItemRemoved(pos);
                    adapter.notifyItemChanged(pos, adapter.getItemCount());
                    compositeDisposable.dispose();
                }));
    }
}