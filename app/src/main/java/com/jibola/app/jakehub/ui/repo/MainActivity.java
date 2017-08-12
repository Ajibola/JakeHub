package com.jibola.app.jakehub.ui.repo;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jibola.app.jakehub.R;
import com.jibola.app.jakehub.app.JakeApplication;
import com.jibola.app.jakehub.domain.model.Repo;
import com.jibola.app.jakehub.ui.util.EndlessScrollListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RepoContract.View {

    @BindView(R.id.repo_recycler_view)
    RecyclerView repoRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    EndlessScrollListener endlessScrollListener = null;

    private final String STATE_REPO_LIST = "state_repo_list";

    @Inject
    RepoContract.Presenter repoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectComponents();
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        repoRecyclerView.setLayoutManager(layoutManager);
        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(layoutManager, 3) {
            @Override
            public void addFooterViewItem() {
                if ((repoRecyclerView.getAdapter()).getItemCount() > 0) {
                    ((RepoAdapter) repoRecyclerView.getAdapter()).addItem(null);
                }
            }

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                repoPresenter.loadMoreRepositories(page, false);
            }
        };

        repoRecyclerView.addOnScrollListener(endlessScrollListener);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoPresenter.refreshRepositories();
            }
        });

        repoPresenter.setView(this);

        if (savedInstanceState != null) {
            Serializable serializableObject = savedInstanceState.getSerializable(STATE_REPO_LIST);
            if (serializableObject instanceof ArrayList<?>) {
                ArrayList<Repo> repoList = (ArrayList<Repo>) serializableObject;
                displayRepositories(repoList, false);
            }
        } else {
            repoPresenter.refreshRepositories();
        }
    }

    private void injectComponents() {
        ((JakeApplication) getApplication()).getAppComponent()
                .inject(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (repoRecyclerView.getAdapter() != null) {
            ArrayList<Repo> currPhotoList = (ArrayList<Repo>) ((RepoAdapter) repoRecyclerView.getAdapter()).getAll();
            outState.putSerializable(STATE_REPO_LIST, currPhotoList);
        }
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(repoRecyclerView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayRepositories(List<Repo> repo, boolean refresh) {
        RepoAdapter adapter;

        if (repoRecyclerView.getAdapter() == null) {
            adapter = new RepoAdapter();
            repoRecyclerView.setAdapter(adapter);
        } else {
            adapter = (RepoAdapter) repoRecyclerView.getAdapter();
        }

        if (endlessScrollListener != null) {
            endlessScrollListener.onLoaded();

        }

        if (refresh) {
            adapter.refreshList(repo);
        } else {
            adapter.updateList(repo);
        }
    }
}
