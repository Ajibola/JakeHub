package com.jibola.app.jakehub.ui.repo;

import com.jibola.app.jakehub.data.local.LocalDataSource;
import com.jibola.app.jakehub.data.network.ApiDataSource;
import com.jibola.app.jakehub.domain.model.Repo;
import com.jibola.app.jakehub.support.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hp on 8/14/2017.
 */
public class RepoInteractorImplTest {

    @Mock
    ApiDataSource apiDataSource;

    @Mock
    LocalDataSource localDataSource;

    RepoInteractorImpl repoInteractor;
    List<Repo> repoList = new TestData().getRepoList("jakegit.json");

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(apiDataSource.getRepos(anyString(), anyInt(), anyInt())).thenReturn(Observable.just(repoList));
        when(localDataSource.getRepos(anyString(), anyInt(), anyInt())).thenReturn(Observable.just(repoList));
        repoInteractor = new RepoInteractorImpl(apiDataSource, localDataSource);
    }

    @Test
    public void testLoadApiRepositories() throws Exception {
        TestObserver<List<Repo>> testObserver = new TestObserver<>();

        repoInteractor.loadCachedRepositories(0).subscribe(testObserver);

        // assert that no errors occured
        testObserver.assertNoErrors();

        // verify that get repos was called
        verify(apiDataSource).getRepos(anyString(), anyInt(), anyInt());

        // verify the values returned
        testObserver.assertValue(repoList);
    }

    @Test
    public void testLoadCachedRepositories() throws Exception {
        TestObserver<List<Repo>> testObserver = new TestObserver<>();

        repoInteractor.loadRepositories(0).subscribe(testObserver);

        testObserver.assertNoErrors();

        // verify that get repos was called
        verify(localDataSource).getRepos(anyString(), anyInt(), anyInt());

        // verify the values returned
        testObserver.assertValue(repoList);
    }

}