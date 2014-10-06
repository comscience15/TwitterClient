package com.codepath.apps.basictwitter.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet extends BaseModel{
	private static final long serialVersionUID = 1L;
//	private long id;
//	private String body, createdAt, handle, imageUrl, name;
	private User user;
	
	
	// create a method extract above values from JSON -- a single tweet
	public static Tweet fromJSON(JSONObject jsonObject){
		Tweet tweet = new Tweet();
		// extract values from the json to populate the member variables
		try{
			tweet.jsonObject = jsonObject;
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
	
	public String getBody(){
		return getString("text");
	}

	public long getUId() {
		return getLong("id");
	}

	public String getCreatedAt() {
		return getString("created_at");
	}

	public User getUser() {
		return user;
	}
	
//	public Tweet() {
//		super();
//	}
//	
//	public String getImageUrl() {
//		return imageUrl;
//	}
//	
//	public void setImageUrl(String imageUrl) {
//		this.imageUrl = imageUrl;
//	}
//	
//	public static List<Tweet> getAll() {
//		return new Select().from(Tweet.class).execute();
//	}
//
//	public static void deleteAll() {
//		new Delete().from(Tweet.class).execute();
//	}
}
