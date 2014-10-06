package com.codepath.apps.basictwitter.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.R.color;
import com.codepath.apps.basictwitter.R.id;
import com.codepath.apps.basictwitter.R.layout;
import com.codepath.apps.basictwitter.UserProfileActivity;
import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	// use getView is how data item is get translated into actual view display on listview
	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for position
		final Tweet tweet = getItem(position);
		// Find an inflate the template
		View v;
		if (convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.tweet_items, parent, false);
		} else {
			v = convertView;
		}
		// Find the views within template
		ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
		TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
		TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		TextView tvName = (TextView) v.findViewById(R.id.tvName);
		TextView tvTimeStamp = (TextView) v.findViewById(R.id.tvTimeStamp);
		
		// Populate views with tweet data
		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
		tvUserName.setText(tweet.getUser().getName());
		tvBody.setText(tweet.getBody());
		
		tvName.setText(tweet.getUser().getScreenName());
		tvName.setTextColor(R.color.Gray);
		tvTimeStamp.setText(setToDays(tweet.getCreatedAt()));
		tvTimeStamp.setTextColor(R.color.Gray);
		
		ivProfileImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), UserProfileActivity.class);
				i.putExtra("screen_name", tweet.getUser().getScreenName());
				getContext().startActivity(i);
			}
		});
		return v;
	}

	private String setToDays(String postedTime) {
		String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
		SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
		sf.setLenient(true);
		
		String relativeDate = "";
		long dateMillis = 0;
		try {
			dateMillis = sf.parse(postedTime).getTime();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
		String relativeTimeInt = relativeDate.substring(0,relativeDate.indexOf(" "));
		String relativeTimeStr = relativeDate.substring(relativeDate.indexOf(" ") + 1, relativeDate.indexOf(" ") + 2);

		return relativeTimeInt + relativeTimeStr;
	}
	
	
}
