package com.wubydax.gymlishwebsites;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.wubydax.gymlishwebsites.data.InfoLoader;
import com.wubydax.gymlishwebsites.data.SitesAdapter;
import com.wubydax.gymlishwebsites.data.WebsiteInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<WebsiteInfo>>, SitesAdapter.OnWebsiteClickListener {
    private SitesAdapter mSitesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSitesAdapter = new SitesAdapter(this);
        recyclerView.setAdapter(mSitesAdapter);

        //Using InfoLoader class to fetch sites info from json in raw resources and create a parcelable object list
        getLoaderManager().initLoader(46, null, this);


    }

    @Override
    public Loader<ArrayList<WebsiteInfo>> onCreateLoader(int i, Bundle bundle) {
        return new InfoLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<WebsiteInfo>> loader, ArrayList<WebsiteInfo> infoArrayList) {
        if(mSitesAdapter != null) mSitesAdapter.updateData(infoArrayList);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<WebsiteInfo>> loader) {
        if(mSitesAdapter != null) mSitesAdapter.updateData(null);
    }

    @Override
    public void OnWebsiteClick(WebsiteInfo websiteInfo) {
        //Interface callback method for handling recycler view item click
        //WebsiteInfo is passed as parcelable in invoking intent
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.BUNDLE_KEY, websiteInfo);
        startActivity(intent);
    }
}
