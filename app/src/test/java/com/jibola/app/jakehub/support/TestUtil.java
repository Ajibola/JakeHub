package com.jibola.app.jakehub.support;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hp on 8/13/2017.
 * A helper class to load test resources
 */
public class TestUtil {

    private static final String TAG = TestUtil.class.getSimpleName();

    public String loadResourceFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File Not Found");
        } catch (IOException e) {
            Log.d(TAG, "IO Exception");
        }

        return null;
    }
}
