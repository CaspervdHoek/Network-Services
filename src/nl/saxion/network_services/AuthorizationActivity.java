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

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("http://9gag.com")) {
					String[] splitUrl = url.split("oauth_verifier=");
					att.execute(splitUrl[1]);
					startActivity(i);
					return true;
				}
				return false;
			}
		});
		}

}
