package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragement extends TweetsFragment {
	
	@Override
	public void fetchData(long max_id) {
		TwitterApplication.getRestClient().getMentionsTimeline(max_id, new JsonHttpResponseHandler() {
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
}
