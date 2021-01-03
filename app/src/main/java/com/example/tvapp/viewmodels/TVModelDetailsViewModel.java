package com.example.tvapp.viewmodels;

import android.app.Application;

import com.example.tvapp.database.TVShowDatabase;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVModelDetails;
import com.example.tvapp.repositories.TVModelDetailsRepository;
import com.example.tvapp.responses.TVShowImagesResponse;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TVModelDetailsViewModel extends AndroidViewModel {

    private TVModelDetailsRepository repository;
    private TVShowDatabase tvShowDatabase;

    public TVModelDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new TVModelDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TVModelDetails> getTVShowDetails(int id) {
        return repository.getTVShowDetails(id);
    }

    public LiveData<TVShowImagesResponse> getImages(int id) {
        return repository.getImages(id);
    }

    public Completable addToWatchlist(TVModel tvShow) {
        return tvShowDatabase.tvShowDao().addToWatchlist(tvShow);
    }

    public Flowable<TVModel> getTVShowFromWatchlist(String id) {
        return tvShowDatabase.tvShowDao().getTVShowFromWatchlist(id);
    }

    public Completable removeTVShowFromWatchlist(TVModel tvShow) {
        return tvShowDatabase.tvShowDao().removeFromWatchList(tvShow);
    }
}
