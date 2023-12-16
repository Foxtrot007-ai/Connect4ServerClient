package Connect4Source;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Application extends JFrame{
	public int height = 500;
	public int width = 1000;
	
	public String[] columnNames = {"Date", "Log text"};
	
	//game state field
	public int buttonHeight = 48;
	public int buttonWidth = 48;
	public int buttonStartX = 550;
	public int buttonStartY = 100;
	public int buttonBorder = 2;
	public JButton[][] gameStateView = new JButton[6][7];
	
	//time
	public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
	
	//log table
	public int tableHeight = 300;
	public int tableWidth = 400;
	public int tableStartX = 100;
	public int tableStartY = 100;
	public JTable tableToShow = new JTable();
	public DefaultTableModel logModel = new DefaultTableModel();
	public JScrollPane tableScroller = new JScrollPane(tableToShow);
	
	public void instantiateLogTable() {
		logModel.setColumnIdentifiers(columnNames);
		tableToShow.setModel(logModel);
		tableToShow.setBounds(tableStartX, tableStartY, tableWidth, tableHeight);
		tableScroller.setBounds(tableStartX, tableStartY, tableWidth, tableHeight);
	}
	
	public void addNewLog(String log){
		String[] row = {dtf.format(LocalDateTime.now()), log};
		logModel.addRow(row);
		tableToShow.repaint();
	}
	
	public void instantiateGameStateField() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				JButton button = new JButton();
				button.setBounds(buttonStartX + i * (buttonWidth + buttonBorder),
						         buttonStartY + j * (buttonHeight + buttonBorder), 
						         buttonWidth, 
						         buttonHeight);
				button.setBackground(Color.GRAY);
				button.repaint();
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
	
	public void updateGameStateView(int x, int y, Color c) {
		JButton button = gameStateView[x][y];
		button.setBackground(c);
		button.repaint();
	}
	
	public Application() {
		instantiateLogTable();
		instantiateGameStateField();
		
		this.setLayout(null);
		this.setSize(width, height); 
		this.setVisible(true);
	}
}  

