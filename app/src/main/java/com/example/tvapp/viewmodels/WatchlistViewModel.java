package com.example.tvapp.viewmodels;

import android.app.Application;

import com.example.tvapp.database.TVShowDatabase;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {

    private TVShowDatabase tvShowDatabase;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public Flowable<List<TVModel>> loadWatchList() {
        return tvShowDatabase.tvShowDao().getWatchlist();
    }

    public Completable removeTVShowFromWatchlist(TVModel tvShow) {
        return tvShowDatabase.tvShowDao().removeFromWatchList(tvShow);
    }
}
