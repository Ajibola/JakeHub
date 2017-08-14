package com.jibola.app.jakehub.ui.util;

import android.content.Context;

import com.jibola.app.jakehub.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.HttpException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hp on 8/13/2017.
 */
public class ErrorMapperTest {

    @Mock
    private Context context;
    private ErrorMapper errorMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // create
        errorMapper = new ErrorMapper(context);
    }

    @Test
    public void testHttpErrorString() throws Exception {
        // create mock throwable of type HttpException
        Throwable throwable = mock(HttpException.class);

        // when an error string is fetched for exception, return HttpError
        when(context.getString(R.string.error_http)).thenReturn("HttpError");

        // get out put of error string for throwable
        String errorMessage = errorMapper.getErrorString(throwable);

        // assert that the error message mocked response is equal to HttpError
        assertEquals(errorMessage, "HttpError");
    }

    @Test
    public void testDefaultErrorString() throws Exception {
        // create mock throwable of any type of exception RuntimeException
        Throwable throwable = mock(RuntimeException.class);

        // when an error string is fetched for returned error
        when(context.getString(R.string.error_default)).thenReturn("DefaultError");

        // get out put of error string for throwable
        String errorMessage = errorMapper.getErrorString(throwable);

        // assert that the error message mocked response is equal to HttpError
        assertEquals(errorMessage, "DefaultError");
    }

}