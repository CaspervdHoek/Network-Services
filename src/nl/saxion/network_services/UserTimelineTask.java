package nl.saxion.network_services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.saxion.network_services.view.TweetAdapter;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class UserTimelineTask extends AsyncTask<String, Void, String> {
	
	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private ArrayList<Tweet> tweetArrayList = new ArrayList<Tweet>();
	private ProfileActivity activity;
	private ListView list;
	private TweetAdapter adapter;
	
	public UserTimelineTask(Model model, ProfileActivity activity, ListView list, TweetAdapter adapter){
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
		this.activity = activity;
		this.list = list;
		this.adapter = adapter;
	}

	/**
	 * Haalt de tijlijn van de ingelogde gebruiker op
	 */
	@Override
	protected String doInBackground(String... params) {
		HttpClient client = new DefaultHttpClient();
		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpGet get = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json");
		String json =  null;
		
		try {
			consumer.sign(get);
			json = client.execute(get, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
		Log.d("json", json);
		return json;
	}
	
	@Override
	protected void onPostExecute(String result) {
				
		try {
			JSONArray tweets = new JSONArray(result);
			JSONObject userTweetJSON = tweets.getJSONObject(0);
			Tweet userTweet = new Tweet(userTweetJSON);
			
			activity.setProfileInfo(userTweet.getUser().getName(), userTweet.getUser().getScreenName()
					, userTweet.getUser().getFollowers(), userTweet.getUser().getFriendsCount(), userTweet.getUser().getTweetCount(), userTweet.getUser().getProfileImageURL());

			
			for(int i = 0; i < tweets.length(); i++){
				JSONObject tweet = tweets.getJSONObject(i);
				Tweet newTweet = new Tweet(tweet);
				tweetArrayList.add(newTweet);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		adapter = new TweetAdapter(activity, R.layout.tweet, tweetArrayList);
		list.setAdapter(adapter);
		
		super.onPostExecute(result);
	}

}
