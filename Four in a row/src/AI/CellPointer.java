package AI;

public class CellPointer {
	
	public static final int ROWINDEX = 0;
	public static final int COLUMNINDEX=24;
	public static final int RU_LD =45;
	public static final int RD_LU =57;
	
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
				this.indexArray[arrayCounter] = tempIndex;
				arrayCounter++;
			}
		}
		
		//Win by Column
		multi=3;
		tempIndex=COLUMNINDEX;
		tempIndex+=multi*this.colIndex;
		
		for(int s=0; s<multi; s++){		 // multi is the number of possible wins on a row
			if(s<= this.rowIndex && this.rowIndex <s+4){
				this.indexArray[arrayCounter] = tempIndex;
				arrayCounter++;
			}
		}
		
		
		
	}
	
	
	
	
}
