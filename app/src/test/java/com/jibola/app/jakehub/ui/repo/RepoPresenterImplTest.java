package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.app.SchedulerProvider;
import com.jibola.app.jakehub.domain.model.Repo;
import com.jibola.app.jakehub.support.TestData;
import com.jibola.app.jakehub.ui.util.ErrorMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hp on 8/14/2017.
 */
public class RepoPresenterImplTest {

    @Mock
    RepoContract.Interactor interactor;

    @Mock
    SchedulerProvider appScheduler;

    @Mock
    RepoContract.View view;

    @Mock ErrorMapper errorMapper;

    RepoPresenterImpl repoPresenter;

    List<Repo> repoList = new TestData().getRepoList("jakegit.json");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // provide immediate thread response for testing
        when(appScheduler.mainThread())
                .thenReturn(Schedulers.trampoline());
        when(appScheduler.backgroundThread())
                .thenReturn(Schedulers.trampoline());

        repoPresenter = new RepoPresenterImpl(interactor, appScheduler);
    }

    @Test
    public void testRefreshRepositories() throws Exception {
        // loads an observable list of repos
        when(interactor.loadRepositories(0)).thenReturn(Observable.just(repoList));

        // attach view to presenter
        repoPresenter.setView(view);

        // refresh list of repositories
        repoPresenter.refreshRepositories();

        // verify that the interactor calls the load repositories method
        verify(interactor).loadRepositories(0);

        // verify that the repos are displayed appropriately
        verify(view).displayRepositories(repoList, true);

        // verify that show loading was called at least once
        verify(view, atLeastOnce()).showLoading();

        // verify that hideLoading was called at least once.
        verify(view, atLeastOnce()).hideLoading();
    }

    @Test
    public void testLoadMoreRepositories() throws Exception {
        // loads an observable list of repos
        when(interactor.loadRepositories(1)).thenReturn(Observable.just(repoList));

        // attach view to presenter
        repoPresenter.setView(view);

        repoPresenter.loadMoreRepositories(1, false);

        // verify that the interactor calls the load repositories method
        verify(interactor).loadRepositories(1);

        // verify that the repos are displayed appropriately
        verify(view).displayRepositories(repoList, true);

        // verify that show loading was called at least once
        verify(view, never()).showLoading();

        // verify that hideLoading was called at least once.
        verify(view, atLeastOnce()).hideLoading();
    }

    @Test
    public void testLoadRepositoriesErrorIsDisplayed() throws Exception {
        when(interactor.loadRepositories(0)).thenReturn(Observable.<List<Repo>>error(new Throwable("Error")));
        when(interactor.loadCachedRepositories(0)).thenReturn(Observable.just(repoList));
        repoPresenter.setView(view);
        repoPresenter.refreshRepositories();

        verify(view, atLeastOnce()).showLoading();
        verify(view, atLeastOnce()).hideLoading();
        verify(view).showMessage(any(Throwable.class));
    }

    @Test
    public void testLoadRepositoriesLoadFromCacheOnError() throws Exception {
        when(interactor.loadRepositories(0)).thenReturn(Observable.<List<Repo>>error(new Throwable("Error")));
        when(interactor.loadCachedRepositories(0)).thenReturn(Observable.just(repoList));
        repoPresenter.setView(view);
        repoPresenter.refreshRepositories();

        verify(interactor).loadRepositories(0);
        verify(interactor).loadCachedRepositories(0);
        verify(view, atLeastOnce()).showLoading();
        verify(view, atLeastOnce()).hideLoading();
        verify(view).displayRepositories(repoList, true);
    }

}