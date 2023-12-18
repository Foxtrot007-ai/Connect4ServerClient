package Connect4ClientSource;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class Application extends JFrame{
	public int height = 600;
	public int width = 600;
	public GameManager game;
	
	//game state field
	public int buttonHeight = 48;
	public int buttonWidth = 48;
	public int buttonStartX = 100;
	public int buttonStartY = 200;
	public int buttonBorder = 2;
	public JButton[][] gameStateView = new JButton[6][7];
	
	public JLabel endInfo = new JLabel();
	public int labelHeight = 50;
	public int labelWidth = 400;
	public int labelStartX = 100;
	public int labelStartY = 100;
	
	public void disableAllButtons(boolean b) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				JButton button = gameStateView[i][j];
				if(button.getBackground() == Color.GRAY) {
					button.setEnabled(b);
					button.repaint();
				}
			}
		}
	}
	
	public void setEndInfoText(String text) {
		endInfo.setText(text);
	}
	
	public void instantiateEndInfo() {
		endInfo.setBounds(labelStartX, labelStartY, labelWidth, labelHeight);
	}
	
	public void instantiateGameStateField() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				JButton button = new JButton();
				button.setBounds(buttonStartX + j * (buttonWidth + buttonBorder),
						         buttonStartY + i * (buttonHeight + buttonBorder), 
						         buttonWidth, 
						         buttonHeight);
				button.setBackground(Color.GRAY);
				button.setEnabled(false);
				button.repaint();
				int y = i;
				int x = j;
				button.addActionListener(
					new AbstractAction("make Move") {
						@Override
						public void actionPerformed(ActionEvent e) {
							disableAllButtons(false);
							game.makeMove(y, x);
							game.buttonClicked = true;
						}
					}
				);
				gameStateView[i][j] = button;
			}
		}
	}
	
	public void resetGameStateView() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				JButton button = gameStateView[i][j];
				button.setBackground(Color.GRAY);
				button.repaint();
			}
		}
	}
	
	public void updateGameStateView(int y, int x, Color c) {
		JButton button = gameStateView[y][x];
		if(button.getBackground() == Color.GRAY) {
			button.setBackground(c);
			button.repaint();
		}
	}	
	
	public void addItemsToFrame() {
		this.add(endInfo);
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				this.add(gameStateView[i][j]);
			}
		}
	}
	
	public Application(GameManager g) {
		game = g;
		
		instantiateGameStateField();
		addItemsToFrame();
		
		this.setLayout(null);
		this.setSize(width, height); 
		this.setVisible(true);
	}
}  
