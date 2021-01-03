package com.example.tvapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tvapp.R;
import com.example.tvapp.adapters.SimilarTVShowAdapter;
import com.example.tvapp.databinding.ActivityDetailsBinding;
import com.example.tvapp.listeners.TVModelListener;
import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVModelDetails;
import com.example.tvapp.utilities.TempDataHolder;
import com.example.tvapp.viewmodels.SimilarTVShowViewModel;
import com.example.tvapp.viewmodels.TVModelDetailsViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity implements TVModelListener {

    private SimilarTVShowAdapter adapter;
    private ActivityDetailsBinding activityDetailsBinding;
    private TVModelDetailsViewModel viewModel;
    private SimilarTVShowViewModel similarTVShowViewModel;
    private TVModel tvModel;
    private List<TVModel> tvModels = new ArrayList<>();
    private Boolean isOnWatchlist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        doInitialization();
    }

    private void doInitialization() {
        viewModel = new ViewModelProvider(this).get(TVModelDetailsViewModel.class);
        activityDetailsBinding.imgBack.setOnClickListener(view -> onBackPressed());
        tvModel = (TVModel) getIntent().getSerializableExtra("tvShow");
        setRecycler();
        getData();
        checkIfAlreadyAddedToWatchlist();
    }

    private void setRecycler() {
        adapter = new SimilarTVShowAdapter(tvModels);
        adapter.setListener(this);
        similarTVShowViewModel = new ViewModelProvider(this).get(SimilarTVShowViewModel.class);
        activityDetailsBinding.rvSimilarShows.setHasFixedSize(true);
        activityDetailsBinding.rvSimilarShows.setAdapter(adapter);
        getSimilarTVShow(tvModel.getId());
        activityDetailsBinding.imgWatchList.setVisibility(View.VISIBLE);
        activityDetailsBinding.imgWatchList.setOnClickListener(view -> {
            if (isOnWatchlist) removeFromWatchlist();
            else addToWatchList();
        });
    }

    private void getSimilarTVShow(int id) {
        similarTVShowViewModel.getSimilarTVShow(id).observe(this, tvShowModelResponse -> {
            activityDetailsBinding.setIsLoading(false);
            if (tvShowModelResponse != null) {
                if (tvShowModelResponse.getTvShows() != null) {
                    if (!tvModels.isEmpty()) {
                        tvModels.clear();
                    }
                    activityDetailsBinding.txtMore.setVisibility(View.VISIBLE);
                    activityDetailsBinding.rvSimilarShows.setVisibility(View.VISIBLE);
                    tvModels.addAll(tvShowModelResponse.getTvShows());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getData() {
        activityDetailsBinding.setIsLoading(true);
        int id = tvModel.getId();
        viewModel.getTVShowDetails(id).observe(this, tvModelDetails -> {
            if (tvModelDetails != null) {
                Log.v("BACKDROP", "Path: " + tvModelDetails.getBackdropPath());
                setData(tvModelDetails);
            }
        });
    }

    private void setData(TVModelDetails tvModelDetails) {
        activityDetailsBinding.setPoster(tvModel.getPosterPath());
        activityDetailsBinding.imgPoster.setVisibility(View.VISIBLE);
        activityDetailsBinding.imgTvShow.setVisibility(View.VISIBLE);
        activityDetailsBinding.fadingEdge.setVisibility(View.VISIBLE);
        activityDetailsBinding.setTvModelDetails(tvModelDetails);
        activityDetailsBinding.setDescription(
                String.valueOf(
                        HtmlCompat.fromHtml(
                                tvModelDetails.getOverview(),
                                HtmlCompat.FROM_HTML_MODE_LEGACY
                        )
                )
        );
        activityDetailsBinding.txtDescription.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtReadMore.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtReadMore.setOnClickListener(view -> {
            if (activityDetailsBinding.txtReadMore.getText().toString().equals("Read More")) {
                activityDetailsBinding.txtReadMore.setMaxLines(Integer.MAX_VALUE);
                activityDetailsBinding.txtReadMore.setEllipsize(null);
                activityDetailsBinding.txtReadMore.setText(R.string.read_less);
            } else {
                activityDetailsBinding.txtReadMore.setMaxLines(4);
                activityDetailsBinding.txtReadMore.setEllipsize(TextUtils.TruncateAt.END);
                activityDetailsBinding.txtReadMore.setText(R.string.read_more);
            }
        });
        activityDetailsBinding.setRating(
                String.format(
                        Locale.getDefault(),
                        "%.2f",
                        tvModelDetails.getRating()
                )
        );
        activityDetailsBinding.viewDivider1.setVisibility(View.VISIBLE);
        activityDetailsBinding.layoutMisc.setVisibility(View.VISIBLE);
        activityDetailsBinding.viewDivider2.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtName.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtRating.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtStarted.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtStatus.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtNetwork.setVisibility(View.VISIBLE);
        activityDetailsBinding.txtGenre.setVisibility(View.VISIBLE);

    }

    @Override
    public void onTVShowClick(TVModel tvModel) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("tvShow", tvModel);
        startActivity(intent);
    }

    private void checkIfAlreadyAddedToWatchlist() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.getTVShowFromWatchlist(String.valueOf(tvModel.getId()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvModel1 -> {
                    isOnWatchlist = true;
                    activityDetailsBinding.imgWatchList.setImageResource(R.drawable.ic_added);
                    compositeDisposable.dispose();
                })
        );
    }

    private void addToWatchList() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.addToWatchlist(tvModel)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    TempDataHolder.IS_WATCHLIST_UPDATED = true;
                    isOnWatchlist = true;
                    activityDetailsBinding.imgWatchList.setImageResource(R.drawable.ic_added);
                    showMessage("Added to Watchlist");
                    compositeDisposable.dispose();
                })
        );
    }

    private void removeFromWatchlist() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.removeTVShowFromWatchlist(tvModel)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    isOnWatchlist = false;
                    TempDataHolder.IS_WATCHLIST_UPDATED = true;
                    activityDetailsBinding.imgWatchList.setImageResource(R.drawable.ic_watchlist);
                    showMessage("Remove from Watchlist");
                    compositeDisposable.dispose();
                })
        );
    }

    private void showMessage(String message) {
        Snackbar snack = Snackbar.make(activityDetailsBinding.main, message, Snackbar.LENGTH_LONG);
        snack.setAction("View Watchlist", view ->
                startActivity(new Intent(DetailsActivity.this, WatchlistActivity.class)));
        snack.show();
    }
}