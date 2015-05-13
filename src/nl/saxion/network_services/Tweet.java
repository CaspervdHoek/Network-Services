package nl.saxion.network_services;

import java.util.ArrayList;

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
		//this.text = obj.getString("text");
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
