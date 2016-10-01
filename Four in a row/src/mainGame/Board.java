package mainGame;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board {
	public static final int COLUMNFULL = -1;
	public static final int COLIMNNOTFULL = 0;
	
	private int mouseXClick;
	private int mouseYClick;
	
	private int [][] board;
	private int toWin;
	private Dimension cell;
	
	private JFrame boardFrame;
	private JPanel boardPanel;
	
	
	
	public Board(int numRow, int numCol, int toWin, Dimension cell){
		this.board = new int [numRow] [numCol];
		reset();

		this.toWin = toWin;
		
		this.cell = new Dimension();
		this.cell = cell;
		
		this.boardFrame = new JFrame();
		this.boardFrame.setSize(cell.width*numCol+15, cell.height*numRow+40);
		this.boardFrame.setTitle("Four in a Row!");
		this.boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		boardPanel = new BoardPanel();
	    boardFrame.getContentPane().add(BorderLayout.CENTER, boardPanel);

	     
		this.boardFrame.setVisible(true);
	}
	
	
	/**
	 * Set all cells to EMPTY, = 0;
	 */
	public void reset(){
		for(int row=0; row<this.board.length; row++){
			for(int col	=0; col<this.board[0].length; col++){
				this.board[row][col] = Piece.EMPTY;
			}
		}
	}
	
	/**
	 * Will search from the butom up and replace the first row that is empty.
	 * If the column is full, this method will return COLFULL = -1;
	 * @param col is the column of the board that will be checked
	 * @return the row which first is empty
	 */
	public int firstEmptyRow(int col){
		for(int tempRow = board.length-1; tempRow >= 0; tempRow--){
			if(this.board[tempRow][col] == Piece.EMPTY){
				System.out.println("I loopen");
				return tempRow;
			}
		}
		System.out.println("Efterloop");
		return COLUMNFULL;
	}
	
	/**
	 * A piece (turn) is placed at board[row][col]. Does not take in to considuration if the place is occupied or outside the board.
	 * @param row the row the piece is placed in.
	 * @param col the column the piece is placed in.
	 * @param turn. The Piece which is placed.
	 */
	public void setPiece(int row, int col, int turn){
		this.board[row][col] = turn;
	}
	

	
	/**
	 * Gets which pieced is placed in board[row][col];
	 * @param row
	 * @param col
	 * @return
	 */
	public int getPiece(int row, int col){
		return this.board[row][col];
	}
	
	public int getHeight(){
		return this.board.length;
	}
	
	public int getWidth(){
		return this.board[0].length;
	}

	
	public JFrame getFrame(){
		return boardFrame;
	}
	
	public boolean checkForWin(int row, int col, int turn){
		
		// Torn
		int counter=0;
		for(int temp=-this.toWin;temp<=toWin; temp++){				
				try{
					counter = this.board[row+temp][col] == turn ? counter+1:0;
				}catch (ArrayIndexOutOfBoundsException e){
					
				}		
				
				if(counter == toWin){
					return true;
				}
			}
		
		//Row
		counter=0;
		for(int temp=-this.toWin;temp<=toWin; temp++){				
			try{
				counter = this.board[row][col+temp] == turn ? counter+1:0;
			}catch (ArrayIndexOutOfBoundsException e){
				
			}		
			
			if(counter == toWin){
				return true;
			}
		}
		
		
		//HU -> NV
		counter=0;
		for(int temp=-this.toWin;temp<=toWin; temp++){				
			try{
				counter = this.board[row+temp][col+temp] == turn ? counter+1:0;
			}catch (ArrayIndexOutOfBoundsException e){
				
			}		
			
			if(counter == toWin){
				return true;
			}
		}
	
		//HN -> VU
		counter=0;
		for(int temp=-this.toWin;temp<=toWin; temp++){				
			try{
				counter = this.board[row+temp][col-temp] == turn ? counter+1:0;
			}catch (ArrayIndexOutOfBoundsException e){
				
			}		
			
			if(counter == toWin){
				return true;
			}
		}
	
		return false;
	}
	
	public int getMouseColMove(){
		mouseXClick =-1;
		
		
		ListenForMouse lForMouse = new ListenForMouse();
		boardPanel.addMouseListener(lForMouse);
		
		while(mouseXClick<0){
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
			}
		}
		for(int tempCol=0; tempCol<board[0].length-1; tempCol++){
			if(tempCol*cell.height<= mouseXClick && mouseXClick <=(tempCol+1)*cell.height){
				return tempCol;
			}
		}
		
		return board[0].length-1;
	}

	
	private class BoardPanel extends JPanel{
		public void paintComponent(Graphics g){
			// Start things
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, cell.width*board[0].length, cell.height*board.length);
			
			for(int tempRow=0; tempRow <board.length; tempRow++){
				for(int tempCol=0; tempCol < board[0].length; tempCol++){
					switch(board[tempRow][tempCol]){
					case Piece.GREEN:
						g.setColor(Color.GREEN);
						break;
					case Piece.RED:
						g.setColor(Color.RED);
						break;
					case Piece.EMPTY:
						g.setColor(Color.BLACK);
						break;
					}
				
					g.fillOval(tempCol*cell.width+2, tempRow*cell.height+2, cell.width-3, cell.height-3);
				}
			}
			
		}

	}
	
	private class ListenForMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent m) {	
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent m) {
			mouseXClick=m.getX();
			mouseYClick=m.getY();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	} // end of Listen for Keys
	
	
}// end of main class
