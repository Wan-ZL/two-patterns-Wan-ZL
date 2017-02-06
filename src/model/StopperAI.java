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
 * 1. if can block, do it. 
 * 2. if enter is empty, fill it. 
 * 3. if can win, get it
 * 4. if corner is empty, random choose one corner 
 * 5. random choose one
 */
public class StopperAI implements TicTacToeStrategy {

	@Override
	public Point desiredMove(TicTacToeGame theGame) {
		
		
		
		return null;
	}

}