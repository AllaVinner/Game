package aI_Occupation;
import java.awt.Dimension;
import java.util.ArrayList;

import information.InformationHolder;
import mainGame.Board;
import mainGame.ColumnIsFullException;
import mainGame.Piece;

public class Player {
	
	public static final int NOIDIOTMOVE = -11;
	public static final int IDIOTMOVE = -17;
	

	// Player is player Red
	private Board board;
	
	private int [] opArray;
	private int [] myArray;
	private int boardValue=0;
	private InformationHolder info;
	private int [] c;
	private int[] k;
	private double a;
	private int myTurn;
	private int opTurn;
	
			 
	public Player(int numRow, int numCol, int toWin, Dimension cell, int [] k, int [] c, double a, int turn){
		this.board = new Board(numRow, numCol, toWin, cell, false);
		info = new InformationHolder(numRow, numCol, toWin, cell);
		this.k= new int [toWin+1];
		this.c= new int [toWin+1];
		this.a = a;
		this.myTurn = turn;
		this.opTurn = -1*turn;
		
		this.opArray = new int [toWin+1];
		this.myArray = new int [toWin+1];
		for(int i=0; i < this.myArray.length; i++){
			opArray[i] =0;
			myArray[i] =0;
			this.k[i] = k[i];
			this.c[i] = c[i];
		}
		
		
	}
	
	public int makeMove(){
		System.out.println("BoardValue: " + calculateBoardValue(myArray, opArray));
		int col=-10;
		int tempValue =0;
		int bestValue =-1000;
		int tempRow=0;
		int [] columnValue = new int [this.board.getWidth()];
		
		col = idiotMove();
		
		if(col != NOIDIOTMOVE){
			tempRow = board.firstEmptyRow(col);
			this.board.setPiece(tempRow, col, myTurn);
			this.info.picePlayed(tempRow, col, myTurn, true);
			System.out.println("BoardValue: " + calculateBoardValue(myArray, opArray));
			return col;
		}
		
//		col = idiot3();
//		if(col != NOIDIOTMOVE){
//			tempRow = board.firstEmptyRow(col);
//			this.board.setPiece(tempRow, col, Piece.RED);
//			this.info.picePlayed(tempRow, col, Piece.RED, true);
//			return col;
//		}

		// this.greenArray / red kommer hålla sig konstanta, info kommer skifta
		
		for(int tempCol=0; tempCol < this.board.getWidth(); tempCol++){
			try{
				tempRow = this.board.firstEmptyRow(tempCol);
				if(tempRow == Board.COLUMNFULL) throw new ColumnIsFullException();
				// Place the fake piece
				this.board.setPiece(tempRow, tempCol, myTurn);
				this.info.picePlayed(tempRow, tempCol, myTurn, false);
				
				// If good move, update the supposed move
				
				switch(myTurn){
				case Piece.GREEN:
					tempValue = calculateBoardValue(this.info.getGreenArray(), this.info.getRedArray());
					break;
				case Piece.RED:
					tempValue = calculateBoardValue(this.info.getRedArray(), this.info.getGreenArray());
					break;
				}
				
				if(tempValue > bestValue){
					bestValue = tempValue;
					col = tempCol;
				}
				columnValue[tempCol]= tempValue;
				
				//reset settings
				this.board.setPiece(tempRow, tempCol, Piece.EMPTY);
				this.info.picePlayed(tempRow, tempCol, Piece.EMPTY,false);
				this.info.setGreenArray(this.opArray);
				this.info.setRedArray(this.myArray);
			}catch (ColumnIsFullException e){
				columnValue[tempCol] = Board.COLUMNFULL;
			}
		}
		
			for(int i=0; i < this.board.getWidth(); i++){
				col = getNBiggestIndex(columnValue, i);
				tempRow = this.board.firstEmptyRow(col);
				if(col != Board.COLUMNFULL && tempRow != Board.COLUMNFULL){

					if(secoundIdiotMove(tempRow, col) == NOIDIOTMOVE){
						this.board.setPiece(tempRow, col, myTurn);
						this.info.picePlayed(tempRow, col, myTurn, true);
						System.out.println("BoardValue: " + calculateBoardValue(myArray, opArray));
						return col;
					}
				}
			}
			for(int i=0; i< this.board.getWidth(); i++){
				col =getNBiggestIndex(columnValue, i);
				tempRow = this.board.firstEmptyRow(col);
				if(col != Board.COLUMNFULL && tempRow != Board.COLUMNFULL){
					this.board.setPiece(tempRow, col, myTurn);
					this.info.picePlayed(tempRow, col, myTurn, true);
					System.out.println("BoardValue: " + calculateBoardValue(myArray, opArray));
					return col;
				}
			}
			return 404;
	}
	
	private int secoundIdiotMove(int row, int col) {
		this.board.setPiece(row, col, myTurn);
		try{
			this.board.setPiece(row-1, col, opTurn);
		
		if(this.board.checkForWin(row-1, col, opTurn)){;
				this.board.setPiece(row, col, Piece.EMPTY);
				this.board.setPiece(row-1, col, Piece.EMPTY);
				return IDIOTMOVE; 
			}
		this.board.setPiece(row-1, col, Piece.EMPTY);
		}catch (ArrayIndexOutOfBoundsException e){}
		this.board.setPiece(row, col, Piece.EMPTY);
		return NOIDIOTMOVE;
	}

	public void moveMade(int playedRow, int playedCol){
		this.board.setPiece(playedRow, playedCol, opTurn);
		this.info.picePlayed(playedRow, playedCol, opTurn, true);
		for(int i=0; i < this.opArray.length; i++){
			this.opArray[i] = this.info.getGreenArray()[i];
			this.myArray[i] = this.info.getRedArray()[i];
		}
		this.boardValue= calculateBoardValue(this.myArray, this.opArray);
		
	}
	
	public int idiot3(){
		int tempRow1=0;
		int tempRow2=0;
		int tempRow3=0;
		
		int counter=0;
		int posCol=-1;
		
		for(int r1=0; r1 <this.board.getWidth(); r1++){
			for(int g1=0; g1 <this.board.getWidth(); g1++){
				for(int g2=0; g2 <this.board.getWidth(); g2++){
					try{
						tempRow1 = board.firstEmptyRow(r1);
						if(tempRow1 == Board.COLUMNFULL) throw new ColumnIsFullException();
						board.setPiece(tempRow1, r1, myTurn);
						
						tempRow2 = board.firstEmptyRow(g1);
						if(tempRow1 == Board.COLUMNFULL) throw new ColumnIsFullException();
						board.setPiece(tempRow2, g1, opTurn);
						
						tempRow3 = board.firstEmptyRow(g2);
						if(tempRow1 == Board.COLUMNFULL) throw new ColumnIsFullException();
						board.setPiece(tempRow3, g2, opTurn);
						
						if(board.checkForWin(tempRow3, g2, opTurn)){
							counter ++;
						}else {
							posCol = r1;
						}

						board.setPiece(tempRow1, r1, Piece.EMPTY);
						board.setPiece(tempRow2, g1, Piece.EMPTY);
						board.setPiece(tempRow3, g2, Piece.EMPTY);
						
	
					} catch (ColumnIsFullException e){}
					
					
					
				}
			}
		}
		
		
		
		if(counter > 1){
			return posCol;
		}else{
			return NOIDIOTMOVE;
		}
	}
	
	public int calculateBoardValue(int [] myArray, int [] oppArray){
		int tempValue= 0;
		
		//C*(n*i)^k

		double tempD;
		
		for(int i=0; i< myArray.length; i++){
			tempD = Math.pow((double) myArray[i]*i, (double) this.k[i]);
			tempValue +=this.c[i]*(int) tempD;
			
			tempD =a * Math.pow((double) oppArray[i]*i, (double) this.k[i]);
			tempValue -= this.c[i]*(int) tempD;
		}

		return tempValue;
	}
	
	
	public void printArray(){
		System.out.println("Så här ligger grön till: ");
		for(int i=0; i < opArray.length; i++){
			System.out.printf("Grön har %d st %d.\n", this.opArray[i], i);
		}
		System.out.println();
		System.out.println("Så här ligger röda till: ");
		for(int i=0; i < opArray.length; i++){
			System.out.printf("Röd har %d st %d.\n", this.myArray[i], i);
		}
		
	}
	
	public int idiotMove(){
		int tempRow=-10;
		// Can Red (AI) win in next move
		for(int tempCol=0; tempCol < this.board.getWidth(); tempCol++){
			try{
				tempRow = this.board.firstEmptyRow(tempCol);
				if(tempRow == Board.COLUMNFULL) throw new ColumnIsFullException();
				
				this.board.setPiece(tempRow, tempCol, myTurn);
				if(this.board.checkForWin(tempRow, tempCol, opTurn)) { 
					this.board.setPiece(tempRow, tempCol, Piece.EMPTY);
					System.out.println(" idiot red");
					return tempCol;
				}
				this.board.setPiece(tempRow, tempCol, Piece.EMPTY);			
			} catch (ColumnIsFullException e){}
		}
		
		// can Green (opp) win in there next move
		for(int tempCol=0; tempCol < this.board.getWidth(); tempCol++){
			try{	
				tempRow = this.board.firstEmptyRow(tempCol);
				if(tempRow == Board.COLUMNFULL) throw new ColumnIsFullException();
				
				this.board.setPiece(tempRow, tempCol, opTurn);
				if(this.board.checkForWin(tempRow, tempCol, opTurn)) {
					System.out.println("idiot gröna");
					this.board.setPiece(tempRow, tempCol, Piece.EMPTY);
					return tempCol;
				}
				this.board.setPiece(tempRow, tempCol, Piece.EMPTY);			
			} catch (ColumnIsFullException e){}
		}
		
		// can 
		return NOIDIOTMOVE;
	}
	
	public static int [] sort(int [] a){
		int temp=0;
		for(int i=a.length-1; i > 0; i--){
			for(int j=0; j < i; j++){
				if(a[j] < a[j+1]){
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		return a;
	}
	
	
	public static int getNBiggestIndex(int [] a, int n){
		int [] tempA = new int[a.length];
		for(int i=0; i< a.length; i++){
			tempA[i] = a[i];
		}
		
		int [] index = new int [a.length];
		for(int i=0; i< a.length; i++){
			index[i] = i;
		}
		
		int tempI =sortWithIndex(a, index, n);
		for(int i=0; i< a.length; i++){
			a[i] = tempA[i];
		}
		
		return tempI;
	}
	
	public static int sortWithIndex(int[] value, int [] index, int n){
		int temp=0;
		int [] tempIndex = new int[index.length];
		int [] tempValue = new int[value.length];
		
		for(int i=0; i < value.length; i++){
			tempIndex[i] = index[i];
			tempValue[i] = value[i];
		}
		
		for(int i=tempIndex.length-1; i > 0; i--){
			for(int j=0; j < i; j++){
				if(tempValue[j] < tempValue[j+1]){
					temp = tempValue[j];
					tempValue[j] = tempValue[j+1];
					tempValue[j+1] = temp;
					
					temp = tempIndex[j];
					tempIndex[j] = tempIndex[j+1];
					tempIndex[j+1] = temp;
				}
			}
		}
		return tempIndex[n];
	}
	
	public static void print(int []  a){
		for (int i: a) {
			System.out.println(i);
		}
	}
	
	
	public static void drawBoard(int[][] board){
		
		for(int i = 0; i < 6; i ++){
			for(int j = 0; j < 7; j ++){
				System.out.print("+-");
			}
			System.out.println("+");
		
		
			for(int k = 0; k < 7; k ++){
				System.out.print("|");
				
				char c = '$';
				switch (board[i][k]){
				case 1 :
					c = 'x';
					break;
				case -1 :
					c = 'o';
					break;
				case 0 :
					c = ' ';
					break;
				}
									
				
				System.out.print(c);
			}
			System.out.println("|");
			
		}
		for(int j = 0; j < 7; j ++){
			System.out.print("+-");		}
		System.out.println("+");
	}
	
	
}
