package com.example.tvapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvapp.R;
import com.example.tvapp.databinding.ItemContainerBinding;
import com.example.tvapp.databinding.ItemContainerTvShowBinding;
import com.example.tvapp.listeners.WatchlistListener2;
import com.example.tvapp.models.TVModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewWatchListAdapter extends RecyclerView.Adapter<NewWatchListAdapter.TVShowViewHolder> {

    private List<TVModel> tvShows;
    private LayoutInflater layoutInflater;
    private WatchlistListener2 listener;

    public NewWatchListAdapter(List<TVModel> tvShows) {
        this.tvShows = tvShows;
    }

    public void setListener(WatchlistListener2 listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container, parent, false
        );
        return new TVShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        private ItemContainerBinding itemContainerTvShowBinding;

        public TVShowViewHolder(ItemContainerBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }

        public void bindTVShow(TVModel tvShow) {
            itemContainerTvShowBinding.setTvShow(tvShow);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(view -> listener.onClick(tvShow));
            itemContainerTvShowBinding.imgDelete.setOnClickListener(view -> listener.removeItem(tvShow, getAdapterPosition()));
        }
    }
}
