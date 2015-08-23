package nl.saxion.network_services;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.saxion.network_services.view.FollowerAdapter;
import nl.saxion.network_services.view.TweetAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	
	private ListView listViewTweets;
	private TextView realName, userName, following, followers, tweetCount;
	private Button homeButton, tweetButton, testButton;
	private EditText editTextTweet;
	private MyApplication app;
	private Model model;
	private TweetAdapter adapter;
	private FollowerAdapter followerAdapter;
	private ImageView profilePhoto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);
		
		app = (MyApplication) getBaseContext().getApplicationContext();
		model = app.getModel();
		
		homeButton = (Button) findViewById(R.id.homeButton);
		tweetButton = (Button) findViewById(R.id.tweetButton);
		//
		testButton = (Button) findViewById(R.id.retweetButton);
		//
		editTextTweet = (EditText) findViewById(R.id.editTextTweet);
		listViewTweets = (ListView) findViewById(R.id.listViewTweets);
		realName = (TextView) findViewById(R.id.realName);
		userName = (TextView) findViewById(R.id.userName);
		following= (TextView) findViewById(R.id.following);
		followers = (TextView) findViewById(R.id.followers);
		tweetCount = (TextView) findViewById(R.id.tweetCount);		
		userName.setTextColor(Color.GRAY);
		profilePhoto = (ImageView) findViewById(R.id.pfImageView);
		
		tweetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateTweet(editTextTweet.getText().toString());
				editTextTweet.setText("");			
			}
		});
		
		homeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ProfileActivity.this, MainActivity.class);
				startActivity(i);
			}
		});
		
		testButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				testFollowerGet();
			}
		});
		
		UserTimelineTask utt = new UserTimelineTask(model, this, listViewTweets, adapter);
		utt.execute();
				
	}
	
	public void testFollowerGet(){
		FollowerListTask getFollowers = new FollowerListTask(model, this, listViewTweets, followerAdapter);
		getFollowers.execute();
	}
	
	public void updateTweet(String tweet){
		UpdateTweetTask update = new UpdateTweetTask(model, this);
		
		update.execute(tweet);
		
	}
	
	public void setProfileInfo(String realName, String userName, String followers, String following, String tweetCount, String URL ){
		this.realName.setText(realName);
		this.userName.setText("@" + userName);
		this.followers.setText(followers + " followers");
		this.following.setText(following + " following");
		this.tweetCount.setText(tweetCount + " tweets");
		model.setScreenname(userName);
		
		ProfilePhotoTask ppt = new ProfilePhotoTask(profilePhoto);
		ppt.execute(URL);
		
	}
	
	

}
