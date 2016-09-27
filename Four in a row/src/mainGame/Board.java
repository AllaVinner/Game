package mainGame;


import javax.swing.*;
import java.awt.Dimension;

public class Board {
	public static final int COLFULL = -1;
	public static final int COLNOTFULL = 0;
	
	private int [][] board;
	private int toWin;
	private Dimension cell;
	
	private JFrame boardFrame;
	
	public Board(int numRow, int numCol, int toWin, Dimension cell){
		this.board = new int [numRow] [numCol];
		reset();
		
		this.toWin = toWin;
		
		this.cell = new Dimension();
		this.cell = cell;
		
		this.boardFrame = new JFrame();
		this.boardFrame.setSize(cell.width*numCol, cell.height*numRow);
		this.boardFrame.setTitle("Four in a Row!");
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
	 * Will search from the buttom up and replace the first row that is empty.
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
		return COLFULL;
	}
	
	public void setPiece(int row, int col, int turn){
		this.board[row][col] = turn;
	}
	
	public int getPiece(int row, int col){
		return this.board[row][col];
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
	
	
}
