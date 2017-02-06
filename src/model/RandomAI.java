package model;

import java.awt.Point;
import java.util.Random;

/**
 * This strategy selects the first available move at random. It is easy to beat
 * 
 * @throws IGotNowhereToGoException
 *             whenever asked for a move that is impossible to deliver
 * 
 * @author mercer
 * Author: Zelin Wan
 */

// There is an intentional compile time error. Implement this interface
public class RandomAI implements TicTacToeStrategy {

	// Randomly find an open spot while ignoring possible wins and stops.
	// This should be easy to beat as a human.

	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		int emptyCounter = 0;
		for(int i = 0 ; i < theGame.size() ; i++){	// check if the board is filled
			for(int m = 0 ; m < theGame.size() ; m++){
				if(theGame.getTicTacToeBoard()[i][m] == '_'){
					emptyCounter++;
				}
			}
		}
		if(emptyCounter == 0){	//when empterCounter is 0, means no empty space
			throw new IGotNowhereToGoException("the board is filled");
		}
		Random rand = new Random();
		int x = rand.nextInt(3);
		int y = rand.nextInt(3);
		while(theGame.getTicTacToeBoard()[x][y] != '_'){
			x = rand.nextInt(3);
			y = rand.nextInt(3);
		}
		return new Point(x, y);
	}
}