package com.tim.task_15_search_mvvm.adapter;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tim.task_15_search_mvvm.R;

/**
 * Created by Tim on 12.03.2017.
 */

public final class BindingAdapter {

    public BindingAdapter() {
        throw new AssertionError();
    }

    @android.databinding.BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
