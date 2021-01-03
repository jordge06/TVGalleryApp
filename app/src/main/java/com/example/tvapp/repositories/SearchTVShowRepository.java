package com.example.tvapp.repositories;

import android.util.Log;

import com.example.tvapp.network.ApiClient;
import com.example.tvapp.network.ApiService;
import com.example.tvapp.responses.GenreResponse;
import com.example.tvapp.responses.TVShowModelResponse;
import com.example.tvapp.responses.TVShowsResponse;
import com.example.tvapp.utilities.Constants;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVShowRepository {

    private ApiService apiService;

    public SearchTVShowRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowsResponse> searchTVShow(String query, int page) {
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.searchTVShows(query, page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponse> call, @NonNull Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<TVShowModelResponse> search(String query, int page) {
        MutableLiveData<TVShowModelResponse> data = new MutableLiveData<>();
        apiService.searchTV(query, page, Constants.API_KEY).enqueue(new Callback<TVShowModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowModelResponse> call, @NonNull Response<TVShowModelResponse> response) {
                if (response.code() != 200) return;
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowModelResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.v("Retrofit Error", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }

    public LiveData<GenreResponse> getGenreList() {
        MutableLiveData<GenreResponse> data = new MutableLiveData<>();
        apiService.getGenreList(Constants.API_KEY).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenreResponse> call, @NonNull Response<GenreResponse> response) {
                if (response.code() != 200) return;
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull  Call<GenreResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.v("Retrofit Error", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
