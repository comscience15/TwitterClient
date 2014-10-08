package com.codepath.apps.basictwitter.adapters;

import java.util.Date;
import java.util.List;

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

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.UserProfileActivity;
import com.codepath.apps.basictwitter.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
	public TweetArrayAdapter(Context context, List<Tweet> tweets) {
		super(context, 0, tweets);
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v= inflater.inflate(R.layout.tweet_items, null);
		}
		
		final Tweet tweet = getItem(position);
		
//		ImageView ivProfileImage = (ImageView)view.findViewById(R.id.ivProfileImage);
//		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), ivProfileImage);
//		
//		TextView tvName = (TextView)view.findViewById(R.id.tvName);
//		String formattedName = "<b>" + tweet.getUser().getName() + "</b>"
//				+ "<small> <font color = '#777777'>@"
//				+ tweet.getUser().getScreenName() + "</font></small>";
//		tvName.setText(Html.fromHtml(formattedName));
//
//		TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
//		tvBody.setText(Html.fromHtml(tweet.getBody()));
//		
//		TextView tvTimeStamp = (TextView) view.findViewById(R.id.tvTimeStamp);
//		tvTimeStamp.setText(getDuration(tweet.getCreatedAt()));
		
		// Find the views within template
		ImageView ivProfileImage = (ImageView) v.findViewById(R.id.ivProfileImage);
		TextView tvUserName = (TextView) v.findViewById(R.id.tvUserName);
		TextView tvBody = (TextView) v.findViewById(R.id.tvBody);
		ImageLoader imageLoader = ImageLoader.getInstance();

		TextView tvName = (TextView) v.findViewById(R.id.tvName);
		TextView tvTimeStamp = (TextView) v.findViewById(R.id.tvTimeStamp);

		// Populate views with tweet data
		imageLoader.displayImage(tweet.getUser().getProfileImageUrl(),ivProfileImage);
		tvUserName.setText(tweet.getUser().getName());
		tvBody.setText(tweet.getBody());

		tvName.setText("@" + tweet.getUser().getScreenName());
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
	
	private String setToDays(Date createdAt) {
		if(createdAt == null){
			return "";
		}
		String date = (String) DateUtils.getRelativeDateTimeString(
                getContext(), createdAt.getTime(), DateUtils.MINUTE_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS, 0);
		String time = date.split(",")[0];
		time = time.replace(" ago", "")
				.replace(" minutes", "m")
				.replace(" minute", "m")
				.replace(" hours", "h")
				.replace(" hour", "h")
				.replace("yesterday", "1d")
				.replace(" days", "d");
		
		return time;
	}
}
