package main;

public class EnemyShip {

	
	private double x;
	private double y;
	private double r;
	
	public EnemyShip(double x){
		this.x = x;
		this.y = 50;
		this.r = 50;
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
	}
	
}
