package nl.saxion.network_services;

public class Entity {
	
	protected int[] indices = new int[2];
	
	public Entity(int begin, int end){
		indices[0] = begin;
		indices[1] = end;
	}

}
