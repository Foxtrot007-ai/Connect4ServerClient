package Connect4Source;

public class Connect4Game {
	private int height = 6;
	private int width = 7;
	public int maxPlayers;
	public int[][] field;
	public int playerTurn;
	
	public void startGame(int turn) {
		resetField();
		playerTurn = turn;
	}
	
	private void resetField() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				field[i][j] = -1;
			}
		}
	}
	
	public void makeMove(int y, int x) throws Exception{
		if(field[y][x] != -1) {
			throw new Exception("pointTaken");
		}
		
		if(y != 5) {
			if(field[y + 1][x] == -1) {
				throw new Exception("invalidPoint");
			}
		}
		
		field[y][x] = playerTurn;
	}
	
	public void changePlayer() {
		playerTurn++;
		if(playerTurn >= maxPlayers) {
			playerTurn = 0;
		}
	}
	
	public int checkIfWin() throws Exception{
		//check rows
		for(int i = 0; i < height; i++) {
			for(int j = 0; j <= width - 4; j++) {
				if(field[i][j] != -1
				&& field[i][j] == field[i][j+1]
				&& field[i][j+1] == field[i][j+2]
				&& field[i][j+2] == field[i][j+3]) {
					return field[i][j];
				}
			}
		}
		
		//check columns
		for(int i = 0; i <= height - 4; i++) {
			for(int j = 0; j < width; j++) {
				if(field[i][j] != -1
				&& field[i][j] == field[i+1][j]
				&& field[i+1][j] == field[i+2][j]
				&& field[i+2][j] == field[i+3][j]) {
					return field[i][j];
				}
			}
		}
		
		//check diagonalLeft
		for(int i = 0; i <= height - 4; i++) {
			for(int j = 0; j <= width - 4; j++) {
				if(field[i][j] != -1
				&& field[i][j] == field[i+1][j+1]
				&& field[i+1][j+1] == field[i+2][j+2]
				&& field[i+2][j+2] == field[i+3][j+3]) {
					return field[i][j];
				}
			}
		}
		
		//check diagonalRight
		for(int i = 0; i <= height - 4; i++) {
			for(int j = width - 1; j >= width - 4; j--) {
				if(field[i][j] != -1
				&& field[i][j] == field[i+1][j-1]
				&& field[i+1][j-1] == field[i+2][j-2]
				&& field[i+2][j-2] == field[i+3][j-3]) {
					return field[i][j];
				}
			}
		}
		
		throw new Exception("not win state");
	}
	
	public Connect4Game(int max) {
		maxPlayers = max;
		playerTurn = 0;
		field = new int[height][width];
	}
}
