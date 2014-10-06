package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;

import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserFragment extends TweetsFragment {
	
	@Override
	public void fetchData(long max_id) {
		TwitterApplication.getRestClient().getUserTimeline(getArguments().getString("screen_name"), max_id, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				adapter.addAll(Tweet.fromJSON(json));
			}
			
			@Override
			public void onFailure(Throwable e) {
				e.printStackTrace();
			}
		});
	}

	public static UserFragment newInstance(String screenName) {
		UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("screenname", screenName);
        fragment.setArguments(args);
		return null;
	}

}
