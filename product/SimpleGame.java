package sprint3_1.product;
//
//import sprint3_1.product.Game.Cell;
//import sprint3_1.product.Game.GameState;

public class SimpleGame extends Game {
	
	public void makeMove(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
				&& grid[row][column] == Cell.EMPTY) {
			grid[row][column] = (turn == 'S') ? Cell.S : Cell.NOUGHT;
			updateGameState(turn, row, column); 
			turn = (turn == 'S') ? 'O' : 'S'; 
		}
	}

	protected void updateGameState(char turn, int row, int column) {
		if (hasWon(turn, row, column)) { 
			currentGameState = (turn == 'S') ? GameState.S_WON : GameState.NOUGHT_WON;
		} else if (isDraw()) {
			currentGameState = GameState.DRAW;
		}
	}

	protected boolean isDraw() {
		System.out.println("isDraw has been ran");
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					return false; 
				}
			}
		}
		return true;
	}

	protected boolean hasWon(char turn, int row, int column) {
		Cell token = (turn=='S')? Cell.S: Cell.NOUGHT;
		return (grid[row][0] == token // 3-in-the-row
				&& grid[row][1] == token && grid[row][2] == token
				|| grid[0][column] == token // 3-in-the-column
						&& grid[1][column] == token && grid[2][column] == token
				|| row == column // 3-in-the-diagonal
						&& grid[0][0] == token && grid[1][1] == token && grid[2][2] == token
				|| row + column == 2 // 3-in-the-opposite-diagonal
						&& grid[0][2] == token && grid[1][1] == token && grid[2][0] == token);
	}

//	private String gameMode = "Simple Game";
}
