package Connect4Source;

import java.awt.Color;
import java.io.IOException;

public class GameManager {
	public int port;
	public Application app = new Application();
	public Connect4Game game = new Connect4Game(2);
	public ConnectionProvider con = new ConnectionProvider(2);
	public int winner = -1;
	
	public void playersConnectionEstablish() {
		try {
			con.startServer(port);
			app.addNewLog("server started");
		} catch (IOException e1) {
			app.addNewLog(e1.getMessage());
			app.addNewLog("server crashed");
		}
		
		try {
			con.connectClient(0);
			app.addNewLog("Player0 connected");
		} catch (IOException e) {
			app.addNewLog(e.getMessage());
			app.addNewLog("Player0 not connected");
		}
		
		try {
			con.connectClient(1);
			app.addNewLog("Player1 connected");
		} catch (IOException e) {
			app.addNewLog(e.getMessage());
			app.addNewLog("Player1 not connected");
		}
		
		app.addNewLog("Connections established");
	}
	
	public String askPlayer() {
		con.sendMessage("choose", game.playerTurn);
		app.addNewLog("Request sent to player " + game.playerTurn);
		String playerAnswer = "";
		
		try {
			do{
				app.addNewLog("Waiting for answer from player " + game.playerTurn);
				playerAnswer = con.getMessageFromClient(game.playerTurn);
			}while(!playerAnswer.contains("move:"));
			app.addNewLog("Got answer from player " + game.playerTurn);
		} catch (IOException e) {
			app.addNewLog(e.getMessage());
			app.addNewLog("Can't get answer from player " + game.playerTurn);
		}
		
		return playerAnswer;
	}
	public void sendChangeToClients(int y, int x, int index) {
		con.sendMessage("change:x/y", game.playerTurn);
		app.addNewLog("changeInfo sent to player " + index);
		try {
			do{
				app.addNewLog("Waiting for answer from player " + index);
			}while(!con.getMessageFromClient(index).contains("ok"));
			app.addNewLog("Got answer from player " + index);
		} catch (IOException e) {
			app.addNewLog(e.getMessage());
			app.addNewLog("Can't get answer from player " + index);
		}
	}
	
	public void makeMove(String move) {
		move.replaceFirst("move:", "");
		String[] pair = move.split("/");
		int y = Integer.parseInt(pair[0]);
		int x = Integer.parseInt(pair[1]);
		try {
			game.makeMove(y, x);
			sendChangeToClients(y,x,0);
			sendChangeToClients(y,x,1);
			app.updateGameStateView(x, y, (game.playerTurn == 0) ? Color.BLUE : Color.RED);
		} catch (Exception e) {
			app.addNewLog(e.getMessage());
			app.addNewLog("Can't make move");
		}
	}
	
	public void checkState() {
		try {
			winner = game.checkIfWin();
		} catch (Exception e) {
			app.addNewLog(e.getMessage());
		}
	}
	
	public void changeTurn() {
		game.changePlayer();
		app.addNewLog("Player Turn: " + game.playerTurn);
	}
	
	public void sendLastInfo() {
		con.sendMessage("winner:" + winner, 0);
		con.sendMessage("winner:" + winner, 1);
		app.addNewLog("Result sent");
	}
	
	public void gameLoop() {
		while(true) {
			String answer = askPlayer();
			makeMove(answer);
			checkState();
			if(winner != -1) {
				break;
			}
			changeTurn();
		}
	}
	
	public GameManager(int port) {
		this.port = port;
		
		app.addNewLog("Application Started");
		
		playersConnectionEstablish();
		
		gameLoop();
		sendLastInfo();
		
		try {
			con.stop();
			app.addNewLog("Connection Ended");
		} catch (IOException e) {
			app.addNewLog("Can't Stop it :O");
		}
		
		app.addNewLog("Exiting");
	}
}
