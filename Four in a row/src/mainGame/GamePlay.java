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
		Dimension cell = new Dimension(100,100);
		int turn = Piece.EMPTY;
		boolean gameON = true;
		boolean rightInput= true;
		
		
		Board myGame = new Board(numRow, numCol, toWin, cell);
		Scanner input = new Scanner(System.in);
		
		/*Här börjar nyy
		 */
//		CellPointer[][] cellPointerBoard = new CellPointer[numRow][numCol];
//		
//		int [] tempC = {1, 2, 3, 4, 3, 2,1};
//		int [] tempR = {1, 2, 3, 3, 2, 1};
//		for(int tempRow=0; tempRow<cellPointerBoard.length; tempRow++){
//			for(int tempCol=0; tempCol<cellPointerBoard[0].length; tempCol++){
//				cellPointerBoard[tempRow][tempCol] = new CellPointer(tempR[tempRow]+tempC[tempCol], tempRow, tempCol);
//				cellPointerBoard[tempRow][tempCol].giveArrayValue();
//			}
//		}
//		
//		WiningRow[] occupationArray = new WiningRow[CellPointer.RU_LD];
//		for(int i=0; i<occupationArray.length; i++){
//			occupationArray[i] = new WiningRow();
//		}
//		
//		int[] greenNumInRow = {0, 0, 0,0};
//		int[] redNumInRow = {0, 0, 0,0};
		
		
		int placeRow=-1;
		int placeCol=-1;
		String catchString = "";
		
		while(gameON){
			myGame.getFrame().repaint();
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
					//placeCol = input.nextInt();
					placeCol =myGame.getMouseColMove();
					placeRow = myGame.firstEmptyRow(placeCol);
					
					
					if(placeRow == Board.COLUMNFULL) throw new ColumnIsFullException();
					
					myGame.setPiece(placeRow, placeCol, turn);
					
					/*Nytt igen
					 */
//					for(int i=0; i<cellPointerBoard[placeRow][placeCol].getIndexArray().length;i++){
//						occupationArray[cellPointerBoard[placeRow][placeCol].getIndexArray()[i]].pieceSet(turn);
//					}
					//hit
					
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
			
			
//			for(int i=0; i < CellPointer.RU_LD; i++){
//				if(occupationArray[i].getOccupation() == Piece.GREEN){
//					greenNumInRow[occupationArray[i].getCounter()-1]++;
//				}
//				if(occupationArray[i].getOccupation() == Piece.RED){
//					redNumInRow[occupationArray[i].getCounter()-1]++;
//				}
//			} 
//			
//			System.out.println("Här är de grönas resultat");
//			for(int i=0; i<4; i++){
//				System.out.println(greenNumInRow[i]);
//			}
//			System.out.println();
//			System.out.println("Här är de rödas resultat");
//			for(int i=0; i<4; i++){
//				System.out.println(redNumInRow[i]);
//			}
//			
			
		} // While loop end
		myGame.getFrame().repaint();
		System.out.println("Det äe slut");
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
