package com.codepath.apps.basictwitter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetActivity extends Activity {
	private TextView tvUserNameC, tvNameC;
	private ImageView ivProfileImageC;
	private EditText etTweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);
		initializeVariables();
	}

	private void initializeVariables() {
//		Tweet tweet = new Tweet();
		tvUserNameC = (TextView) findViewById(R.id.tvUserNameC);
		tvNameC = (TextView) findViewById(R.id.tvNameC);
		etTweet = (EditText) findViewById(R.id.etTweet);
		ImageLoader imageLoader = ImageLoader.getInstance();
		
//		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImageC);
 	}
}
