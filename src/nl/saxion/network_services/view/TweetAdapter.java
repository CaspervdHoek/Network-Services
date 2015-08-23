package nl.saxion.network_services.view;

import java.util.ArrayList;

import nl.saxion.network_services.FavoriteTask;
import nl.saxion.network_services.MainActivity;
import nl.saxion.network_services.Model;
import nl.saxion.network_services.MyApplication;
import nl.saxion.network_services.ProfileActivity;
import nl.saxion.network_services.ProfilePhotoTask;
import nl.saxion.network_services.R;
import nl.saxion.network_services.RetweetTask;
import nl.saxion.network_services.Tweet;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends ArrayAdapter<Tweet> {
	
	private LayoutInflater inflater;
	private int resource;
	MyApplication app;
	Model model;
	
	public TweetAdapter(Context context, int resource, ArrayList<Tweet> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
		
		
		app = (MyApplication) context.getApplicationContext();
		model = app.getModel();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView screenName = (TextView) convertView.findViewById(R.id.screenName);
		TextView name = (TextView) convertView.findViewById(R.id.realName);
		TextView tweetText = (TextView) convertView.findViewById(R.id.tweetText);
		ImageView foto = (ImageView) convertView.findViewById(R.id.foto);
		Button retweetButton = (Button) convertView.findViewById(R.id.retweetButton);
		Button favoriteButton = (Button) convertView.findViewById(R.id.favoriteButton);
		
		Tweet tweet = getItem(position);
		ProfilePhotoTask ppt = new ProfilePhotoTask(foto);
		final String tweetID = tweet.getID();
				
		tweetText.setText(tweet.getText());
		name.setText(tweet.getUser().getName());
		screenName.setText("@" + tweet.getUser().getScreenName());
		screenName.setTextColor(Color.GRAY);
		if (tweet.getUser().getProfileImageURL() != null){
			ppt.execute(tweet.getUser().getProfileImageURL());
		}
		
		retweetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RetweetTask rt = new RetweetTask(model);
				rt.execute(tweetID);
				Log.d("Retweet", "nice");
			}
		});
		
		favoriteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FavoriteTask ft = new FavoriteTask(model);
				ft.execute(tweetID);
				Log.d("Favorite", "Internet famous!");
			}
		});
		
		return convertView;
	}


}
