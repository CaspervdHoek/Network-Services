package nl.saxion.network_services.view;

import java.util.ArrayList;

import nl.saxion.network_services.R;
import nl.saxion.network_services.Tweet;
import android.content.Context;
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
		Log.d("TweetAdapter", "GetView number " + position);
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView screenName = (TextView) convertView.findViewById(R.id.screenName);
		TextView userName = (TextView) convertView.findViewById(R.id.userName);
		TextView tweetText = (TextView) convertView.findViewById(R.id.tweetText);
		ImageView foto = (ImageView) convertView.findViewById(R.id.foto);
		
		Tweet tweet = getItem(position);
		
		return convertView;
	}


}
