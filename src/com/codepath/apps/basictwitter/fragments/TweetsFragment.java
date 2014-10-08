package com.codepath.apps.basictwitter.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.adapters.TweetArrayAdapter;
import com.codepath.apps.basictwitter.listeners.EndlessScrollListener;
import com.codepath.apps.basictwitter.models.Tweet;

public abstract class TweetsFragment extends Fragment{
	private ListView lvTweets;
	private List<Tweet> tweets;
	protected TweetArrayAdapter adapter;

	public TweetArrayAdapter getAdapter() {
		return adapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		adapter = new TweetArrayAdapter(getActivity(), tweets);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tweets, container, false);
		lvTweets = (ListView) view.findViewById(R.id.lvTweets);
		lvTweets.setAdapter(adapter);
		setUpTimelineView();
		setUpScrolling();
		//setUpOnClickListner(view);
		return view;
	}
	
//	private void setUpOnClickListner(View view) {
//		lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View tweetItemView, int position,
//					long id) {
//				Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
//				LinearLayout ll = (LinearLayout)tweetItemView.findViewById(R.id.llTweetItem);
//				
//				ImageView im = (ImageView)ll.findViewById(R.id.ivProfile);
//				Toast.makeText(getActivity(), "Hello-" +im, Toast.LENGTH_SHORT).show();
//				start Intent now.....
//			}
//			
//		});
//		
//	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void setUpTimelineView() {
		loadTweets(0);
	}

	private void setUpScrolling() {
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				loadTweets(tweets.get(tweets.size() - 1).getId() - 1);
			}
		});
	}
	
	public void showProfile(View v){
		Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
	}
	
	public abstract void loadTweets(long maxId);

}
