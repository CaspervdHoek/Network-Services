package nl.saxion.network_services;

public class Hashtag extends Entity{
	//#memes
	private String text;
	
	public Hashtag(int begin, int end, String text){
		super(begin, end);
		this.text = text;
	}

}
