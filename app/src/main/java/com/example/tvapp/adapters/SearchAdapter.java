package com.example.tvapp.adapters;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tvapp.R;
import com.example.tvapp.databinding.ItemContainerGenreBinding;
import com.example.tvapp.models.GenreModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private GenreModel[] genreModels;
    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerGenreBinding itemContainerGenreBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_genre, parent, false);
        return new MyViewHolder(itemContainerGenreBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(genreModels[position]);
    }

    @Override
    public int getItemCount() {
        return genreModels.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemContainerGenreBinding itemContainerGenreBinding;

        public MyViewHolder(ItemContainerGenreBinding itemContainerGenreBinding) {
            super(itemContainerGenreBinding.getRoot());
            this.itemContainerGenreBinding = itemContainerGenreBinding;
        }

        public void bindData(GenreModel genreModel) {
            itemContainerGenreBinding.setName(genreModel.getName());
        }
    }
}
