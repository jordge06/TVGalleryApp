package com.example.tvapp.viewmodels;

import com.example.tvapp.repositories.MostPopularTVShowRepository;
import com.example.tvapp.responses.TVShowsResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowRepository repository ;

    public MostPopularTVShowsViewModel() {
        repository = new MostPopularTVShowRepository();
    }

    public LiveData<TVShowsResponse> getMostPopularTVShow(int page) {
        return repository.getMostPopularTvShows(page);
    }
}
