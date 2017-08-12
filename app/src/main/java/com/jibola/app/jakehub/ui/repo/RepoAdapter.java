package com.jibola.app.jakehub.ui.repo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jibola.app.jakehub.R;
import com.jibola.app.jakehub.domain.model.Repo;
import com.jibola.app.jakehub.ui.widget.FooterViewHolder;
import com.jibola.app.jakehub.ui.widget.RepoViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hp on 8/10/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Repo> repoList;

    public static final int REPO_VIEW_TYPE = 100;
    public static final int FOOTER_VIEW_TYPE = 102;

    public RepoAdapter() {
        repoList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        final Repo repo = (Repo) repoList.get(position);

        if (repo == null) {
            return FOOTER_VIEW_TYPE;
        } else {
            return REPO_VIEW_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_footer, parent, false);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_repo, parent, false);
            return new RepoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == REPO_VIEW_TYPE) {
            RepoViewHolder repoViewHolder = (RepoViewHolder) holder;
            Repo repo = repoList.get(position);
            repoViewHolder.bind(repo);
        }
    }

    public void addItem(Repo item) {
        if (!repoList.contains(item)) {
            repoList.add(item);
            notifyItemInserted(repoList.size() - 1);
        }
    }

    public void removeItem(int position) {
        if (repoList.size() > position) {
            repoList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public List<Repo> getAll() {
        return repoList;
    }

    public void refreshList(Collection<Repo> collection) {
        repoList.clear();
        repoList.addAll(collection);
        notifyDataSetChanged();
    }

    public void updateList(Collection<Repo> collection) {
        int oldRange = repoList.size();
        repoList.addAll(collection);
        int newRange = repoList.size();
        notifyItemRangeInserted(oldRange, newRange);
    }
}
