package main;

import java.util.ArrayList;

public class Ship {
	 
	
	private double x;
	private ArrayList<Shot> mag;
	
	public Ship(){
		x = 100;
		mag = new ArrayList<>();
	}
	
	public void newShot(){
		mag.add(new Shot(this.x));
	}
	
	public void nextLap(){	
		for(int i=0; i < mag.size(); i++){
			mag.get(i).next();
			if(mag.get(i).getY() < 0){
				mag.remove(mag.get(i));
			}
		}
		
		
	}
	
	public ArrayList<Shot> getMag(){
		return this.mag;
	}
	
	public double getX(){
		return x;
	}
	
	public void leftX(double i){
		this.x -= i;
	}
	
	public void rightX(double i){
		this.x += i;
	}

}
