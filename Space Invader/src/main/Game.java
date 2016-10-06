package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		Game my = new Game();
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			my.nextLap();
			my.board.repaint();
			
			//System.out.println(my.ship.getMag().size());
		}

	}
	
	private JFrame board;
	private Ship ship;
	private EnemyCruse cruse;
	
	public Game(){
		this.board = new JFrame("Space Invader");
		ship = new Ship();
		cruse = new EnemyCruse();
		
		this.board.setSize(600, 600);
		this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.board.add(new Draw());
		
		ListenForKeys lKeys = new ListenForKeys();
		this.board.addKeyListener(lKeys);
		
		this.board.setVisible(true);

	}
	
	public void nextLap(){
		int counter =0;
		ship.nextLap();
		cruse.next();
		
		for (Shot s : ship.getMag()) {
			for(EnemyShip e : cruse.getArray()){
				if(getDistance(s,e) < e.getR()){
					s.hit();
					e.hit();
				}
			}
		}
		
	}
	
	public class Draw extends JComponent{
		public void paint(Graphics g){
			
			// set background
			g.setColor(Color.CYAN);
			g.fillRect(0, 0, 600, 600);
			
			// set Space Ship
			g.setColor(Color.RED);
			g.fillRect( (int) ship.getX(), 550, 10, 80);
			
			// Shots
			g.setColor(Color.BLACK);
			for (Shot s : ship.getMag()) {
				g.fillRect( (int) s.getX(), (int) s.getY(), 7, 7);
			}
			
			// cruse
			g.setColor(Color.GREEN);
			for (EnemyShip c : cruse.getArray()) {
				g.fillOval( (int) c.getX(), (int) c.getY(), (int) (2*c.getR()), (int) (2*c.getR()));
			}
					
			
		}
	}
	
	public class ListenForKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				ship.leftX(10);
				break;
			case KeyEvent.VK_RIGHT:
				ship.rightX(10);
				break;
			case KeyEvent.VK_SPACE:
				ship.newShot();
				break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public double getDistance(Shot s, EnemyShip e){
		double ax = s.getX();
		double ay = s.getY();
		double bx = e.getX() +e.getR();
		double by = e.getY() +e.getR();
		
		return Math.pow( Math.pow(ax-bx, 2) + Math.pow(ay-by, 2), 0.5);
		
	}

}
