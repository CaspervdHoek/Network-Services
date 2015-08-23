package nl.saxion.network_services;

import java.io.IOException;
import java.util.ArrayList;

import nl.saxion.network_services.view.FollowerAdapter;
import nl.saxion.network_services.view.TweetAdapter;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class FollowerListTask extends AsyncTask<String, Double, String> {
	
	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private ArrayList<User> followerArrayList = new ArrayList<User>();
	private ListView list;
	private FollowerAdapter adapter;
	private ProfileActivity activity;
		
	public FollowerListTask(Model model, ProfileActivity activity, ListView list, FollowerAdapter adapter){
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
		this.activity = activity;
		this.list = list;
		this.adapter = adapter;
	}
	
	@Override
	protected String doInBackground(String... params) {
		HttpClient client = new DefaultHttpClient();
		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpGet get = new HttpGet("https://api.twitter.com/1.1/followers/list.json?screen_name=" + model.getScreenname());
		String result = "Hoi ik ben eigenlijk leeg";
		
		try {
			consumer.sign(get);
			result = client.execute(get, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("follower", result);
		return result;
	}
	
	@Override
	protected void onPostExecute(String result) {
		Log.d("pre user make", "gaat nog goed");
		try {
			JSONArray followers = new JSONObject(result).getJSONArray("users");
			
			for(int i = 0; i < followers.length(); i++){
				JSONObject follower = followers.getJSONObject(i);
				User newFollower = new User(follower);
				followerArrayList.add(newFollower);
				Log.d("na toevoegen aan lijst", newFollower.getScreenName());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		adapter = new FollowerAdapter(activity, R.layout.tweet, followerArrayList);
		list.setAdapter(adapter);
		
		super.onPostExecute(result);
	}

}
