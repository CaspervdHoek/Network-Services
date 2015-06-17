package nl.saxion.network_services.view;

import java.util.ArrayList;

import nl.saxion.network_services.R;
import nl.saxion.network_services.Tweet;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetAdapter extends ArrayAdapter<Tweet> {
	
	private LayoutInflater inflater;
	private int resource;

	public TweetAdapter(Context context, int resource, ArrayList<Tweet> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;		
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
		
		Tweet tweet = getItem(position);
		
		tweetText.setText(tweet.getText());
		name.setText(tweet.getUser().getName());
		screenName.setText("@" + tweet.getUser().getScreenName());
		screenName.setTextColor(Color.GRAY);
		if (tweet.getUser().getProfileImageURL() != null){
			foto.setImageURI(Uri.parse(tweet.getUser().getProfileImageURL()));
		}
		
		return convertView;
	}


}
