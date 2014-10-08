package com.codepath.apps.basictwitter.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import static com.codepath.apps.basictwitter.variables.Constants.DATE_FORMAT;

public class Tweet extends BaseModel{
	private static final long serialVersionUID = 1L;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
//	private long id;
//	private String body, createdAt, handle, imageUrl, name;
	private User user;
	
	
	// create a method extract above values from JSON -- a single tweet
	public User getUser() {
		return user;
	}

	public String getBody(){
		return getString("text");
	}

	public long getId(){
		return getLong("id");
	}

	public boolean isFavorited(){
		return getBoolean("favorited");
	}

	public boolean isRetweeted(){
		return getBoolean("retweeted");
	}
	
	public Date getCreatedAt(){
		try{
            return DATE_FORMAT.parse(getString("created_at"));
        } catch (ParseException e) { 
        	e.printStackTrace();
        	return null;
        }
		 
	}

	public static Tweet fromJSON(JSONObject jo){
		Tweet tweet = new Tweet();
		try{
			tweet.jsonObject = jo;
			tweet.user = User.fromJSON(jo.getJSONObject("user"));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public static ArrayList<Tweet> fromJSON(JSONArray ja){
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(ja.length());

		for(int i =0 ; i < ja.length(); i++){
			JSONObject jo = null;
			try{
				jo = ja.getJSONObject(i);
			}catch(JSONException e){
				e.printStackTrace();
				continue;
			}
			Tweet tweet = Tweet.fromJSON(jo);
			if(tweet != null){
				tweets.add(tweet);
			}
		}
		return tweets;

	}
}
