package information;

public class CellPointer {
	
	public static final int ROWINDEX = 0;
	public static final int COLUMNINDEX=24;
	public static final int RU_LD =45;
	public static final int RD_LU =57;
	
	public static final int [] DIAGONALINDEX= {0, 0+1, 1+2, 3+3, 6+3, 9+2};
	public static final int [] DIAGONALWINROW ={1, 2, 3, 3, 2, 1};
	
	
	private int[]indexArray;
	private int rowIndex;
	private int colIndex;
	
	
	
	public CellPointer(int ArrayLength, int rowIndex, int colIndex){
		this.indexArray = new int[ArrayLength];
		this.rowIndex=rowIndex;
		this.colIndex=colIndex;
	}
	
	public int[] getIndexArray(){
		return this.indexArray;
	}
	
	public void giveArrayValue(){
		int arrayCounter=0;
		int multi=0;
		int tempIndex=0;
		
		//Win by row
		multi=4;
		tempIndex = ROWINDEX;
		tempIndex += multi*this.rowIndex;
		
		for(int s=0; s<multi; s++){		 // multi is the number of possible wins on a row
			if(s<= this.colIndex && this.colIndex <s+4){
				this.indexArray[arrayCounter] = tempIndex+s;
				arrayCounter++;
			}
		}
		
		//Win by Column
		multi=3;
		tempIndex=COLUMNINDEX;
		tempIndex+=multi*this.colIndex;
		
		for(int s=0; s<multi; s++){		 // multi is the number of possible wins on a row
			if(s<= this.rowIndex && this.rowIndex <s+4){
				this.indexArray[arrayCounter] = tempIndex+s;
				arrayCounter++;
			}
		}


		
		//1.
		tempIndex = RU_LD;
		
		//2
		int subtraction = this.rowIndex - this.colIndex;
		int i = -subtraction +2; // this takes the subtraction to the right index in DiagonalIndex[]
		try{
			tempIndex += DIAGONALINDEX[i];
			
			//3
			int startCol = getSubStart(this.rowIndex, this.colIndex);
			int tempRow;
			for(int tempCol = startCol; tempCol< startCol + DIAGONALWINROW[i]; tempCol++){
				tempRow = subtraction + tempCol;
				if(tempCol <= this.colIndex && this.colIndex < tempCol+4 && tempRow <= this.rowIndex && this.rowIndex < tempRow+4){
					this.indexArray[arrayCounter] = tempIndex + tempCol-startCol;
					arrayCounter ++;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e){}
		
		
		
		
		
		//1.
		tempIndex = RD_LU;
		
		//2
		int addition = this.rowIndex + this.colIndex;
		i = addition -3; // this takes the addition to the right index in DiagonalIndex[]
		try{
			tempIndex += DIAGONALINDEX[i];
			
			//3
			int startCol = getAddStart(this.rowIndex, this.colIndex);
			int tempRow;
			for(int tempCol = startCol; tempCol< startCol + DIAGONALWINROW[i]; tempCol++){
				tempRow = addition - tempCol;
				if(tempCol <= this.colIndex && this.colIndex < tempCol+4 && tempRow -4 <this.rowIndex && this.rowIndex <= tempRow){
					this.indexArray[arrayCounter] = tempIndex + tempCol-startCol;
					arrayCounter ++;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e){}
		
	}
	
	public int getSubStart(int row, int col){
		if(row > col){
			return 0;
		}else{
			return col -row;
		}
	}
	
	public static int getAddStart(int row, int col){
		if(row+col < 5){
			return 0;
		}else{
			return col+row -5;
		}
	}
	
	
	
	
}
