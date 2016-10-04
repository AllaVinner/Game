package mainGame;
	import java.awt.Dimension;
	import java.util.InputMismatchException;
	import java.util.Scanner;

import aI_Occupation.Player;
import information.InformationHolder;

	public class GamePlay_AI {

		
		public static void main(String[] args) {
			int numRow = 6;
			int numCol = 7;
			int toWin =4;
			int playTurn = Piece.RED;
			int aITurn = -1 *playTurn;
			int start = Piece.GREEN;
			Dimension cell = new Dimension(100,100);

			Board myGame = new Board(numRow, numCol, toWin, cell, true);
			int [] c = {0,1, 2, 4, 8};
			int k[] ={0,1,2,3,4};
			double a = 1.0;
			
			
			Player aIPlayer = new Player(numRow, numCol, toWin, cell, c, k , a, aITurn);

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
						turn = start;
						break;
				}
				if(turn == playTurn){
				
					do{
						try{
							placeCol =myGame.getMouseColMove();
							placeRow = myGame.firstEmptyRow(placeCol);
							if(placeRow == Board.COLUMNFULL) throw new ColumnIsFullException();
							
							myGame.setPiece(placeRow, placeCol, turn);	
							System.out.println(2);
							break;
							
						}catch (ColumnIsFullException e){}
					
							
					}while(true);
					aIPlayer.moveMade(placeRow, placeCol);
				} else if(turn == aITurn){// end if green 
					placeCol = aIPlayer.makeMove();
					placeRow= myGame.firstEmptyRow(placeCol);
					myGame.setPiece(placeRow, placeCol, aITurn);
				}
				
				gameON =! myGame.checkForWin(placeRow, placeCol, turn);
				moveNum++;
				myGame.getFrame().repaint();
				
				if(moveNum == numCol*numRow){
					gameON =false;
				}
				
			} // While loop end
			
		} // main end
	} // 