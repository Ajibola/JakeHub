package com.jibola.app.jakehub.ui.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jibola.app.jakehub.R;
import com.jibola.app.jakehub.domain.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp on 8/10/2017.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_repo_name) TextView textRepoName;
    @BindView(R.id.txt_repo_description) TextView textRepoDescription;
    @BindView(R.id.txt_repo_watchers) TextView textRepoWatchers;
    @BindView(R.id.txt_repo_stars) TextView textRepoStars;
    @BindView(R.id.txt_repo_forks) TextView textRepoForks;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Repo repo) {
        textRepoName.setText(repo.name());
        textRepoDescription.setText(repo.description());
        textRepoWatchers.setText(String.valueOf(repo.watchers()));
        textRepoStars.setText(String.valueOf(repo.stars()));
        textRepoForks.setText(String.valueOf(repo.forks()));
    }
}
