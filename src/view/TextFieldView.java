import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.OurObserver;
import model.TicTacToeGame;

public class TextFieldView extends JPanel implements OurObserver{
	
	private TicTacToeGame theGame;
	private JTextArea stateText = new JTextArea("Emter Value to Move");
	
	public TextFieldView(TicTacToeGame TicTacToeGame, int width,int height){
		theGame = TicTacToeGame;
		
		
	}
}