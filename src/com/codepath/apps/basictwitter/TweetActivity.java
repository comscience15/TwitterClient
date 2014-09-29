package com.codepath.apps.basictwitter;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.models.Profile;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetActivity extends Activity {
	private TwitterClient client;
//	private TextView tvUserNameC, tvNameC, tvCharCount;
//	private ImageView ivProfileImageC;
//	private EditText etTweet;
//	private Button btnTweet,btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);
		client = TwitterApplication.getRestClient(); // call getRestClient() from TwitterClient class
//		initializeVariables();
	}

	private void getUserInfo() {
//		final Profile profile = new Profile();
		client.postTweet(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray json) {
				Log.d("debug", json.toString());
//				tvUserNameC.setText(profile.toString());
//				Toast.makeText(getBaseContext(), "Username is " + profile.toString(), Toast.LENGTH_SHORT).show();
//				tvNameC.setText(profile.getUid());
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				System.out.println("FAILURE!!!!!!");
			}
		});
	}

//	private void initializeVariables() {
////		Tweet tweet = new Tweet();
//		tvUserNameC = (TextView) findViewById(R.id.tvUserNameC);
//		tvNameC = (TextView) findViewById(R.id.tvNameC);
//		etTweet = (EditText) findViewById(R.id.etTweet);
//		ImageLoader imageLoader = ImageLoader.getInstance();
//		btnTweet = (Button) findViewById(R.id.btnTweet);
//		btnCancel = (Button) findViewById(R.id.btnCancel);
//		etTweet.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				// TODO Auto-generated method stub
//				// Fires right as the text is being changed (even supplies the range of text)
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//				// TODO Auto-generated method stub
//				// Fires right before text is changing
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				// Fires right after the text has changed
//				tvCharCount.setText(s.toString());
//			}
//		});
//		
////		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImageC);
////		tvUserNameC.setText(tweet.getUser().getScreenName());
////		tvNameC.setText("@" + tweet.getUser().getName());
// 	}
	
	public void onTweet(View v) {
		getUserInfo();
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}
	
	public void onCancel(View v) {
//		etTweet.setText("");
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		tvCharCount = new TextView(this);
//		tvCharCount.setTextColor(getResources().getColor(R.color.Gray));
//		tvCharCount.setPadding(5, 0, 5, 0);
//		tvCharCount.setTextSize(8);
////		menu.add(0).setActionView(tvCharCount).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//		return true;
//	}
}
