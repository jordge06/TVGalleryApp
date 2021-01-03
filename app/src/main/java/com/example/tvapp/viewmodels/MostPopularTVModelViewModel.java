package com.example.tvapp.viewmodels;

import com.example.tvapp.repositories.MostPopularTVShowModelRepository;
import com.example.tvapp.repositories.MostPopularTVShowRepository;
import com.example.tvapp.responses.TVShowModelResponse;
import com.example.tvapp.responses.TVShowsResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MostPopularTVModelViewModel extends ViewModel {

    private MostPopularTVShowModelRepository repository ;

    public MostPopularTVModelViewModel() {
        repository = new MostPopularTVShowModelRepository();
    }

    public LiveData<TVShowModelResponse> getMostPopularTVShow(int page) {
        return repository.getMostPopularTvShows(page);
    }
}
