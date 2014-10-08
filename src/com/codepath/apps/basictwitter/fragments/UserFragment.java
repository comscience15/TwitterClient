package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;
import static com.codepath.apps.basictwitter.variables.Constants.SCREEN_NAME;

public class UserFragment extends TweetsFragment {

	public static UserFragment newInstance(String screenName){
		UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        fragment.setArguments(args);
        return fragment;
    }
	
	@Override
	public void loadTweets(long maxId) {
		TwitterApplication.getRestClient().getUserTimeline(getArguments().getString("screen_name"), maxId,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonTweets) {
						// Log.d("DEBUG", jsonTweets.toString());
						adapter.addAll(Tweet.fromJSON(jsonTweets));
					}

					@Override
					public void onFailure(Throwable t) {
						t.printStackTrace();
						Log.d("DEBUG", "ERROR-" + t.getMessage());
					}
				});
	}

}
