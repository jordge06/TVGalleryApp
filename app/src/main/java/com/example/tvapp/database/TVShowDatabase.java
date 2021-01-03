package com.example.tvapp.database;

import android.content.Context;

import com.example.tvapp.dao.TVShowDao;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;
import com.example.tvapp.utilities.Converter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = TVModel.class, version = 3, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class TVShowDatabase extends RoomDatabase {

    private static TVShowDatabase tvShowDatabase;

    public static TVShowDatabase getTvShowDatabase(Context context) {
        if (tvShowDatabase == null) {
            tvShowDatabase = Room.databaseBuilder(
                    context,
                    TVShowDatabase.class, "tv_show_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return tvShowDatabase;
    }

    public abstract TVShowDao tvShowDao();
}
