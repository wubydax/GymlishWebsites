package com.wubydax.gymlishwebsites.data;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.res.Resources;

import com.wubydax.gymlishwebsites.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by dax on 12/11/16.
 * Custom Async loader to handle graceful data fetching and parsing and eliminate need to reloading the data when it's in loader.
 */

public class InfoLoader extends AsyncTaskLoader<ArrayList<WebsiteInfo>> {
    private Resources mResources;
    private ArrayList<WebsiteInfo> mInfoArrayList;


    public InfoLoader(Context context) {
        super(context);
        mResources = context.getResources();
    }

    @Override
    public ArrayList<WebsiteInfo> loadInBackground() {
        ArrayList<WebsiteInfo> list = new ArrayList<>();

        InputStream inputStream = mResources.openRawResource(R.raw.websites);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            for(int i = 0; i < jsonArray.length(); i++) {
                WebsiteInfo websiteInfo = new WebsiteInfo(jsonArray.getJSONObject(i));
                list.add(websiteInfo);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    @Override
    public void deliverResult(ArrayList<WebsiteInfo> data) {
        mInfoArrayList = data;
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        if(mInfoArrayList != null && mInfoArrayList.size() > 0) {
            deliverResult(mInfoArrayList);
        } else {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        mInfoArrayList = null;
    }
}
