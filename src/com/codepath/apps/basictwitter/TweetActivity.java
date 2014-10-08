package com.codepath.apps.basictwitter;


import static com.codepath.apps.basictwitter.variables.Constants.SCREEN_NAME;
import static com.codepath.apps.basictwitter.variables.Constants.TWEET_KEY;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.models.User;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.codepath.apps.basictwitter.restcalls.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetActivity extends Activity {
	
	private EditText etTweet;
	private TextView tvUserNameC, tvNameC, tvCharCount;
	private ImageView ivProfileImageC;
	private String screenName, name;
	private int MAX_CHARS = 140;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);
		setUpViews();
		setActionBar();
		setEditText();
	}

	private void setEditText() {
		int chk = MAX_CHARS - etTweet.getText().toString().length();
		String limit = chk + " characters left";
		tvCharCount.setText(limit);
		
		editTextCount();
	}

	private void editTextCount() {
		etTweet.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				setEditText();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void setActionBar() {
		TwitterApplication.getRestClient().getAccounSettings(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonObj) {
				try {
					screenName = (String) jsonObj.get("screen_name");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				ActionBar actionBar = getActionBar();
				actionBar.setTitle("@" + screenName);
			}
		});
	}

	private void setUpViews() {
		etTweet = (EditText)findViewById(R.id.etTweet);
		tvUserNameC = (TextView) findViewById(R.id.tvUserNameC);
		tvNameC = (TextView) findViewById(R.id.tvNameC);
		tvCharCount = (TextView) findViewById(R.id.tvCharCount);
		ivProfileImageC = (ImageView)findViewById(R.id.ivProfileImageC);
		
		screenName = getIntent().getStringExtra("screen_name");
		tvUserNameC.setText("@"+screenName);
		name = getIntent().getStringExtra("name");
		tvNameC.setText(name);
		
		TwitterApplication.getRestClient().getUserInfo(screenName, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int arg0, JSONObject jsonObject) {
				User user = User.fromJSON(jsonObject);
				ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImageC);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			setResult(RESULT_OK);
			finish();
			return true;
		}else if (item.getItemId() == R.id.tweet) {
			tweet();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void tweet() {
		String status = etTweet.getText().toString();
		if ((status == null || status == "") && status.length() > 140) {
				Toast.makeText(this, "Please make sure status message is not longer than 140 characters", Toast.LENGTH_SHORT).show();
		} else {
			postTweet(status);
		}
	}

	public void postTweet(String status) {
		TwitterApplication.getRestClient().postTweet(status, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				Toast.makeText(getApplicationContext(), "Tweet Posted", Toast.LENGTH_SHORT).show();
				Intent data = new Intent();
				data.putExtra("tweet", json.toString());
				setResult(RESULT_OK, data);
				finish();
				super.onSuccess(json);
			}
			
			@Override
			public void onFailure(Throwable arg0, JSONObject error) {
				Toast.makeText(TweetActivity.this, "FAIL: " + arg0.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}
