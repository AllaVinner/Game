package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game  {
	
	private JFrame board;
	private Dimension size;
	private Dimension cell;
	
	private Snake snake;
	private Food food;
	
	public static void main(String[] args) {
		new Game();
		
	}
	
	public Game(){
		
		// Create Board
		board = new JFrame("Snake");
		size = new Dimension(20, 20);
		cell = new Dimension(15, 15);
		snake = new Snake(size.width, size.height);
		
		
		//Listener
		ListenForKeys lKeys = new ListenForKeys();
		board.addKeyListener(lKeys);
		
		//food
		food = new Food(size.width, size.height);
		
		board.setSize(size.width*cell.width+20, size.height*cell.height+40);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.add(new Board(),BorderLayout.CENTER);
		board.setVisible(true);

	}
	
	public void nextLap(){
		try {
			Thread.sleep(70);
		} catch (InterruptedException e) {
		}
		
		if(snake.death()){
			snake.reset();
		}
		if(snake.getX() == food.getX() && snake.getY() == food.getY()){
			food.eaten();
//			snake.eat();
			snake.move(true);
		}else{
			snake.move(false);
		}
		board.repaint();
		
	}
	
	
	
	private class	Board extends JComponent{
		public void paint(Graphics g){
			Graphics2D graph2 = (Graphics2D)g;
			graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			
			//Draw backGround
			graph2.setPaint(Color.GREEN);
			Shape backGround = new Rectangle(0, 0, size.width*cell.width, size.height*cell.height);
			graph2.fill(backGround);
			
			// Draw Food
			graph2.setPaint(Color.RED);
			Shape dFood = new Rectangle(food.getX()*cell.width, food.getY()*cell.height, cell.width, cell.height);
			graph2.fill(dFood);
			
			//Draw Head Snake
			graph2.setPaint(Color.BLACK);
			Shape head = new Rectangle(snake.getX()*cell.width, snake.getY()*cell.height, cell.width, cell.height);
			graph2.fill(head);
			
			//Draw Tail
			graph2.setPaint(Color.GRAY);
			for(int i=0; i < snake.getTail().size(); i++){
				Shape tail = new Rectangle(snake.getTail().get(i).getPosX()*cell.width, snake.getTail().get(i).getPosY()*cell.height, cell.width, cell.height);
				graph2.fill(tail);
			}
			
			
			
			
		}
	}
	
	private class ListenForKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent k) {
			switch(k.getKeyCode()){
			case KeyEvent.VK_RIGHT :
				snake.setDir(Snake.RIGHT);
				break;	
			case KeyEvent.VK_DOWN :
				snake.setDir(Snake.DOWN);
				break;	
			case KeyEvent.VK_LEFT :
				snake.setDir(Snake.LEFT);
				break;	
			case KeyEvent.VK_UP :
				snake.setDir(Snake.UP);
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
	} // end class ListenForKeys

}// end class Game