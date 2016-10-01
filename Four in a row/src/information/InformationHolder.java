package information;

import java.awt.Dimension;

import mainGame.Piece;

public class InformationHolder{
	
	public static final int [] [] VALUEBOARD = {{3,4,5,7,5,4,3} , {4,6,8,10,8,6,4} , {5,8,11,13,11,8,5} , {5,8,11,13,11,8,5} , {4,6,8,10,8,6,4} , {3,4,5,7,5,4,3}};
	
	private CellPointer[][] pointerMatrix;
	private WiningRow [] winningArray;
	private int [] greenArray;
	private int [] redArray;
	
	/**
	 *
	 * @param numRow
	 * @param numCol
	 * @param toWin
	 * @param cell
	 */
	public InformationHolder(int numRow, int numCol, int toWin, Dimension cell){
		
		pointerMatrix = new CellPointer [numRow][numCol];
		
		greenArray = new int[toWin+1];
		redArray = new int[toWin+1];		
		
		for(int i=0; i<greenArray.length; i++){
			greenArray[i] = 0;
			redArray[i] = 0;
		}
		
		int [] valueRow ={1, 2, 3, 3, 2, 1};
		int [] valueColumn = {1, 2, 3, 4, 3, 2, 1};
		
		for(int tempRow=0; tempRow< numRow; tempRow++){
			for(int tempCol=0; tempCol<numCol; tempCol++){
				pointerMatrix[tempRow][tempCol] = new CellPointer( VALUEBOARD[tempRow][tempCol],tempRow, tempCol);
				pointerMatrix[tempRow][tempCol].giveArrayValue();
			}
		}
		
		winningArray = new WiningRow[69];
		
		for(int i=0; i < winningArray.length; i++){
			winningArray[i] = new WiningRow();
		}
		
	}
	
	
	/**
	 * This method will adjust the arrays "greenArray" and "redArray" to fit how many in a row of each there is.
	 * 
	 * @param placeRow is the row which a piece just was laid.
	 * @param placeCol is the column which a piece just was laid.
	 * @param turn. Which color the piece had
	 */
	public void picePlayed(int placeRow, int placeCol, int turn){
		int length =pointerMatrix[placeRow][placeCol].getIndexArray().length;
		int index=0;		

		for(int i=0; i<length; i++){
			
			index = pointerMatrix[placeRow][placeCol].getIndexArray()[i];
			
			if(winningArray[index].getOccupation() == Piece.EMPTY){
				System.out.println(index + "Detta var index");
				switch (turn) {
				case Piece.GREEN:
					greenArray[1]++;
					break;
				case Piece.RED:
					redArray[1]++;
				}
	
			}else if(winningArray[index].getOccupation() == WiningRow.DEAD){
				
			}else if(winningArray[index].getOccupation() == turn){
				switch (turn) {
				case Piece.GREEN:
					greenArray[winningArray[index].getCounter()]--;
					greenArray[winningArray[index].getCounter()+1]++;
					break;
				case Piece.RED:
					redArray[winningArray[index].getCounter()]--;
					redArray[winningArray[index].getCounter()+1]++;
					break;
				}
				
			}else if(winningArray[index].getOccupation() == -1*turn){
				switch (turn) {
				case Piece.GREEN:
					redArray[winningArray[index].getCounter()]--;
					break;
				case Piece.RED:
					greenArray[winningArray[index].getCounter()]--;
				}
			} // end if statement
			
			winningArray[index].pieceSet(turn);
			

		} // for end
	} // method end
	
	public void printArray(){
		System.out.println("Så här ligger grön till: ");
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Grön har %d st %d.\n", greenArray[i], i);
		}
		System.out.println();
		System.out.println("Så här ligger röda till: ");
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Röd har %d st %d.\n", redArray[i], i);
		}
		
	}
	
	public void printIndexArray(int [] a){
		for(int i =0;i < a.length; i++){
			System.out.println("!!  : " + a[i]);
		}
	}
	
	public int [] getGreenArray(){
		return this.greenArray;
	}
	
	public int [] getRedArray(){
		return this.redArray;
	}
	
	public void setGreenArray(int[] greenArray){
		for(int i=0; i< greenArray.length; i++){
			this.greenArray[i] =greenArray[i];
		}
	}
	
	public void setRedArray(int[] redArray){
		for(int i=0; i< redArray.length; i++){
			this.redArray[i] =redArray[i];
		}
	}

} // class end

