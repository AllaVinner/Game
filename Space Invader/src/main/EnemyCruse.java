package main;

import java.util.ArrayList;

public class EnemyCruse {

	public static final double LEFT =-1;
	public static final double RIGHT =1;
	
	
	private ArrayList<EnemyShip> cruse;
	private double dir = LEFT;
	
	public EnemyCruse(){
		cruse = new ArrayList<>();
		for(int i=0; i< 5; i++){
			cruse.add(new EnemyShip(50+100*i));
		}
		
		
		
	}
	
	public ArrayList<EnemyShip> getArray(){
		return this.cruse;
	}
	
	public void next(){
		for (EnemyShip c : cruse) {
			c.move(dir);
			
		}
		
		for (EnemyShip c : cruse) {
			if(!(0 < c.getX() && c.getX() < 500)){
				dir = -1*dir;
				down();	
			}
	
		}
	}
	
	public void down(){
		for (EnemyShip c : cruse) {
			c.down();
		}
	}
	
}
