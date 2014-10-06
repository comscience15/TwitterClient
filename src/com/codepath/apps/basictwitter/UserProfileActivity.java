package com.codepath.apps.basictwitter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.models.User;
import com.codepath.apps.basictwitter.restcalls.TwitterApplication;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class UserProfileActivity extends FragmentActivity {
	private TextView tvName, tvTagline, tvFollowers, tvFollowing;
	private ImageView ivProfileImage;
	private String screenName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		setUserNameActionBar();
		
//		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		UserFragment userF = UserFragment.newInstance(screenName);
//		ft.replace(R.id.frTimeline, userF);
//		ft.commit();
	}
	// get header with twitter user name to Action bar
	private void setUserNameActionBar() {
		TwitterApplication.getRestClient().getUserSetting(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				try {
					screenName = (String) json.get("screen_name");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ActionBar ab = getActionBar();
				ab.setTitle("@" + screenName);
				initVariables(screenName);
			}
			
			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}
	
//	private void loadProfileInfo() {
//		TwitterApplication.getRestClient().verifyCredentails(new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(JSONObject json) {
//				User u = User.fromJSON(json);
////				getActionBar().setTitle("@" + u.getScreenName());
//				// make header user profile on UserProfile activity
//				populateProfileHeader(u);
//			}
//		});
//	}

//	private void populateProfileHeader(User user) {		
//		tvName.setText(user.getName());
//		tvTagline.setText(user.getTagline());
//		tvFollowers.setText(user.getFollowersCount() + " Followers");
//		tvFollowing.setText(user.getFriendsCount() + " Following");
//		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
//	}
	
	private void initVariables(String screenName) {
		tvName = (TextView) findViewById(R.id.tvName);
		tvTagline = (TextView) findViewById(R.id.tvTagline);
		tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);

		TwitterApplication.getRestClient().getTweetDetail(screenName, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject json) {
				User u = User.fromJSON(json);
				tvName.setText(u.getName());
				tvTagline.setText(u.getTagline());
				tvFollowers.setText(u.getFollowersCount() + " Followers");
				tvFollowing.setText(u.getFriendsCount() + " Following");
				ImageLoader.getInstance().displayImage(u.getProfileImageUrl(), ivProfileImage);
			}
			
			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
