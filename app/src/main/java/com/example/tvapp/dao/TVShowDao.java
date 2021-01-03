package com.example.tvapp.dao;

import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TVShowDao {

    @Query("SELECT * FROM tvModel")
    Flowable<List<TVModel>> getWatchlist();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchlist(TVModel tvModel);

    @Delete
    Completable removeFromWatchList(TVModel tvModel);

    @Query("SELECT * FROM tvModel WHERE id =:tvShowsId")
    Flowable<TVModel> getTVShowFromWatchlist(String tvShowsId);

}
