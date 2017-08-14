package com.jibola.app.jakehub.ui.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Adapted EndlessRecyclerView class
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold = 0;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 0;

    private int currentScrollState;
    private boolean loading = true;
    private boolean stopReload = false;
    RecyclerView.LayoutManager mLayoutManager;

    public EndlessScrollListener(LinearLayoutManager layoutManager, int threshold) {
        this.mLayoutManager = layoutManager;
        this.visibleThreshold = threshold;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && !stopReload && (lastVisibleItemPosition + visibleThreshold) >= totalItemCount) {
            currentPage++;
            addFooterViewItem();
            onLoadMore(currentPage, totalItemCount);
            previousTotalItemCount = totalItemCount+1;
        }
    }

    public abstract void addFooterViewItem();

    public abstract void onLoadMore(int page, int totalItemsCount);

    public void setLoading(boolean loading) {
        this.loading = loading;

        int totalItemCount = mLayoutManager.getItemCount();
        if(!loading && previousTotalItemCount > 0 && previousTotalItemCount == totalItemCount) {
            stopReload = true;
        }
    }

}
