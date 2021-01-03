package com.example.tvapp.network;

import com.example.tvapp.models.TVModelDetails;
import com.example.tvapp.responses.GenreResponse;
import com.example.tvapp.responses.TVShowDetailsResponse;
import com.example.tvapp.responses.TVShowImagesResponse;
import com.example.tvapp.responses.TVShowModelResponse;
import com.example.tvapp.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TVShowsResponse> getMostPopularTVShows(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);

    @GET("search")
    Call<TVShowsResponse> searchTVShows(@Query("q") String query, @Query("page") int page);

    @GET("tv/popular")
    Call<TVShowModelResponse> getPopularTVShowModel(
            @Query("page") int page,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}")
    Call<TVModelDetails> getTVModelDetails(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}/similar")
    Call<TVShowModelResponse> getSimilarTVShow(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("tv/{tv_id}/images")
    Call<TVShowImagesResponse> getTVShowImages(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("genre/tv/list")
    Call<GenreResponse> getGenreList(@Query("api_key") String apiKey);

    @GET("search/tv")
    Call<TVShowModelResponse> searchTV(@Query("query") String query,
                                       @Query("page") int page,
                                       @Query("api_key") String apiKey);
}
