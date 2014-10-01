package com.codepath.apps.basictwitter.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

public class Tweet extends Model implements Serializable{
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

	public static ArrayList<Tweet> fromJSON(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = null;
			try {
				jsonObj = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
			Tweet tweet = Tweet.fromJSON(jsonObj);
			if (tweet != null) {
				tweets.add(tweet);
			}
		}
		return tweets;
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

	public long getUId() {
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
	
	public static List<Tweet> getAll() {
		return new Select().from(Tweet.class).execute();
	}

	public static void deleteAll() {
		new Delete().from(Tweet.class).execute();
	}
}
