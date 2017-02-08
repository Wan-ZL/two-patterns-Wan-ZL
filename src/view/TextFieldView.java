package view;

/*
 * Author: Zelin Wan
 * Purpose: This is another type of view, use JTextArea instead of JButton.
 * (This calss is learned from ButtonView.java)
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.OurObserver;
import javafx.scene.paint.Color;
import model.ComputerPlayer;
import model.TicTacToeGame;

public class TextFieldView extends JPanel implements OurObserver {

	private TicTacToeGame theGame;
	private JLabel stateText = new JLabel();
	private JTextArea[][] text = null;
	private JTextArea[][] inputText = null;
	private JLabel[][] inputLabel = null;
	private JButton[][] buttons = null;
	private ComputerPlayer computerPlayer;
	private int height, width;
	private int x, y;

	public TextFieldView(TicTacToeGame TicTacToeGame, int width, int height) {
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		initializeTextPanel();
	}

	private void initializeTextPanel() {	// initiallize the play Panel
		JPanel textPanel = new JPanel();
		int size = theGame.size();
		textPanel.setLayout(new GridLayout(size, size));
		Font myFont = new Font("Courier", Font.BOLD, 42);
		text = new JTextArea[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				text[i][j] = new JTextArea(" _");
				text[i][j].setFont(myFont);
				// buttons[i][j].addActionListener(buttonListener);
				textPanel.add(text[i][j]);
			}
		}
		this.setLayout(null);
		textPanel.setLocation(width / 6, 80);
		textPanel.setSize(width - 120, height - 200);
		this.add(textPanel);
		stateText.setSize(200,40);
		stateText.setFont(new Font("Arial", Font.BOLD, 18));
		stateText.setEnabled(false);
		stateText.setLocation(100, height - 100);
		this.add(stateText);
		initializeTextInput();
		initializeLabel();
		initializeButton();
	}

	private void initializeTextInput() {	// initailize the input square
		JPanel inputPanel = new JPanel();
		int size = 2;
		inputText = new JTextArea[size][1];
		inputPanel.setLayout(new GridLayout(size, size, 5, 5));
		for (int i = 0; i < size; i++) {
			inputText[i][0] = new JTextArea("");
			inputPanel.add(inputText[i][0]);
		}
		// this.setLayout(null);
		inputPanel.setLocation(10, 10);
		inputPanel.setSize(40, 40);
		this.add(inputPanel);
	}

	private void initializeLabel() {	// initailize the label of input square
		JPanel labelPanel = new JPanel();
		int size = 2;
		inputLabel = new JLabel[size][1];
		labelPanel.setLayout(new GridLayout(size, size, 5, 5));
		inputLabel[0][0] = new JLabel("row");
		labelPanel.add(inputLabel[0][0]);
		inputLabel[1][0] = new JLabel("column");
		labelPanel.add(inputLabel[1][0]);

		labelPanel.setLocation(60, 10);
		labelPanel.setSize(50, 40);
		this.add(labelPanel);
	}

	private void initializeButton() {	// initialize the play button
		JPanel buttonPanel = new JPanel();
		int size = 1;
		buttonPanel.setLayout(new GridLayout(size, size, 5, 5));
		ButtonListener buttonListener = new ButtonListener();
		buttons = new JButton[size][size];
		buttons[0][0] = new JButton("Make The move");
		buttons[0][0].addActionListener(buttonListener);
		buttonPanel.add(buttons[0][0]);
		buttonPanel.add(buttons[0][0]);
		buttonPanel.setLocation(120, 10);
		buttonPanel.setSize(150, 30);
		this.add(buttonPanel);

	}

	public void update() {	// update the text Panel
		if (theGame.maxMovesRemaining() == theGame.size() * theGame.size()) {
			resetText(true);
		}
		if (!theGame.stillRunning()) {
			resetText(false);
		} else {
			updateText();
		}
		// TODO
	}

	private void updateText() {
		char[][] temp = theGame.getTicTacToeBoard();
		for (int i = 0; i < temp.length; i++) {
			for (int m = 0; m < temp[i].length; m++) {
				String text = "" + temp[i][m];
				if (text.equals("X") || text.equals("O")) {
					this.text[i][m].setText(" "+text);
					this.text[i][m].setEnabled(false);
				}
			}
		}
		// TODO Auto-generated method stub

	}

	private void resetText(boolean enable) {	// fully reset the Text Panel
		for (int i = 0; i < theGame.size(); i++) {
			for (int m = 0; m < theGame.size(); m++) {
				text[i][m].setText(" _");
				text[i][m].setEnabled(enable);
			}
		}
	}

	private class ButtonListener implements ActionListener {	// the actionListener of play button

		@Override
		public void actionPerformed(ActionEvent arg0) {
			x = Integer.parseInt(inputText[0][0].getText());
			y = Integer.parseInt(inputText[1][0].getText());
			if(x > 2 || y > 2 || x < 0 || y < 0){
				System.out.println("ERROR, input range is 0-2");
				return;
			}
			if(theGame.getTicTacToeBoard()[x][y] != '_'){
				System.out.println("ERROR, the position already placed");
				return;
			}
			else{
				theGame.choose(x, y);
				updateText();		// after played, update the Panel
			}
			
			if(theGame.tied()){
				stateText.setText("Tied");
			}
			else if(theGame.didWin('X')){
				stateText.setText("X wins");
				updateText();
			}
			else{
				Point play = computerPlayer.desiredMove(theGame);
				theGame.choose(play.x, play.y);
				updateText();
				if(theGame.didWin('O')){
					stateText.setText("O wins");
					updateText();
				}
			}
			
		}

	}

}