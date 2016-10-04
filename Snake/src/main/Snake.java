package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Snake {
	
	private int posX;
	private int posY;
	private int dir;
	private ArrayList<Pos> tail;
	private int sizeX;
	private int sizeY;
	
	public static final int RIGHT =0;
	public static final int DOWN = 1; 
	public static final int LEFT = 2; 
	public static final int UP = 3;
	
	
	public Snake(int sizeX, int sizeY){
		
		this.posX = 0;
		this.posY = 0;
		this.dir=RIGHT;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		this.tail = new ArrayList<>();
		 
		
		
	}
	
	public void move(boolean eaten){
		Pos link = new Pos();
		this.tail.add(0, link);
	
		
		switch(this.dir){
		case RIGHT :
			this.posX ++;
			break;
		case DOWN :
			this.posY ++;
			break;
		case LEFT :
			this.posX --;
			break;
		case UP :
			this.posY --;
			break;
		}
		
		this.posX = modulo(this.posX, this.sizeX);
		this.posY = modulo(this.posY, this.sizeY);
		
		
		
		if(! eaten){
			this.tail.remove(tail.size()-1);
		}		
	}
	
	
	
	public int getX(){
		return this.posX;
	}

	public int getY(){
		return this.posY;
	}
	
	public ArrayList<Pos> getTail(){
		return this.tail;
	}
	
	public void setDir(int dir){
		this.dir = dir;
	}
	
	public void eat(){
		Pos link = new Pos();
		this.tail.add(0, link);
	}
	
	public boolean death(){
		for(int i=0; i< tail.size(); i++){
			if(tail.get(i).getPosX() == this.posX && tail.get(i).getPosY() == this.posY){
				return true;
			}
		}
		return false;
	}
	
	public void reset(){
		tail.removeAll(getTail());
	}
	
	
	public class Pos{
		int X;
		int Y;
		public Pos(){
			this.X = posX;
			this.Y = posY;
			
		}
		
		public int getPosX(){
			return this.X;
		}
		
		public int getPosY(){
			return this.Y;
		}
	}
	
	public static int modulo(int value, int modulo){
		if(value >= 0){
			return value%modulo;
		}else{
			return value%modulo+modulo;
		}
	}
	
	
}
