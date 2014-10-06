package com.codepath.apps.basictwitter;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.models.User;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.codepath.apps.basictwitter.restcalls.TwitterClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

public class TweetActivity extends Activity {
	private TwitterClient client;
	private TextView tvUserNameC, tvNameC, tvCharCount;
	private ImageView ivProfileImageC;
	private EditText etTweet;
	
	private int MAX_CHARS = 140;
	private User user;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		initializeVariables();	
		getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);

	}

//	private void editTextListener() {
//		etTweet.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) {
//				setEditText();
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//	}

	private void setEditText() {
		int chk = MAX_CHARS - etTweet.getText().toString().length();
		String limit = chk + " characters left";
		tvCharCount.setText(limit);
	}

	private void initializeVariables() {
		tvUserNameC = (TextView) findViewById(R.id.tvUserNameC);
		tvNameC = (TextView) findViewById(R.id.tvNameC);
		tvCharCount = (TextView) findViewById(R.id.tvCharCount);
		etTweet = (EditText) findViewById(R.id.etTweet);
		ivProfileImageC = (ImageView) findViewById(R.id.ivProfileImageC);
		String sName = getIntent().getStringExtra("screen_name");
		tvUserNameC.setText("@" + sName);
		
		TwitterApplication.getRestClient().checkUserProfile(sName, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, JSONObject json) {
				User u = User.fromJSON(json);
				ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivProfileImageC);
			}
		});
		
		setEditText();
		
		etTweet.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				setEditText();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
 	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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

	private void postTweet(String status) {
		client = new TwitterClient(this);
		String reply = null;
//		reply = String.valueOf(getIntent().getLongExtra("id", 0));
		TwitterApplication.getRestClient().postTweet(status, reply, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				setResult(RESULT_OK);
				finish();
				super.onSuccess(json);
			}
			
			@Override
			public void onFailure(Throwable arg0, JSONObject error) {
				Toast.makeText(TweetActivity.this, "FAIL: " + arg0.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	public void showResult(String result) {
		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}
}
