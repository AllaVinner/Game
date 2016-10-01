package aI_Occupation;
import java.awt.Dimension;

import information.InformationHolder;
import mainGame.Board;
import mainGame.Piece;

public class Player {


	// Player is player Red
	private Board board;
	
	private int [] greenArray;
	private int [] redArray;
	private int boardValue =0;
	private InformationHolder info;
			 
	public Player(int numRow, int numCol, int toWin, Dimension cell){
		this.board = new Board(numRow, numCol, toWin, cell);
		info = new InformationHolder(numRow, numCol, toWin, cell);
		
		
		this.greenArray = new int [toWin+1];
		this.redArray = new int [toWin+1];
		for(int i=0; i < this.redArray.length; i++){
			greenArray[i] =0;
			redArray[i] =0;
		}
		
		
	}
	
	public int makeMove(){
		int col=-1;
		int tempValue =-12222;
		int bestValue =0;
		int tempRow=0;
		int [] tempGreenA = new int[this.greenArray.length];
		int [] tempRedA = new int [ this.redArray.length];
		
		for(int i=0; i< tempGreenA.length;i++){
			tempGreenA[i] = this.greenArray[i];
			tempRedA[i] = this.redArray[i];
		}
		
		for(int tempCol=0; tempCol <this.board.getWidth();tempCol++){
			tempRow = this.board.firstEmptyRow(tempCol);
			this.board.setPiece(tempRow, tempCol, Piece.RED);
			this.info.picePlayed(tempRow, tempCol, Piece.RED);
			
//			System.out.println("Col nr " + tempCol + "ger dessa reultat.");
//			this.info.printArray();
			
			tempValue =calculateBoardValue(this.info.getRedArray(), this.info.getGreenArray());
			if(tempValue > bestValue){
				bestValue = tempValue;
				col = tempCol;
			}
			
			for(int i=0; i < greenArray.length; i++){
				System.out.println("Grön " + this.greenArray[i]);
				System.out.println("Röd " + this.redArray[i]);
			}
			
			this.board.setPiece(tempRow, tempCol, Piece.EMPTY);
			this.info.picePlayed(tempRow, tempCol, Piece.EMPTY);
			this.info.setGreenArray(tempGreenA);
			this.info.setRedArray(tempRedA);
		}
		
		tempRow = this.board.firstEmptyRow(col);
		this.board.setPiece(tempRow, col, Piece.RED);
		this.info.picePlayed(tempRow, col, Piece.RED);
			
		return col;
		
	}
	
	public void moveMade(int playedRow, int playedCol){
		this.board.setPiece(playedRow, playedCol, Piece.GREEN);
		this.boardValue= calculateBoardValue(this.redArray, this.greenArray);
		this.info.picePlayed(playedRow, playedCol, Piece.GREEN);
		this.greenArray = this.info.getGreenArray();
		this.redArray = this.info.getRedArray();
		
	}
	
	public static int calculateBoardValue(int [] myArray, int [] oppArray){
		int tempValue= 0;
		
		//C*(n*i)^k
		int [] c ={0, 1, 2, 4, 8};
		int [] k = {0 ,1, 2, 3, 4};
		double a = 0.8;
		double tempD;
		
		for(int i=0; i< myArray.length; i++){
			tempD = Math.pow((double) myArray[i]*i, (double) k[i]);
			tempValue +=c[i]*(int) tempD;
			
			tempD =a * Math.pow((double) oppArray[i]*i, (double) k[i]);
			tempValue -= c[i]*(int) tempD;
		}

		return tempValue;
	}
	
}
