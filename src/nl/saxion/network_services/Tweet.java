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
	
	public Tweet(JSONObject obj){
		
		try {
			JSONArray statuses = obj.getJSONArray("statuses");
			JSONObject tweet = statuses.getJSONObject(0);
			
			this.id = tweet.getString("id");
			this.createdAt = tweet.getString("created_at");
			this.favoriteCount = tweet.getInt("favourites_count");
			this.favorited = tweet.getBoolean("favourited");
			this.inReplyToScreenName = tweet.getString("in_reply_to_screen_name");
			this.inReplyToUserId = tweet.getString("in_reply_to_status_id");
			this.retweetCount = tweet.getInt("retweet_count");
			this.retweeted = tweet.getBoolean("retweeted");
			this.text = tweet.getString("text");
		
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Tweet(String id, User user, String createdAt, ArrayList<Entity> entities, int favoriteCount,
			boolean favorited, String inReplyToScreenName, String inReplyToUserId, int retweetCount,
			boolean retweeted, String text, Media media){
		
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
		this.entities = entities;
		this.favoriteCount = favoriteCount;
		this.favorited = favorited;
		this.inReplyToScreenName = inReplyToScreenName;
		this.inReplyToUserId = inReplyToUserId;
		this.retweetCount = retweetCount;
		this.retweeted = retweeted;
		this.text = text;
		this.media = media;
	}
}
