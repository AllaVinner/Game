package main;

public class EnemyShip {

	
	private double x;
	private double y;
	private double r;
	private boolean alive;
	
	public EnemyShip(double x){
		this.x = x;
		this.y = 50;
		this.r = 50;
		alive = true;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getR(){
		return r;
	}
	
	public void move(double dir){
		switch((int) dir){
		case (int) EnemyCruse.LEFT :
			x -= 2;
			break;
		case (int) EnemyCruse.RIGHT :
			x += 2;
			break;
		}
	}
	
	public void down(){
		y += 25;
	}
	
	public void hit(){
		r-=10;
		if(r <= 9){
			alive =false;
		}
	}
	
	public void reset(double x){
		this.x = x;
		this.y = 50;
		this.r = 50;
		alive =true;
	}
	
	public boolean getLive(){
		return alive;
	}
	
	
	
}
