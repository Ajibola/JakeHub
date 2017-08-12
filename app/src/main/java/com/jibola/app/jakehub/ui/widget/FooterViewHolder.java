package com.jibola.app.jakehub.ui.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jibola.app.jakehub.R;
import com.jibola.app.jakehub.domain.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 8/10/2017.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progress_wheel) ProgressBar progressWheel;

    public FooterViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
