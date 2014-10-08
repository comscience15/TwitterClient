package com.codepath.apps.basictwitter;

import java.security.InvalidAlgorithmParameterException;

import org.json.JSONObject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.fragments.UserFragment;
import com.codepath.apps.basictwitter.models.User;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import static com.codepath.apps.basictwitter.variables.Constants.SCREEN_NAME;

public class UserProfileActivity extends FragmentActivity {

	TextView tvName;
	TextView tvTagline;
	TextView tvFollowers;
	TextView tvFollowing;
	ImageView ivProfileImage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		ActionBar actionBar = getActionBar();
		String screenName = getIntent().getStringExtra("screen_name");
		actionBar.setTitle("@" + screenName);
		
		setUpViews();
		loadProfileData(screenName);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		UserFragment userTimelineFragment = UserFragment.newInstance(screenName);
		ft.replace(R.id.frTimeline, userTimelineFragment);
		ft.commit();
	}
	
	private void setUpViews() {
		tvName = (TextView)findViewById(R.id.tvName);
		tvTagline = (TextView)findViewById(R.id.tvTagline);
		tvFollowers = (TextView)findViewById(R.id.tvFollowers);
		tvFollowing = (TextView)findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView)findViewById(R.id.ivProfileImage);
	}

	private void loadProfileData(String screenName){
		TwitterApplication.getRestClient().getUserInfo(screenName, new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject json) {
						User user = User.fromJSON(json);
						tvName.setText(user.getName());
						tvTagline.setText(user.getTagline());
						tvFollowers.setText(user.getFollowersCount()+ " Followers");
						tvFollowing.setText(user.getFriendsCount() + " Following");
						ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
					}

					@Override
					public void onFailure(Throwable t) {
						t.printStackTrace();
						Log.d("DEBUG", "ERROR-" + t.getMessage());
					}
				});
	}
}
