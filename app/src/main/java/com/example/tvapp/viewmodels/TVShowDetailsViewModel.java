package com.example.tvapp.viewmodels;

import android.app.Application;

import com.example.tvapp.database.TVShowDatabase;
import com.example.tvapp.models.TVShow;
import com.example.tvapp.repositories.TVShowDetailsRepository;
import com.example.tvapp.responses.TVShowDetailsResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TVShowDetailsViewModel extends AndroidViewModel {

    private TVShowDetailsRepository repository;
    private TVShowDatabase tvShowDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new TVShowDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return repository.getTVShowDetails(tvShowId);
    }

//    public Completable addToWatchlist(TVShow tvShow) {
//        return tvShowDatabase.tvShowDao().addToWatchlist(tvShow);
//    }
//
//    public Flowable<TVShow> getTVShowFromWatchlist(String id) {
//        return tvShowDatabase.tvShowDao().getTVShowFromWatchlist(id);
//    }
//
//    public Completable removeTVShowFromWatchlist(TVShow tvShow) {
//        return tvShowDatabase.tvShowDao().removeFromWatchList(tvShow);
//    }
}
