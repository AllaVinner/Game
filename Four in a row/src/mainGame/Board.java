package mainGame;


import javax.swing.*;
import java.awt.Dimension;

public class Board {

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
	
	
	
}
