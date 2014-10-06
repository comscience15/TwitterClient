package com.codepath.apps.basictwitter.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.adapters.TweetArrayAdapter;
import com.codepath.apps.basictwitter.listeners.EndlessScrollListener;
import com.codepath.apps.basictwitter.models.Tweet;

public abstract class TweetsFragment extends Fragment{
//	protected PullToRefreshListView ptrTweets;
	private ListView ptrTweets;
	private List<Tweet> tweets;
	protected TweetArrayAdapter adapter;
//	private ProfileImageListener imgListener;
//	private TwitterArrayAdapter tArrAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		tArrAdapter = new TwitterArrayAdapter(getActivity(), new ArrayList<Tweet>());
//		setProfileListener(imgListener);
		initialVariables();
	}

	private void initialVariables() {
		tweets = new ArrayList<Tweet>();
		adapter = new TweetArrayAdapter(getActivity(), tweets);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// inflate layout
		View v = inflater.inflate(R.layout.fragment_tweets, container, false);
		// Assign our view references
		ptrTweets = (ListView) v.findViewById(R.id.lvTweets);
		ptrTweets.setAdapter(adapter);
		fetchData(0);
		
		ptrTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int page, int totalItemCount) {
				fetchData(tweets.get(tweets.size() - 1).getUId() - 1);
			}
			
		});
		
//		ptrTweets.setOnRefreshListener(new OnRefreshListener() {
//			
//			@Override
//			public void onRefresh() {
//				populateTimeLine(0, getTwitterHandler(true));
//			}
//		});
//		
//		populateTimeLine(0, getTwitterHandler(true));
		// return the layout view
		return v;
	}
	
	public TweetArrayAdapter getAdapter() {
		return adapter;
	}
	
	public abstract void fetchData(long max_id);

//	protected abstract void populateTimeLine(long max_id,AsyncHttpResponseHandler twitterHandler);
//	
//	private AsyncHttpResponseHandler getTwitterHandler(final boolean b) {
//		return new JsonHttpResponseHandler() {
//			@SuppressWarnings("unused")
//			public void onSuccess(JSONArray response) {
//				List<Tweet> tweets = Tweet.fromJSON(response);
//				if (b) {
//					getAdapter().clear();
//				}
//				getAdapter().addAll(tweets);
////				ptrTweets.onRefreshComplete();
//			}
//			
//			@SuppressWarnings("unused")
//			public void onFailure(Throwable throwable, JSONArray json) {
////				ptrTweets.onRefreshComplete();
//			}
//		};
//	}
//
//	public TwitterArrayAdapter getAdapter() {
//		return tArrAdapter;
//	}
//
////	// Delegate the adding to the internal adapter <-- this way is prefer cause minimize of calling (numbers of dots)
////	public void addAll(ArrayList<Tweet> tweets) {
////		ptrTweets.addAll(tweets);
////	}
////	
//	public void setProfileListener(ProfileImageListener imgListener) {
//		this.imgListener = imgListener;
//		if (tArrAdapter != null) {
//			tArrAdapter.setProfileListener(imgListener);
//		}
//	}
}
