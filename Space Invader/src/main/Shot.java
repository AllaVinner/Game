package main;

public class Shot {

	private double x;
	private double y;
	
	public Shot(double x){
		this.x = x;
		this.y = 550;
		
	}
	
	public void hit(){
		y = -10;
	}
	
	public void next(){
		this.y -= 10; 
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

	
	

}
