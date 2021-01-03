package com.example.tvapp.listeners;

import com.example.tvapp.models.TVShow;

public interface WatchlistListener {
    void onTVShowClick(TVShow tvShow);

    void removeTVShowFromWatchlist(TVShow tvShow, int pos);
}
