package mainGame;

import java.awt.Dimension;

public class GamePlay {

	int numRow = 6;
	int numCol = 7;
	int toWin =4;
	Dimension cell = new Dimension(40,40);
	
	public static void main(String[] args) {
		int numRow = 6;
		int numCol = 7;
		int toWin =4;
		Dimension cell = new Dimension(40,40);
		
		Board myGame = new Board(numRow, numCol, toWin, cell);

	}

}
