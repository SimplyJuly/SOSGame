package sprint3_1.product;

public abstract class Game {

	public enum Cell {EMPTY, S, NOUGHT}
	public enum GameState {PLAYING, DRAW, S_WON, NOUGHT_WON}
	
	protected int TOTALROWS = 3;
	protected int TOTALCOLUMNS = 3;

	protected Cell[][] grid;
	protected char turn;

	protected GameState currentGameState;
	private String gameMode = "Simple Game";

	public Game() {
		grid = new Cell[TOTALROWS][TOTALCOLUMNS];
		resetGame();
	}

	public void resetGame() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
		turn = 'S';
	}

	public int getTotalRows() {
		return TOTALROWS;
	}

	public int getTotalColumns() {
		return TOTALCOLUMNS;
	}
	
	public String getGameMode() {
		return gameMode;
	}
	
	public void updateGameMode(String updatedMode) {
		gameMode = updatedMode;
	}
	
	public void updateBoardSize(int size) {
		resetGame();
		TOTALROWS = size;
		TOTALCOLUMNS = size + 1;
		grid = new Cell[TOTALROWS][TOTALCOLUMNS];
		resetGame();
	}

	/**
	 * 
	 * @precond none
	 * @postcond returns Cell.EMPTY, Cell.CROSS, or Cell.NAUGHT 
	 * if row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMN
	 * otherwise null
	 * 
	 */
	public Cell getCell(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS) {
			return grid[row][column];
		} else {
			return null;
		}
	}

	public char getTurn() {
		return turn;
	}

	/**
	 * @precondiiton: none
	 * @postcondition if (row, column) is a valid empty cell, then the player's token 
	 *        has been placed in the cell and the turn has changed to the other player
	 *                
	 */
	public abstract void makeMove(int row, int column);
//	public void makeMove(int row, int column) {
//		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
//				&& grid[row][column] == Cell.EMPTY) {
//			grid[row][column] = (turn == 'S') ? Cell.S : Cell.NOUGHT;
//			updateGameState(turn, row, column); 
//			turn = (turn == 'S') ? 'O' : 'S'; 
//		}
//	}
//
	protected abstract void updateGameState(char turn, int row, int column);
//	private void updateGameState(char turn, int row, int column) {
//		if (hasWon(turn, row, column)) { 
//			currentGameState = (turn == 'S') ? GameState.S_WON : GameState.NOUGHT_WON;
//		} else if (isDraw()) {
//			currentGameState = GameState.DRAW;
//		}
//	}
//
	protected abstract boolean isDraw();
//	private boolean isDraw() {
//		for (int row = 0; row < TOTALROWS; ++row) {
//			for (int col = 0; col < TOTALCOLUMNS; ++col) {
//				if (grid[row][col] == Cell.EMPTY) {
//					return false; 
//				}
//			}
//		}
//		return true;
//	}
//
	protected abstract boolean hasWon(char turn, int row, int column);
//	private boolean hasWon(char turn, int row, int column) {
//		Cell token = (turn=='S')? Cell.S: Cell.NOUGHT;
//		return (grid[row][0] == token // 3-in-the-row
//				&& grid[row][1] == token && grid[row][2] == token
//				|| grid[0][column] == token // 3-in-the-column
//						&& grid[1][column] == token && grid[2][column] == token
//				|| row == column // 3-in-the-diagonal
//						&& grid[0][0] == token && grid[1][1] == token && grid[2][2] == token
//				|| row + column == 2 // 3-in-the-opposite-diagonal
//						&& grid[0][2] == token && grid[1][1] == token && grid[2][0] == token);
//	}

	public GameState getGameState() {
		return currentGameState;
	}

}