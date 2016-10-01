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
			Dimension cell = new Dimension(100,100);

			Board myGame = new Board(numRow, numCol, toWin, cell);
			Player aIPlayer = new Player(numRow, numCol, toWin, cell);

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
				if(turn == Piece.GREEN){
				
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
				} else if(turn == Piece.RED){// end if green 
					System.out.println(21);
					placeCol = aIPlayer.makeMove();
					placeRow= myGame.firstEmptyRow(placeCol);
					System.out.println(22);
					myGame.setPiece(placeRow, placeCol, Piece.RED);
				}
				
				gameON =! myGame.checkForWin(placeRow, placeCol, turn);
				moveNum++;
				System.out.println(3);
				myGame.getFrame().repaint();
				System.out.println(4);
				
				if(moveNum == numCol*numRow){
					gameON =false;
				}
				
			} // While loop end
			
		} // main end
	} // 