package com.example.tvapp.viewmodels;

import com.example.tvapp.repositories.SearchTVShowRepository;
import com.example.tvapp.responses.GenreResponse;
import com.example.tvapp.responses.TVShowModelResponse;
import com.example.tvapp.responses.TVShowsResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SearchTVShowViewModel extends ViewModel {
    private SearchTVShowRepository searchTVShowRepository;

    public SearchTVShowViewModel() {
        searchTVShowRepository = new SearchTVShowRepository();
    }

    public LiveData<TVShowsResponse> searchTVShow(String query, int page) {
        return searchTVShowRepository.searchTVShow(query, page);
    }

    public LiveData<GenreResponse> getGenreList() {
        return searchTVShowRepository.getGenreList();
    }

    public LiveData<TVShowModelResponse> searchTVModel(String query, int page) {
        return searchTVShowRepository.search(query, page);
    }
}
