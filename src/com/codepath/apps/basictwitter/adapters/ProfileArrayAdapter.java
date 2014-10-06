package com.codepath.apps.basictwitter.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.basictwitter.R;
import com.codepath.apps.basictwitter.R.id;
import com.codepath.apps.basictwitter.R.layout;
import com.codepath.apps.basictwitter.models.Profile;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileArrayAdapter extends ArrayAdapter<Profile> {
	public ProfileArrayAdapter(Context context, List<Profile> profile) {
		super(context, 0, profile);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Profile profile = getItem(position);
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.activity_tweet, parent, false);
		} else {
			v = convertView;
		}
		
		ImageView ivProfileImageC = (ImageView) v.findViewById(R.id.ivProfileImageC);
		TextView tvUserNameC = (TextView) v.findViewById(R.id.tvUserNameC);
		TextView tvNameC = (TextView) v.findViewById(R.id.tvNameC);
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		imageLoader.displayImage(profile.getProfileImageUrl(), ivProfileImageC);
		tvUserNameC.setText(profile.getUser().getScreenName());
		tvNameC.setText("@" + profile.getUser().getName());
		return v;
	}
}
