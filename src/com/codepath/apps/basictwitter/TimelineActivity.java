package com.codepath.apps.basictwitter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.basictwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.basictwitter.fragments.MentionsTimelineFragement;
import com.codepath.apps.basictwitter.listeners.FragmentTabsListener;
import com.codepath.apps.basictwitter.models.Tweet;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends FragmentActivity {
////	PullToRefreshListView ptlTimeline;
//	ListView ptlTimeline;
//	private TweetArrayAdapter tweetAdapter;
//	private TwitterClient client;
//	private ArrayList<Tweet> tweets;
////	private Context context = this;
	private int TWEET_REQUEST_CODE = 123;
	private String screenName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_timeline);
//		setUserNameActionBar();
		setupTabs();
		
//		ptlTimeline = (PullToRefreshListView) findViewById(R.id.ivTweets);
//		ptlTimeline = (ListView) findViewById(R.id.lvTweets);
//		setListView();
//			Tweet.deleteAll();
//			populateTimeline(1,0);	
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab home = actionBar
				.newTab()
				.setText("Home")
				// .setIcon(R.drawable.ic_home)
				.setTag("HomeTimelineFragment")
				.setTabListener(
				new FragmentTabsListener<HomeTimelineFragment>(R.id.flContainer, this, "Home",
				HomeTimelineFragment.class));
		
		actionBar.addTab(home);
		actionBar.selectTab(home);

		Tab mention = actionBar
				.newTab()
				.setText("Mentions")
				// .setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
					new FragmentTabsListener<MentionsTimelineFragement>(R.id.flContainer, this, "Mentions",
				MentionsTimelineFragement.class));

		actionBar.addTab(mention);
	}

//	private void setUserNameActionBar() {
////		tweets = new ArrayList<Tweet>();
////		tweetAdapter = new TweetArrayAdapter(this, tweets);
////		client.getUserSetting(new JsonHttpResponseHandler() {
//		TwitterApplication.getRestClient().getUserSetting(new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(JSONObject json) {
//				try {
//					screenName = (String) json.get("screen_name");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				ActionBar ab = getActionBar();
//				ab.setTitle("@" + screenName);
//			}
//		});
//	}

//	private void setListView() {
//		tweets = new ArrayList<Tweet>();
//		tweetAdapter = new TweetArrayAdapter(this, tweets);
//		ptlTimeline.setAdapter(tweetAdapter);
//		
////		ptlTimeline.setOnRefreshListener(new OnRefreshListener() {
////
////			@Override
////			public void onRefresh() {
////					Tweet.deleteAll();
////					populateTimeline(1,0);
////			}
////		});
//		
//		//set endless scroll
//		ptlTimeline.setOnScrollListener(new OnScrollListener() {
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
//			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//				Log.i("DEBUG", "firstVisibleItem = " + firstVisibleItem);
//				Log.i("DEBUG", "visibleItemCount = " + visibleItemCount);
//				Log.i("DEBUG", "toalItemCount = " + totalItemCount);
//				if (totalItemCount < previousTotalItemCount) {
//					this.currentPage = this.startingPageIndex;
//					this.previousTotalItemCount = totalItemCount;
//					if (totalItemCount == 0) {
//						this.loading = true;
//					}
//				}
//				if (loading && (totalItemCount > previousTotalItemCount)) {
//					loading = false;
//					previousTotalItemCount = totalItemCount;
//					currentPage++;
//				}
//				int unseenItemCount = totalItemCount - visibleItemCount;
//				if (!loading
//						&& unseenItemCount <= (firstVisibleItem + visibleItemCount)) {
//					onLoadMore(currentPage + 1, totalItemCount);
//					loading = true;
//				}
//			}
//
//			public void onLoadMore(int page, int totalItemCount) {
//				Log.i("DEBUG", "Fetching page = " + page);
//				Log.i("DEBUG", "Fetching totalItemCount = " + totalItemCount);
//				Tweet last = (Tweet) ptlTimeline.getItemAtPosition(totalItemCount - 1);
//				if (last == null) {
//					populateTimeline(1, 0);
//				} else {
//					populateTimeline(page, last.getUId() - 1);
//				}
//			}
//		});
//	}
//
//	public void populateTimeline(int page, long l){
//		client = new TwitterClient(context);
//		if (page <= 1) {
//			showTimeline();
//		}else{
//			// send network request, get back json data
//			client.getHomeTimeLineMore(l, new JsonHttpResponseHandler() {
//				// from this case got a single object so use JSONObject from
//				// GET/home_timeline
//				@Override
//				public void onSuccess(int code, JSONArray json) {
//					JSONArray items = null;
//					items = json;
//					ArrayList<Tweet> tweets = Tweet.fromJSON(items);
//
//					for (Tweet tweet : tweets) {
//						tweetAdapter.add(tweet);
//						tweet.save();
//					}
//				}
//
//				@Override
//				public void onFailure(Throwable e, JSONArray s) {
//					Log.d("debug", e.toString());
//					Log.d("debug", s.toString());
//				}
//			});
//		}
//	}
//	
//	public void showTimeline() {
//		//send network request, get back json data
//		client.getHomeTimeLine(new JsonHttpResponseHandler() {
//			// from this case got a single object so use JSONObject from GET/home_timeline
//			@Override
//			public void onSuccess(JSONArray json) {
//				tweetAdapter.clear();
//				tweetAdapter.notifyDataSetInvalidated();
//				JSONArray items = null;
//				items = json;
//				ArrayList<Tweet> tweets = Tweet.fromJSON(items);
//				
//				for (Tweet tweet : tweets){
//					tweetAdapter.add(tweet);
//					tweet.save();
//				}
////				ptlTimeline.onRefreshComplete();
//			}
//
//			@Override
//			public void onFailure(Throwable e, JSONArray s) {
//				Log.d("debug", e.toString());
//				Log.d("debug", s.toString());
//			}
//		});
//	}

	public void onMiTweet(MenuItem mi) {
		if (mi.getItemId() == R.id.miTweet) {
			postTweet();
		}
	}
	
	private void postTweet() {
		// Create an intent
		Intent i = new Intent(TimelineActivity.this, TweetActivity.class);
		i.putExtra("screen_name", screenName);
		// Execute intent
		startActivityForResult(i, TWEET_REQUEST_CODE);
	}
	
	public void onShowUserProfile(MenuItem mi) {
		Intent i = new Intent(this, UserProfileActivity.class);
		i.putExtra("screenname", screenName);
		startActivity(i);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TWEET_REQUEST_CODE && resultCode == RESULT_OK) {
//			tweetAdapter.clear();
//			tweetAdapter.notifyDataSetInvalidated();
//			populateTimeline(1,0);
			String tweetMsg = data.getStringExtra("tweet");
			try {
				Tweet tweet = Tweet.fromJSON(new JSONObject(tweetMsg));
				HomeTimelineFragment homeTimeLineF = (HomeTimelineFragment) getSupportFragmentManager().findFragmentByTag("Home");
				homeTimeLineF.getAdapter().insert(tweet, 0);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compost, menu);
		return true;
	}
}
