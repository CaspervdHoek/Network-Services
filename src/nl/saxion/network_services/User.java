package nl.saxion.network_services;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

public class User {

	private String id;
	private String description;
	private int followersCount;
	private int friendsCount;
	private int listedCount;
	private String location;
	private String name;
	private String profileBackgroundColor;
	private String profileBackgroundImageURL;
	private String profileBannerURL;
	private String profileImageURL;
	private boolean profileUseBackgroundImage;
	private boolean accountProtected;
	private String screenName;
	private Tweet status;
	
	public User(JSONObject user){
		
		try {
			this.id = user.getString("id_str");
			this.description = user.getString("description");
			this.followersCount = user.getInt("followers_count");
			this.friendsCount = user.getInt("friends_count");
			this.listedCount = user.getInt("listed_count");
			this.location = user.getString("location");
			this.name = user.getString("name");
			this.screenName = user.getString("screen_name");
			if(user.has("profile_background_color")){
				this.profileBackgroundColor = user.getString("profile_background_color");
			}
//			this.profileBackgroundImageURL = user.getString("profile_background_image_url");
//			this.profileBannerURL = user.getString("profile_banner_url");
			if(user.has("profile_image_url")){
				this.profileImageURL = user.getString("profile_image_url");
			}
			this.profileUseBackgroundImage = user.getBoolean("profile_use_background_image");
			this.accountProtected = user.getBoolean("protected");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getScreenName(){
		return screenName;
	}
	
	public String getProfileImageURL(){
		return profileImageURL;
	}
}
