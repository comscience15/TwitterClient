package com.codepath.apps.basictwitter.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends BaseModel {
	private static final long serialVersionUID = 1L;
	private String name, screenName, description;
//	private long id;
//	private int status_count, followers, following;
//	private String profileImageUrl, profileBackgroundImageUrl;
//
//	// User.fromJSON(..)
//	public static User fromJSON(JSONObject json) {
//		User u = new User();
//		try {
//			u.name = json.getString("name");
//			u.id = json.getLong("id");
//			u.screenName = "@" + json.getString("screen_name");
//			u.profileImageUrl = json.getString("profile_image_url");
//			u.status_count = json.getInt("statuses_count");
//			u.followers = json.getInt("followers_count");
//			u.following = json.getInt("friends_count");
//			u.description = json.getString("description");
//			u.profileBackgroundImageUrl = json.getString("profile_background_image_url");
//		}catch (JSONException e){
//			e.printStackTrace();
//			return null;
//		}
//		return u;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public long getUid() {
//		return id;
////		return getLong("id");
//	}
//
//	public String getScreenName() {
//		return screenName;
//	}
//
//	public String getProfileImageUrl() {
//		return profileImageUrl;
//	}
//	
//	public String getProfileBackgroundImageUrl() {
//		return profileBackgroundImageUrl;
//	}
//	
//	public String getTagline() {
//		return description;
//	}
//	
//	public int getNumTweets() {
//		return status_count;
//	}
//	
//	public int getFollowersCount() {
//		return followers;
//	}
//	
//	public int getFriendsCount() {
//		return following;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public void setScreenName(String screenName) {
//		this.screenName = screenName;
//	}
//	
//	public void setProfileImageUrl(String profileImageUrl) {
//		this.profileImageUrl = profileImageUrl;
//	}

	public String getName(){
		return getString("name");
	}

	public long getId(){
		return getLong("id");
	}

	public String getScreenName(){
		return getString("screen_name");
	}

	public String getProfileImageUrl(){
		return getString("profile_image_url");
	}

	public String getProfileBackGroundImageUrl(){
		return getString("profile_background_image_url");
	}

	public int getNumTweets(){
		return getInt("statuses_count");
	}

	public int getFollowersCount(){
		return getInt("followers_count");
	}

	public int getFriendsCount(){
		return getInt("friends_count");
	}
	
	public String getTagline(){
		return getString("description");
	}

	public static User fromJSON(JSONObject jo) {
		User user = new User();
		try {
			user.jsonObject = jo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;

	}
}
