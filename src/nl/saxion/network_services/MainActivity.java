package nl.saxion.network_services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import nl.saxion.network_services.view.TweetAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ImageButton searchButton;
	private Button profileButton;
	private EditText searchEditText;
	private ListView list;
	private TweetAdapter adapter;
	private MyApplication app;
	private Model model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		app = (MyApplication) getBaseContext().getApplicationContext();
		model = app.getModel();
		
		list = (ListView) findViewById(R.id.listViewTweets);
		
		ArrayList<Tweet> tweetArrayList = new ArrayList<Tweet>();
		adapter = new TweetAdapter(this, R.layout.tweet, tweetArrayList);
		
		list.setAdapter(adapter);
		searchEditText = (EditText) findViewById(R.id.searchEditText);
		searchButton = (ImageButton) findViewById(R.id.searchButton);
		profileButton = (Button) findViewById(R.id.profileButton);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SearchTweetTask stt = new SearchTweetTask(MainActivity.this, list, adapter);
				stt.execute(searchEditText.getText() + "");
				Log.d("tekst", searchEditText.getText() + "");
				
			}
		});
		
		profileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ProfileActivity.class);
				startActivity(i);
			}
		});
		
		HomeTimelineTask htt = new HomeTimelineTask(model, this, list, adapter);
		htt.execute();
		
		// Code voor mijlpaal 1, voor het inlezen van een JSON bestand
//		try {
//			JSONObject tweets = new JSONObject(readAssetIntoString("searchresult.json.txt"));
//			JSONArray statuses = tweets.getJSONArray("statuses");
//			
//			for(int i = 0; i < statuses.length(); i++){
//				JSONObject tweet = statuses.getJSONObject(i);
//				Tweet newTweet = new Tweet(tweet);
//				tweetArrayList.add(newTweet);
//			}
//
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
		
	}


	/**
     * Reads an asset file and returns a string with the full contents.
     *
     * @param filename  The filename of the file to read.
     * @return          The contents of the file.
     * @throws IOException  If file could not be found or not read.
     */
    private String readAssetIntoString(String filename) throws IOException {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
			InputStream is = getAssets().open(filename, AssetManager.ACCESS_BUFFER);
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
            throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();		
	}	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
