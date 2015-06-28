package nl.saxion.network_services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import nl.saxion.network_services.view.TweetAdapter;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import android.os.AsyncTask;
import android.widget.ListView;

public class UpdateTweetTask extends AsyncTask<String, Void, String> {
	
	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private ProfileActivity activity;
	
	HttpClient client = new DefaultHttpClient();
	ResponseHandler<String> handler = new BasicResponseHandler();
	
	public UpdateTweetTask(Model model, ProfileActivity activity){
		this.model = model;
		this.provider = model.getProvider();
		this.consumer = model.getConsumer();
	}
	
	/**
	 * Signed de post request en probeert een tweet te plaatsen. Mislukt als dezelfde tweet hiervoor al geplaatst is.
	 */
	@Override
	protected String doInBackground(String... params) {
		
		String encodedTweet = null;
		
		try {
			encodedTweet = URLEncoder.encode(params[0], "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + encodedTweet);
		
		String result = null;
		
		try {
			consumer.sign(request);
			result = client.execute(request, handler);
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
		
		return result;
	}

	
}
