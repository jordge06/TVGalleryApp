package com.example.tvapp.repositories;

import android.util.Log;

import com.example.tvapp.models.TVModelDetails;
import com.example.tvapp.network.ApiClient;
import com.example.tvapp.network.ApiService;
import com.example.tvapp.responses.TVShowImagesResponse;
import com.example.tvapp.utilities.Constants;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVModelDetailsRepository {

    private ApiService apiService;

    public TVModelDetailsRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TVModelDetails> getTVShowDetails(int id) {
        MutableLiveData<TVModelDetails> data = new MutableLiveData<>();
        apiService.getTVModelDetails(id, Constants.API_KEY).enqueue(new Callback<TVModelDetails>() {
            @Override
            public void onResponse(@NonNull Call<TVModelDetails> call, @NonNull Response<TVModelDetails> response) {
                data.setValue(response.body());
                Log.v("Code", "Error: " + response.code());
                Log.v("Code", "Error: " + response.raw().request().url());
            }

            @Override
            public void onFailure(@NonNull Call<TVModelDetails> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.v("OnFailure", "Error: " + t.getMessage());
            }
        });
        return data;
    }

    public LiveData<TVShowImagesResponse> getImages(int id) {
        MutableLiveData<TVShowImagesResponse> data = new MutableLiveData<>();
        apiService.getTVShowImages(id, Constants.API_KEY).enqueue(new Callback<TVShowImagesResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowImagesResponse> call, @NonNull Response<TVShowImagesResponse> response) {
                if (response.code() != 200) return;
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowImagesResponse> call, @NonNull Throwable t) {
                data.setValue(null);
                Log.v("OnFailure", "Error: " + t.getMessage());

            }
        });
        return data;
    }
}
