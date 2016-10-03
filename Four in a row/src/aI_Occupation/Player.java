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
	
	private int [] greenArray;
	private int [] redArray;
	private int boardValue=0;
	private InformationHolder info;
	private int [] c;
	private int[] k;
	private double a;
	
			 
	public Player(int numRow, int numCol, int toWin, Dimension cell, int [] k, int [] c, double a){
		this.board = new Board(numRow, numCol, toWin, cell, false);
		info = new InformationHolder(numRow, numCol, toWin, cell);
		this.k= new int [toWin+1];
		this.c= new int [toWin+1];
		this.a = a;
		
		this.greenArray = new int [toWin+1];
		this.redArray = new int [toWin+1];
		for(int i=0; i < this.redArray.length; i++){
			greenArray[i] =0;
			redArray[i] =0;
			this.k[i] = k[i];
			this.c[i] = c[i];
		}
		
		
	}
	
	public int makeMove(){
		System.out.println("BoardValue: " + calculateBoardValue(redArray, greenArray));
		int col=-10;
		int tempValue =0;
		int bestValue =-1000;
		int tempRow=0;
		int [] columnValue = new int [this.board.getWidth()];
		
		col = idiotMove();
		
		if(col != NOIDIOTMOVE){
			tempRow = board.firstEmptyRow(col);
			this.board.setPiece(tempRow, col, Piece.RED);
			this.info.picePlayed(tempRow, col, Piece.RED, true);
			System.out.println("BoardValue: " + calculateBoardValue(redArray, greenArray));
			return col;
		}

		// this.greenArray / red kommer hålla sig konstanta, info kommer skifta
		
		for(int tempCol=0; tempCol < this.board.getWidth(); tempCol++){
			try{
				tempRow = this.board.firstEmptyRow(tempCol);
				if(tempRow == Board.COLUMNFULL) throw new ColumnIsFullException();
				// Place the fake piece
				this.board.setPiece(tempRow, tempCol, Piece.RED);
				this.info.picePlayed(tempRow, tempCol, Piece.RED, false);
				
				// If good move, update the supposed move
				tempValue = calculateBoardValue(this.info.getRedArray(), this.info.getGreenArray());
				if(tempValue > bestValue){
					bestValue = tempValue;
					col = tempCol;
				}
				columnValue[tempCol]= tempValue;
				
				//reset settings
				this.board.setPiece(tempRow, tempCol, Piece.EMPTY);
				this.info.picePlayed(tempRow, tempCol, Piece.EMPTY,false);
				this.info.setGreenArray(this.greenArray);
				this.info.setRedArray(this.redArray);
			}catch (ColumnIsFullException e){
				columnValue[tempCol] = Board.COLUMNFULL;
			}
		}
		
			for(int i=0; i < this.board.getWidth(); i++){
				col = getNBiggestIndex(columnValue, i);
				tempRow = this.board.firstEmptyRow(col);
				if(col != Board.COLUMNFULL && tempRow != Board.COLUMNFULL){

					if(secoundIdiotMove(tempRow, col) == NOIDIOTMOVE){
						this.board.setPiece(tempRow, col, Piece.RED);
						this.info.picePlayed(tempRow, col, Piece.RED, true);
						System.out.println("BoardValue: " + calculateBoardValue(redArray, greenArray));
						return col;
					}
				}
			}
			for(int i=0; i< this.board.getWidth(); i++){
				col =getNBiggestIndex(columnValue, i);
				tempRow = this.board.firstEmptyRow(col);
				if(col != Board.COLUMNFULL && tempRow != Board.COLUMNFULL){
					this.board.setPiece(tempRow, col, Piece.RED);
					this.info.picePlayed(tempRow, col, Piece.RED, true);
					System.out.println("BoardValue: " + calculateBoardValue(redArray, greenArray));
					return col;
				}
			}
			return 404;
	}
	
	private int secoundIdiotMove(int row, int col) {
		this.board.setPiece(row, col, Piece.RED);
		try{
			this.board.setPiece(row-1, col, Piece.GREEN);
		
		if(this.board.checkForWin(row-1, col, Piece.GREEN)){;
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
		this.board.setPiece(playedRow, playedCol, Piece.GREEN);
		this.info.picePlayed(playedRow, playedCol, Piece.GREEN, true);
		for(int i=0; i < this.greenArray.length; i++){
			this.greenArray[i] = this.info.getGreenArray()[i];
			this.redArray[i] = this.info.getRedArray()[i];
		}
		this.boardValue= calculateBoardValue(this.redArray, this.greenArray);
		
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
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Grön har %d st %d.\n", this.greenArray[i], i);
		}
		System.out.println();
		System.out.println("Så här ligger röda till: ");
		for(int i=0; i < greenArray.length; i++){
			System.out.printf("Röd har %d st %d.\n", this.redArray[i], i);
		}
		
	}
	
	public int idiotMove(){
		int tempRow=-10;
		// Can Red (AI) win in next move
		for(int tempCol=0; tempCol < this.board.getWidth(); tempCol++){
			try{
				tempRow = this.board.firstEmptyRow(tempCol);
				if(tempRow == Board.COLUMNFULL) throw new ColumnIsFullException();
				
				this.board.setPiece(tempRow, tempCol, Piece.RED);
				if(this.board.checkForWin(tempRow, tempCol, Piece.RED)) { 
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
				
				this.board.setPiece(tempRow, tempCol, Piece.GREEN);
				if(this.board.checkForWin(tempRow, tempCol, Piece.GREEN)) {
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
