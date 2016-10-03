package information;

import mainGame.Piece;

public class WiningRow {

	public static final int DEAD=10;
	
	private int occupation;
	private int counter;
	
	
	public WiningRow(){
		this.counter=0;
		this.occupation=Piece.EMPTY;
	}
	
	public void pieceSet(int turn){
		if(turn == this.occupation || this.occupation == Piece.EMPTY){
			counter++;
			this.occupation = turn;
		}else{
			counter++;
			occupation = DEAD;
		}
		if(counter > 4){
			counter =4;
		}
	}
	
	public int getOccupation(){
		return this.occupation;
	}
	
	public int getCounter(){
		return counter;
	}

}
