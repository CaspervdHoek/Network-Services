package nl.saxion.network_services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AuthorizationActivity extends Activity {

	private WebView webview;
	private Model model;
	private MyApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);

		app = (MyApplication) getBaseContext().getApplicationContext();
		
		model = app.getModel();
		webview = (WebView) findViewById(R.id.webView1);

		final WebviewTask wv = new WebviewTask(webview, model);
		wv.execute();
		final AccessTokenTask att = new AccessTokenTask(model);
		
		final Intent i = new Intent(this, ProfileActivity.class);

		webview.setWebViewClient(new WebViewClient() {

			/**
			 * Vangt op of de callback URL geladen wordt en stopt het laden zodra dit zo is
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.d("Override", url);
				if (url.startsWith("http://9gag.com")) {
					Log.d("Override", "voor split");
					String[] splitUrl = url.split("oauth_verifier=");
					Log.d("Override", "voor execute");
					att.execute(splitUrl[1]);
					Log.d("Override", "voor startactivity");
					startActivity(i);
					return true;
			
				}
				return false;
			}
		});

	}
}
