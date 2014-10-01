package com.codepath.apps.basictwitter.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.query.Delete;

public class Tweet {
	private long id;
	private String body, createdAt, handle, imageUrl, name;
	private User user;
	
	
	// create a method extract above values from JSON -- a single tweet
	public static Tweet fromJSON(JSONObject jsonObject){
		Tweet tweet = new Tweet();
		// extract values from the json to populate the member variables
		try{
			tweet.name = jsonObject.getJSONObject("user").getString("name");
			tweet.handle = "@" + jsonObject.getJSONObject("user").getString("screen_name");
			tweet.body = jsonObject.getString("text");
			tweet.id = jsonObject.getLong("id");
			tweet.createdAt = jsonObject.getString("created_at");
			tweet.imageUrl = jsonObject.getJSONObject("user").getString("profile_image_url");
			tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
		}catch (JSONException e){
			e.printStackTrace();
			return null;
		}
		
		return tweet;
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
		
		for (int i = 0; i < jsonArray.length(); i++){
			JSONObject tweetJson = null;
			try {
				tweetJson = jsonArray.getJSONObject(i);
			} catch(Exception e){
				e.printStackTrace();
				continue;
			}
			Tweet tweet = Tweet.fromJSON(tweetJson);
			if (tweet != null){
				tweets.add(tweet);
			}
		}
		return tweets;
	}
	
	@Override
	public String toString() {
		return getBody() + "  " + getUser().getScreenName();
	}
	
	public String getBody() {
		return body;
	}

	public long getId() {
		return id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public User getUser() {
		return user;
	}
	
	public Tweet() {
		super();
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
