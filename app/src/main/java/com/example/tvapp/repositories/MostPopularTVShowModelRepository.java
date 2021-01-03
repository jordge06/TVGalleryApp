package com.example.tvapp.repositories;

import android.util.Log;

import com.example.tvapp.network.ApiClient;
import com.example.tvapp.network.ApiService;
import com.example.tvapp.responses.TVShowModelResponse;
import com.example.tvapp.utilities.Constants;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTVShowModelRepository {

    private ApiService apiService;

    public MostPopularTVShowModelRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVShowModelResponse> getMostPopularTvShows(int page) {
        MutableLiveData<TVShowModelResponse> data = new MutableLiveData<>();
        apiService.getPopularTVShowModel(page, Constants.API_KEY).enqueue(new Callback<TVShowModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowModelResponse> call, @NonNull Response<TVShowModelResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowModelResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.v("Error Api", "onFailure: " + t.getMessage());
            }
        });
        return data;
    }
}
