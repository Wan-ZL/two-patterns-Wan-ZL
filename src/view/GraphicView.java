package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ComputerPlayer;
import model.TicTacToeGame;
import controller.OurObserver;

public class GraphicView extends JPanel implements OurObserver, MouseListener{
	
	private TicTacToeGame theGame;
	private int width, height;
	private ComputerPlayer computerPlayer;
	private JPanel labelPanel;
	private JLabel[][] inputLabel = null;
	private Graphics2D g2;
	
	@Override
	public void paintComponent(Graphics g){
		
	}

	public GraphicView(TicTacToeGame TicTacToeGame, int width, int height){
		
		theGame = new TicTacToeGame();
		this.width = width;
		this.height = height;
		computerPlayer = theGame.getComputerPlayer();
	    initializeGraphicPanel();
	}

	private void initializeGraphicPanel() {
		labelPanel = new JPanel();
		//g2 = (Graphics2D)g;
		int size = theGame.size();
		inputLabel = new JLabel[size][size];
		labelPanel.setLayout(new GridLayout(size, size, 5, 5));
		Font font = new Font("Arial", Font.BOLD, 75);
		for(int i = 0 ; i < size ; i++){	// initialize Label[][]
			for(int m = 0 ; m < size ; m++){
				inputLabel[i][m] = new JLabel("    ");
				inputLabel[i][m].setFont(font);
				inputLabel[i][m].setBackground(Color.BLACK);
				labelPanel.add(inputLabel[i][m]);
			}
		}
		labelPanel.setBackground(Color.BLUE);
		labelPanel.setLocation(0,0);
		labelPanel.setSize(1000, 1000);
		this.add(labelPanel);
		this.addMouseListener(this);
		//g2.drawLine(100, 100, 200, 100);
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		updateText();
		// TODO Auto-generated method stub
		
	}
	
	private void updateText(){
		char[][] temp = theGame.getTicTacToeBoard();
		for(int i = 0 ; i < temp.length ; i++){
			for(int m = 0 ; m < temp[i].length ; m++){
				String text = " "+temp[i][m]+" ";
				if(text.equals(" X ") || text.equals(" O ")){
					this.inputLabel[i][m].setText(text);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		System.out.printf("x is %d, y is %d\n",x,y);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
