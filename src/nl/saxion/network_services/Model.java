package nl.saxion.network_services;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import android.webkit.WebView;
/**
 * class die dingen bijhoud die universeel bereikbaar moeten zijn
 * @author Viradj
 *
 */
public class Model {
	
	private static final String OAUTH_REQUEST_URL = "https://api.twitter.com/oauth/request_token";
	private static final String OAUTH_ACCESSTOKEN_URL = "https://api.twitter.com/oauth/access_token";
	private static final String OAUT_AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	private static final String CONSUMER_KEY = "fmPbMIYHurHra0Np8qEhh9IS8";
	private static final String CONSUMER_SECRET = "8YcCA0V7dUwgzvsLWMTJfaX6xSMVdrREMjHKXEX0z6MBqz2j7e";
	private static final String OAUTH_CALLBACK_URL = "http://9gag.com";
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	
	public Model(){
		provider = new CommonsHttpOAuthProvider(OAUTH_REQUEST_URL, OAUTH_ACCESSTOKEN_URL, OAUT_AUTHORIZE_URL);
		consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	}
	
	public CommonsHttpOAuthProvider getProvider(){
		return provider;
	}
	
	public CommonsHttpOAuthConsumer getConsumer(){
		return consumer;
	}

}
