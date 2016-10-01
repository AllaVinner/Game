package mainGame;

import java.awt.Dimension;
import java.util.InputMismatchException;
import java.util.Scanner;

import information.InformationHolder;


public class GamePlay {
	
	public static void main(String[] args) {
		int numRow = 6;
		int numCol = 7;
		int toWin =4;
		Dimension cell = new Dimension(100,100);

		Board myGame = new Board(numRow, numCol, toWin, cell);
		

		int placeRow=-1;
		int placeCol=-1;
		int turn = Piece.EMPTY;
		boolean gameON = true;
		int moveNum=0;
	
		
		while(gameON){

			switch(turn){
				case Piece.GREEN :
					turn = Piece.RED;
					break;
				case Piece.RED:
					turn = Piece.GREEN;
					break;
				case Piece.EMPTY:
					turn = Piece.GREEN;
					break;
			}
			
			do{
				try{
					placeCol =myGame.getMouseColMove();
					placeRow = myGame.firstEmptyRow(placeCol);
					
					if(placeRow == Board.COLUMNFULL) throw new ColumnIsFullException();
					
					myGame.setPiece(placeRow, placeCol, turn);	
					
					break;
					
				}catch (ColumnIsFullException e){}

					
			}while(true);
			
			gameON =! myGame.checkForWin(placeRow, placeCol, turn);
			moveNum++;
			myGame.getFrame().repaint();
			
			if(moveNum == numCol*numRow){
				gameON =false;
			}
			
		} // While loop end
		
	} // main end
} // 
