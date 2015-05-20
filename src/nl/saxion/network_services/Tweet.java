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

}
