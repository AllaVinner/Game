package AI;

import java.awt.Dimension;

import mainGame.Piece;

public class InformationHolder{
	
	
	private CellPointer[][] pointerMatrix;
	private WiningRow [] winningArray;
	private int [] greenArray;
	private int [] redArray;
	
	public InformationHolder(int numRow, int numCol, int toWin, Dimension cell){
		
		pointerMatrix = new CellPointer [numRow][numCol];
		
		greenArray = new int[toWin];
		redArray = new int[toWin];		
		
		for(int i=0; i<toWin; i++){
			greenArray[i] = 0;
			redArray[i] = 0;
		}
		
		int [] valueRow ={1, 2, 3, 3, 2, 1};
		int [] valueColumn = {1, 2, 3, 4, 3, 2, 1};
		
		for(int tempRow=0; tempRow< numRow; tempRow++){
			for(int tempCol=0; tempCol<numCol; tempCol++){
				pointerMatrix[tempRow][tempCol] = new CellPointer( valueRow[tempRow] + valueColumn[tempCol] ,tempRow, tempCol);
				pointerMatrix[tempRow][tempCol].giveArrayValue();
			}
		}
		
		winningArray = new WiningRow[CellPointer.ROWINDEX+CellPointer.COLUMNINDEX];
		
		for(int i=0; i < winningArray.length; i++){
			winningArray[i] = new WiningRow();
		}
		
	}
	
	
	/**
	 * 
	 * @param placeRow
	 * @param placeCol
	 * @param turn
	 */
	public void picePlayed(int placeRow, int placeCol, int turn){
		int length =pointerMatrix[placeRow][placeCol].getIndexArray().length;
		int index=0;
		int preCounter=0;
		int preOccupation = Piece.EMPTY;
		
		for(int i=0; i<length; i++){
			index = pointerMatrix[placeRow][placeCol].getIndexArray()[i];
			System.out.println("index är: " + index);
			//Pre-settings

			if(winningArray[index].getOccupation() == turn){
				switch (turn) {
				case Piece.GREEN:
					greenArray[winningArray[index].getCounter()]--; // 1 in a row is found at [0]
					break;
				case Piece.RED:
					redArray[winningArray[index].getCounter()]--;
				}
				
				winningArray[index].pieceSet(turn);
				
				switch (turn) {
				case Piece.GREEN:
					greenArray[winningArray[index].getCounter()]++;
					break;
				case Piece.RED:
					redArray[winningArray[index].getCounter()]++;
					break;
				}
				
			}else if(winningArray[index].getOccupation() == Piece.EMPTY){
				switch (turn) {
				case Piece.GREEN:
					greenArray[winningArray[index].getCounter()]++;
					break;
				case Piece.RED:
					redArray[winningArray[index].getCounter()]++;
					break;
				}
			
			winningArray[index].pieceSet(turn);
				
			}else if(winningArray[index].getOccupation() == WiningRow.DEAD){
				winningArray[index].pieceSet(turn);
				
			}else if(winningArray[index].getOccupation() == -1*turn){
				
				switch (turn) {
				case Piece.GREEN:
					redArray[winningArray[index].getCounter()]--;
					break;
				case Piece.RED:
					greenArray[winningArray[index].getCounter()]--;
				}
				
				winningArray[index].pieceSet(turn);
	
			} // Else end
		} // for end
	} // method end
	
	public void printArray(){
		System.out.println("Så här ligger grön till: ");
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Grön har %d st %d.\n", greenArray[i], i+1);
		}
		System.out.println();
		System.out.println("Så här ligger röda till: ");
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Röd har %d st %d.\n", redArray[i], i+1);
		}
		
	}
	
} // class end
