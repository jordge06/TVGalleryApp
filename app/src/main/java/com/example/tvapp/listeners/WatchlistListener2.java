package com.example.tvapp.listeners;

import com.example.tvapp.models.TVModel;

public interface WatchlistListener2 {

    void onClick(TVModel tvModel);

    void removeItem(TVModel tvModel, int pos);
}
