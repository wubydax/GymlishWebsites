package com.wubydax.gymlishwebsites.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dax on 12/11/16.
 * Parcelable object for ease of bundling and passing with intents
 */

public class WebsiteInfo implements Parcelable {
    public String url, title;
    String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    WebsiteInfo(JSONObject jsonObject) { //Website info from json object constructor
        try {
            url = jsonObject.getString("url");
            title = jsonObject.getString("title");
            description = jsonObject.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public WebsiteInfo() { //Main "no data" constructor for default cases
        url = "https://www.gymglish.com/en/";
        title = "Gymlish home";
        description = "";
    }

    private WebsiteInfo(Parcel in) {
        this.url = in.readString();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<WebsiteInfo> CREATOR = new Parcelable.Creator<WebsiteInfo>() {
        @Override
        public WebsiteInfo createFromParcel(Parcel source) {
            return new WebsiteInfo(source);
        }

        @Override
        public WebsiteInfo[] newArray(int size) {
            return new WebsiteInfo[size];
        }
    };
}
