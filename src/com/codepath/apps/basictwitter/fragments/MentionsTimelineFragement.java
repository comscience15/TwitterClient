package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.util.Log;

import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragement extends TweetsFragment {

	@Override
	public void loadTweets(long maxId) {
		TwitterApplication.getRestClient().getMentionsTimeline(maxId,
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
