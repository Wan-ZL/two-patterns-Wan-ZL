package model;

import java.awt.Point;
import java.util.Random;

/**
 * This TTT strategy tries to prevent the opponent from winning by checking for
 * a space where the opponent is about to win. If none found, it randomly picks
 * a place to win, which an sometimes be a win even if not really trying.
 * 
 * @author mercer
 */

/*
 * 1. if can block, do it. 2. if can win, get it 3. if enter is empty, fill it.
 * 4. if corner is empty, random choose one corner 5. random choose one Note:
 * 'O' is AI
 */
public class StopperAI implements TicTacToeStrategy {

	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		int x = 0;
		int y = 0;
		char target = 'O';
		int emptySpaceCounter = 0;
		int xCounter = 0;
		int emptyCounter = 0;
		
		for(int i = 0 ; i < theGame.size() ; i++){	// check if the board is filled
			for(int m = 0 ; m < theGame.size() ; m++){
				if(theGame.getTicTacToeBoard()[i][m] == '_'){
					emptySpaceCounter++;
				}
			}
		}
		if(emptySpaceCounter == 0){	//when empterCounter is 0, means no empty space
			throw new IGotNowhereToGoException("the board is filled");
		}
		
		// check if can block, the second loop is to check if can win
		for (int r = 0; r < 2; r++) {
			for (int i = 0; i < theGame.size(); i++) { // horizontal check
				xCounter = 0;
				emptyCounter = 0;
				for (int m = 0; m < theGame.size(); m++) {
					if (theGame.getTicTacToeBoard()[m][i] == target) {
						xCounter++;
					} else if (theGame.getTicTacToeBoard()[m][i] == '_') {
						x = m;
						y = i;
						emptyCounter++;
					}
				}
				if (xCounter == 2 && emptyCounter == 1) {
					return new Point(x, y);
				}
			}
			for (int i = 0; i < theGame.size(); i++) { // vertical check
				xCounter = 0;
				emptyCounter = 0;
				for (int m = 0; m < theGame.size(); m++) {
					if (theGame.getTicTacToeBoard()[i][m] == target) {
						xCounter++;
					} else if (theGame.getTicTacToeBoard()[i][m] == '_') {
						x = i;
						y = m;
						emptyCounter++;
					}
				}
				//System.out.printf("xCounter is %d, emptyCounter is %d, x is %d, y is %d\n", xCounter,emptyCounter, x, y);
				if (xCounter == 2 && emptyCounter == 1) {
					return new Point(x, y);
				}
			}
			for (int i = 0; i < theGame.size(); i++) { // left top to right
														// bottom, check
				xCounter = 0;
				emptyCounter = 0;
				if (theGame.getTicTacToeBoard()[i][i] == target) {
					xCounter++;
				} else if (theGame.getTicTacToeBoard()[i][i] == '_') {
					x = i;
					y = i;
					emptyCounter++;
				}
				if (xCounter == 2 && emptyCounter == 1) {
					return new Point(x, y);
				}
			}
			int m = theGame.size();
			for (int i = 0; i < theGame.size(); i++) { // right top to left
														// bottom, check
				m--;
				xCounter = 0;
				emptyCounter = 0;
				if (theGame.getTicTacToeBoard()[i][m] == target) {
					xCounter++;
				} else if (theGame.getTicTacToeBoard()[i][m] == '_') {
					x = i;
					y = m;
					emptyCounter++;
				}
				if (xCounter == 2 && emptyCounter == 1) {
					return new Point(x, y);
				}
			}
			target = 'X';
		}
		// check if 1 and 2 finished
		
		if(theGame.getTicTacToeBoard()[1][1] == '_'){	//	if center is empty, fill the center.
			return new Point(1, 1);
		}
		
		for(int i = 0 ; i < 3 ; i+=2){	// if corner is empty, fill it
			for(int m = 0 ; m < 3 ; m+=2){
				if(theGame.getTicTacToeBoard()[i][m] == '_'){
					return new Point(i, m);
				}
			}
		}
		
		Random rand = new Random();
		x = rand.nextInt(3);
		y = rand.nextInt(3);
		while(theGame.getTicTacToeBoard()[x][y] != '_'){
			x = rand.nextInt(3);
			y = rand.nextInt(3);
		}
		return new Point(x, y);
		
	}

}