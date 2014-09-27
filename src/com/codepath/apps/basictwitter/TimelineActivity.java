package com.codepath.apps.basictwitter;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	private TwitterClient client;
	private ArrayList<Tweet> tweets;
	private ArrayAdapter<Tweet> aTweets;
	private ListView lvTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		client = TwitterApplication.getRestClient(); // call getRestClient() from TwitterClient class
		populateTimeline();
		initializeVariables();
	}
	
	private void initializeVariables() {
		lvTweets = (ListView) findViewById(R.id.ivTweets);
		tweets = new ArrayList<Tweet>();
		aTweets = new TweetArrayAdapter(this, tweets);
		lvTweets.setAdapter(aTweets);
		
		//set endless scroll
//		lvTweets.setOnScrollListener(new OnScrollListener() {
//			private int visibleThreshold = 5;
//			private int currentPage = 0;
//			private int previousTotalItemCount = 0;
//			private boolean loading = true;
//			private int startingPageIndex = 0;
//			
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
//				Log.i("DEBUG", "firstVisibleItem = " + firstVisibleItem);
//				Log.i("DEBUG", "visibleItemCount = " + visibleItemCount);
//				Log.i("DEBUG", "toalItemCount = " + totalItemCount);
//				
//			}
//		});
	}

	public void populateTimeline(){
		//send network request, get back json data
		client.getHomeTimeLine(new JsonHttpResponseHandler() {
			// from this case got a single object so use JSONObject from GET/home_timeline
			@Override
			public void onSuccess(JSONArray json) {
				aTweets.addAll(Tweet.fromJSONArray(json));
			}
			
			@Override
			public void onFailure(Throwable e, JSONArray s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}
		});
	}
}
