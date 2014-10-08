package com.codepath.apps.basictwitter;

import static com.codepath.apps.basictwitter.variables.Constants.SCREEN_NAME;
import static com.codepath.apps.basictwitter.variables.Constants.TWEET_KEY;

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

	private String screenName, name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

		setupMyInfo();
		setupTabs();
	}

//    public void showProgressBar() {
//        setProgressBarIndeterminateVisibility(true); 
//    }
//
//    public void hideProgressBar() {
//        setProgressBarIndeterminateVisibility(false); 
//    }
    
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab home = actionBar.newTab()
				.setText("Home")
				// .setIcon(R.drawable.ic_home)
				.setTag("HomeTimelineFragment")
				.setTabListener(
						new FragmentTabsListener<HomeTimelineFragment>(R.id.flContainer, this, "Home",
								HomeTimelineFragment.class));

		Tab mention = actionBar.newTab()
				.setText("Mentions")
				// .setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimelineFragment")
				.setTabListener(
						new FragmentTabsListener<MentionsTimelineFragement>(R.id.flContainer, this, "Mentions",
								MentionsTimelineFragement.class));

		actionBar.addTab(home);
		actionBar.addTab(mention);
		actionBar.selectTab(home);
	}

	private void setupMyInfo() {
		TwitterApplication.getRestClient().getAccounSettings(
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject jsonObj) {
						try {
							screenName = (String) jsonObj.get("screen_name");
							name = (String) jsonObj.get("name");
						} catch (JSONException e) {
							e.printStackTrace();
						}
//						ActionBar actionBar = getActionBar();
//						actionBar.setTitle("@" + screenName);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compost, menu);
		return true;
	}
	
	public void onShowUserProfile(MenuItem mi) {
		// Create an intent
		Intent i = new Intent(TimelineActivity.this, UserProfileActivity.class);
		i.putExtra("screen_name", screenName);
//		i.putExtra("name", name);
		startActivity(i);
	}
	
	public void onMiTweet(MenuItem mi) {
		// Create an intent
		Intent i = new Intent(TimelineActivity.this, TweetActivity.class);
		i.putExtra("screen_name", screenName);
		i.putExtra("name", name);
		startActivityForResult(i, 200);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 200) {
			String tweetStr = data.getStringExtra("tweet");
			try {
				Tweet tweet = Tweet.fromJSON(new JSONObject(tweetStr));
				HomeTimelineFragment homeTimelineFragment = (HomeTimelineFragment)getSupportFragmentManager().findFragmentByTag("Home");
				homeTimelineFragment.getAdapter().insert(tweet, 0);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
