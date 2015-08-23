package nl.saxion.network_services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {

	private String id;
	private User user;
	private String createdAt;
	private ArrayList<Entity> entities;
	private int favoriteCount;
	private boolean favorited;
	private String inReplyToScreenName;
	private String inReplyToUserId;
	private int retweetCount;
	private boolean retweeted;
	private String text;
	private Media media;
	
	public Tweet(JSONObject tweet){
		
		try {

			this.id = tweet.getString("id");
			this.createdAt = tweet.getString("created_at");
			this.favoriteCount = tweet.getInt("favorite_count");
			this.favorited = tweet.getBoolean("favorited");
			this.inReplyToScreenName = tweet.getString("in_reply_to_screen_name");
			this.inReplyToUserId = tweet.getString("in_reply_to_status_id");
			this.retweetCount = tweet.getInt("retweet_count");
			this.retweeted = tweet.getBoolean("retweeted");
			this.text = tweet.getString("text");
			
			this.user = new User(tweet.getJSONObject("user"));
		
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getID(){
		return id;
	}
	
	public User getUser(){
		return user;
	}
	
	public String getText(){
		return text;
	}

}
