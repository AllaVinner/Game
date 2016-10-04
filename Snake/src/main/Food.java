package main;

public class Food {

	private int posX;
	private int posY;
	private int width;
	private int height;
	
	
	public Food(int width, int height){
		
		this.posX =(int) ( Math.random()*(double) width);
		this.posY =(int) ( Math.random()*(double) height);
		this.width = width;
		this.height = height;
	}
	
	public int getX(){
		return this.posX;
	}
	
	
	public int getY(){
		return this.posY;
	}
	
	public void eaten(){
		this.posX =(int) ( Math.random()*(double) width);
		this.posY =(int) ( Math.random()*(double) height);
	}
}
