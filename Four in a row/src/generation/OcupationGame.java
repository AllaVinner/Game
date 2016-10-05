package generation;


import mainGame.*;
import java.awt.Dimension;
import java.util.InputMismatchException;
import java.util.Scanner;

import aI_Occupation.Player;
import information.InformationHolder;
public class OcupationGame {




		
		public static void main(String[] args) {
			int numRow = 6;
			int numCol = 7;
			int toWin =4;
			int turn1 = Piece.RED;
			int turn2 = -1 *turn1;
			int start = Piece.GREEN;
			Dimension cell = new Dimension(100,100);

			Board myGame = new Board(numRow, numCol, toWin, cell, true);
			int [] c1 = {0,1, 2, 4, 8};
			int [] k1 ={0,1,2,3,4};
			double a1 = 1.0;
			
			int [] c2 = {0,1, 2, 4, 8};
			int [] k2 ={0,1,2,3,4};
			double a2 = 1.0;
			
			Player player1 = new Player(numRow, numCol, toWin, cell, c1, k1 , a1, turn1);
			Player player2 = new Player(numRow, numCol, toWin, cell, c2, k2 , a2, turn2);

			int placeRow=-1;
			int placeCol=-1;
			int turn = Piece.EMPTY;
			boolean gameON = true;
			int moveNum=0;
		
			for(int count=0; count < 50; count ++){
				gameON =true;
				while(gameON){
	
					switch(turn){
						case Piece.GREEN :
							turn = Piece.RED;
							break;
						case Piece.RED:
							turn = Piece.GREEN;
							break;
						case Piece.EMPTY:
							turn = start;
							break;
					}
					if(turn == turn1){
						placeCol = player1.makeMove();
						placeRow= myGame.firstEmptyRow(placeCol);
						myGame.setPiece(placeRow, placeCol, turn1);
					} else if(turn == turn2){// end if green 
						placeCol = player2.makeMove();
						placeRow= myGame.firstEmptyRow(placeCol);
						myGame.setPiece(placeRow, placeCol, turn2);
					}
					
					gameON =! myGame.checkForWin(placeRow, placeCol, turn);
					myGame.getFrame().repaint();
					
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					
					moveNum++;
					if(moveNum == numCol*numRow){
						gameON =false;
					}
		
				} // GameWhile loop end
				
				if(turn != turn1){
					c1 = generatRandom(c1);
					k1 = generatRandom(k1);
					a1 += Math.random() - 0.2;
					player1.setConstants(c1, k1, a1);
					print(c2, k2, a2);
				}else if(turn != turn2){
					c2 = generatRandom(c2);
					k2 = generatRandom(k2);
					a2 += Math.random() - 0.2;
					player2.setConstants(c2, k2, a2);
					print(c1, k1, a1);
				}
				
				player1.reset();
				player2.reset();
				myGame.reset();
				myGame.getFrame().repaint();
				turn = Piece.EMPTY;
				
			} // for generation end
			
			
			
			
		} // main end
		
		
		public static int [] generatRandom(int [] a){
			int s=0;
			int [] r = new int[a.length];
			r = a.clone();
			
			for(int i=0; i < a.length; i++){
				s = (int) (Math.random()*4) -2;
				r[i]+=s;
			}
			return r;
		}
		
		public static void print(int [] c, int[] k, double a){
			System.out.println("Winner was combination");
			System.out.println("Winner c array");
			for(int i=0; i<c.length; i++){
				System.out.printf("C for %d i rad var: %d\n", i, c[i]);
			}
			System.out.println();
			for(int i=0; i<c.length; i++){
				System.out.printf("k for %d i rad var: %d\n", i, k[i]);
			}
			System.out.println("the a constant was " + a);
			
			
		}
		
	} // 

