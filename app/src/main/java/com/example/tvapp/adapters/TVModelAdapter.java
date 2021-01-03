package com.example.tvapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tvapp.R;
import com.example.tvapp.databinding.ItemContainerTvModelBinding;
import com.example.tvapp.listeners.TVModelListener;
import com.example.tvapp.models.TVModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TVModelAdapter extends RecyclerView.Adapter<TVModelAdapter.TVShowViewHolder> {

    private List<TVModel> tvShows;
    private LayoutInflater layoutInflater;
    private TVModelListener listener;

    public TVModelAdapter(List<TVModel> tvShows) {
        this.tvShows = tvShows;
    }

    public void setListener(TVModelListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvModelBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_model, parent, false
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
        private ItemContainerTvModelBinding itemContainerTvShowBinding;

        public TVShowViewHolder(ItemContainerTvModelBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }

        public void bindTVShow(TVModel tvModel) {
            itemContainerTvShowBinding.setTvShow(tvModel);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(view -> listener.onTVShowClick(tvModel));
        }
    }
}
