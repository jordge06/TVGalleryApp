package com.example.tvapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvapp.R;
import com.example.tvapp.databinding.ItemContainerTvSimiliarBinding;
import com.example.tvapp.listeners.SimilarTVShowListener;
import com.example.tvapp.listeners.TVModelListener;
import com.example.tvapp.listeners.TVShowListener;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVModelDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SimilarTVShowAdapter extends RecyclerView.Adapter<SimilarTVShowAdapter.MyViewHHolder> {

    private List<TVModel> tvModels;
    private LayoutInflater layoutInflater;
    private TVModelListener listener;

    public SimilarTVShowAdapter(List<TVModel> tvModels) {
        this.tvModels = tvModels;
    }

    public void setListener(TVModelListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvSimiliarBinding itemContainerTvSimiliarBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_similiar, parent, false
        );
        return new MyViewHHolder(itemContainerTvSimiliarBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHHolder holder, int position) {
        holder.bindData(tvModels.get(position));
    }

    @Override
    public int getItemCount() {
        return tvModels.size();
    }


    class MyViewHHolder extends RecyclerView.ViewHolder {
        ItemContainerTvSimiliarBinding itemContainerTvSimiliarBinding;

        public MyViewHHolder(ItemContainerTvSimiliarBinding itemContainerTvSimiliarBinding) {
            super(itemContainerTvSimiliarBinding.getRoot());
            this.itemContainerTvSimiliarBinding = itemContainerTvSimiliarBinding;
        }

        public void bindData(TVModel tvModel) {
            itemContainerTvSimiliarBinding.setUrl(tvModel.getPosterPath());
            itemContainerTvSimiliarBinding.executePendingBindings();
            itemContainerTvSimiliarBinding.getRoot().setOnClickListener(view -> listener.onTVShowClick(tvModel));
        }
    }
}
