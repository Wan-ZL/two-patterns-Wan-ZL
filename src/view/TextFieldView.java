package view;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.OurObserver;
import model.ComputerPlayer;
import model.TicTacToeGame;

public class TextFieldView extends JPanel implements OurObserver{
	
	private TicTacToeGame theGame;
	private JTextArea stateText = new JTextArea("Emter Value to Move");
	private JTextArea[][] text = null;
	private JTextArea[][] inputText = null;
	private ComputerPlayer computerPlayer;
	private int height, width;
	
	public TextFieldView(TicTacToeGame TicTacToeGame, int width,int height){
		theGame = TicTacToeGame;
		this.height = height;
		this.width = width;
		computerPlayer = theGame.getComputerPlayer();
		initializeTextPanel();
	}
	
	private void initializeTextPanel() {
		JPanel textPanel = new JPanel();
		int size = theGame.size();
		textPanel.setLayout(new GridLayout(size, size, 5, 5));
		Font myFont = new Font("Arial", Font.TRUETYPE_FONT, 40);
		text = new JTextArea[size][size];
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				text[i][j] = new JTextArea(" _");
				text[i][j].setFont(myFont);
				//buttons[i][j].addActionListener(buttonListener);
				textPanel.add(text[i][j]);
			}
		}
		this.setLayout(null);
		textPanel.setLocation(width/6, 80);
		textPanel.setSize(width -120, height - 200);
		this.add(textPanel);
		initializeTextInput();
	} 
	
	private void initializeTextInput(){
		JPanel inputPanel = new JPanel();
		int size = 2;
		inputText = new JTextArea[size][size];
		inputPanel.setLayout(new GridLayout(size, size, 5, 5));
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				inputText[i][j] = new JTextArea("");
				inputPanel.add(inputText[i][j]);
			}
		}
		//this.setLayout(null);
		inputPanel.setLocation(10, 10);
		inputPanel.setSize(80, 40);
		this.add(inputPanel);
		
	}
	
	public void update(){
		if(theGame.maxMovesRemaining() == theGame.size()*theGame.size()){
			resetText(true);
		}
		if(!theGame.stillRunning()){
			resetText(false);
		}
		else{
			updateText();
			stateText.setText("Emter Value to Move");
		}
		//TODO
	}

	private void updateText() {
		char[][] temp = theGame.getTicTacToeBoard();
		for(int i = 0 ; i < temp.length ; i++){
			for(int m = 0 ; m < temp[i].length ; m++){
				String text = "" + temp[i][m];
				if(text.equals("X") || text.equals("O")){
					this.text[i][m].setText(text);
					this.text[i][m].setEnabled(false);
				}
			}
		}
		// TODO Auto-generated method stub
		
	}

	private void resetText(boolean enable) {
		for(int i = 0 ; i < theGame.size() ; i++){
			for(int m = 0 ; m < theGame.size() ; m++){
				text[i][m].setText(" _");
				text[i][m].setEnabled(enable);
			}
		}
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
}