package mainGame;

import java.awt.Dimension;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class GamePlay {
	
	public static void main(String[] args) {
		int numRow = 6;
		int numCol = 7;
		int toWin =4;
		Dimension cell = new Dimension(40,40);
		int turn = Piece.EMPTY;
		boolean gameON = true;
		boolean rightInput= true;
		
		
		Board myGame = new Board(numRow, numCol, toWin, cell);
		Scanner input = new Scanner(System.in);
		
		int placeRow=-1;
		int placeCol=-1;
		String catchString = "";
		
		while(gameON){
			drawBoard(myGame);
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
					System.out.print("Please enter the column nr you want to play in: ");
					placeCol = input.nextInt();
					placeRow = myGame.firstEmptyRow(placeCol);
					
					if(placeRow == Board.COLFULL) throw new ColumnIsFullException();
					
					myGame.setPiece(placeRow, placeCol, turn);
					System.out.println("H÷÷÷R");
					rightInput=true;
				
				}catch (InputMismatchException e){
					catchString = input.nextLine();
					rightInput=false;
				}catch (ArrayIndexOutOfBoundsException e){
					rightInput=false;
				}catch (ColumnIsFullException e){
					rightInput=false;
				}
			}while(! rightInput);
			
			gameON =! myGame.checkForWin(placeRow, placeCol, turn);
			
		} // While loop end
		System.out.println("Det ‰e slut");
	}
	
	
	public static void drawBoard(Board board){
		for(int row=0; row<6; row++){
			for(int col=0; col <7; col++){
				System.out.print(board.getPiece(row, col));
			}
			System.out.println();
		}
		
	}


}
