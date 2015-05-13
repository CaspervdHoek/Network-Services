package nl.saxion.network_services;

public class URL extends Entity{
	
	private String displayURL;
	
	public URL(int begin, int end, String displayURL){
		super(begin, end);
		this.displayURL = displayURL;
	}

}
