package nl.saxion.network_services;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.saxion.network_services.view.TweetAdapter;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	
	private ListView listViewTweets;
	private TextView realName, userName, following, followers, tweetCount;
	private MyApplication app;
	private Model model;
	private TweetAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);
		
		app = (MyApplication) getBaseContext().getApplicationContext();
		model = app.getModel();
		
		listViewTweets = (ListView) findViewById(R.id.listViewTweets);
		realName = (TextView) findViewById(R.id.realName);
		userName = (TextView) findViewById(R.id.userName);
		following= (TextView) findViewById(R.id.following);
		followers = (TextView) findViewById(R.id.followers);
		tweetCount = (TextView) findViewById(R.id.tweetCount);		
		userName.setTextColor(Color.GRAY);
		userName.setText("@Casper_vdHoek");
		
		UserTimelineTask utt = new UserTimelineTask(model, this, listViewTweets, adapter);
		utt.execute();
		
	}
	
	

}
