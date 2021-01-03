package com.example.tvapp.utilities;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvapp.models.GenreModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("android:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        try {
            imageView.setAlpha(0f);
            Picasso.get().load(Constants.IMAGE_URL + url).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } catch (Exception ignored) {

        }
    }

    @BindingAdapter("setGenre")
    public static void setGenres(TextView textView, Integer[] genre) {
        for (int g : genre) {
            String genreValue = g + " ";
            textView.append(genreValue);
        }
    }

    @BindingAdapter("setGenreName")
    public static void setGenreName(TextView textView, GenreModel[] genres) {
        if (genres != null) {
            for (GenreModel genre : genres) {
                String genreValue = genre.getName() + " ";
                textView.append(genreValue);
            }
        }

    }
}
