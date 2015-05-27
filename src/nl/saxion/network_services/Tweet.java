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
			//JSONArray statuses = obj.getJSONArray("statuses");
			//JSONObject tweet = statuses.getJSONObject(0);
			
			this.id = tweet.getString("id");
			this.createdAt = tweet.getString("created_at");
<<<<<<< HEAD

			if( tweet.has("favorite_count"))
				this.favoriteCount = tweet.getInt("favorite_count");
			this.favorited = tweet.getBoolean("favourited");
=======
			this.favoriteCount = tweet.getInt("favorite_count");
			this.favorited = tweet.getBoolean("favorited");
>>>>>>> origin/master
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
	
	public String getText(){
		return text;
	}

}
