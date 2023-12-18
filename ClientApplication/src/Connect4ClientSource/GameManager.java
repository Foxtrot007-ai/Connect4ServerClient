package Connect4ClientSource;

import java.awt.Color;
import java.io.IOException;

public class GameManager {
	
	public boolean buttonClicked;
	public Application app;
	public ConnectionProvider con;
	
	public void makeMove(int y, int x) {
		con.sendMessage("move:" + y + "/" + x);
	}
	
	public void waitForMove() {
		app.disableAllButtons(true);
		buttonClicked = false;
		while(!buttonClicked) {}
	}
	
	public void updateView(String move) {
		move.replaceFirst("move:", "");
		String[] tuple = move.split("/");
		int y = Integer.parseInt(tuple[0]);
		int x = Integer.parseInt(tuple[1]);
		int c = Integer.parseInt(tuple[2]);
		app.updateGameStateView(y, x, (c == 0) ? Color.RED : Color.BLUE);
	}
	
	
	public void gameLoop() {
		boolean looping = true;
		String result;
		while(looping) {
			result = "";
			try {
				result = con.getMessageFromServer();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			if(result.contains("move:")) {
				updateView(result);
			}
			
			if(result.contains("choose")) {
				con.sendMessage("ok");
				waitForMove();
			}
			
			if(result.contains("winner")) {
				app.setEndInfoText("You won :)");
			}
			
			if(result.contains("loser")) {
				app.setEndInfoText("You lost :(");
			}
		}
	}
	
	public GameManager() {
		app = new Application(this);
		con = new ConnectionProvider("127.0.0.1", 6666); //localhost
		boolean connectionMade = true;
		
		try {
			con.connectServer();
		} catch (IOException e) {
			connectionMade = false;
		}
			
		if(connectionMade) {
			gameLoop();
		}
		
		try {
			con.stop();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
