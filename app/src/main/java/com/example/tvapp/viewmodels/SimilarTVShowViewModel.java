package com.example.tvapp.viewmodels;

import com.example.tvapp.repositories.SimilarTVShowRepository;
import com.example.tvapp.responses.TVShowModelResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SimilarTVShowViewModel extends ViewModel {

    private SimilarTVShowRepository repository;

    public SimilarTVShowViewModel() {
        repository = new SimilarTVShowRepository();
    }

    public LiveData<TVShowModelResponse> getSimilarTVShow(int id) {
        return repository.getSimilarTVShow(id);
    }
}
