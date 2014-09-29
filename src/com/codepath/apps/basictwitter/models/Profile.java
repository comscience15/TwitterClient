package com.codepath.apps.basictwitter.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile {
	private String name;
	private long id;
	private String profileImageUrl;
	private User user;
	
	// create a method extract above values from JSON -- a single tweet
		public static Profile fromJSON(JSONObject jsonObject){
			Profile profile = new Profile();
			// extract values from the json to populate the member variables
			try{
				profile.id = jsonObject.getLong("id");
				profile.name = jsonObject.getString("name");
				profile.profileImageUrl = jsonObject.getString("profile_image_url");
				profile.user = User.fromJSON(jsonObject.getJSONObject("user"));
			}catch (JSONException e){
				e.printStackTrace();
				return null;
			}
			
			return profile;
		}

		public static ArrayList<Profile> fromJSONArray(JSONArray jsonArray) {
			ArrayList<Profile> profiles = new ArrayList<Profile>(jsonArray.length());
			
			for (int i = 0; i < jsonArray.length(); i++){
				JSONObject profileJson = null;
				try {
					profileJson = jsonArray.getJSONObject(i);
				} catch(Exception e){
					e.printStackTrace();
					continue;
				}
				Profile profile = Profile.fromJSON(profileJson);
				if (profile != null){
					profiles.add(profile);
				}
			}
			return profiles;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		public long getUid() {
			return id;
		}
		
		public User getUser() {
			return user;
		}
		
		public String getProfileImageUrl() {
			return profileImageUrl;
		}
}
