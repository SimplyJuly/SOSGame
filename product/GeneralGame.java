package sprint3_1.product;

import sprint3_1.product.Game.GameState;

//import sprint3_1.product.Game.Cell;
//import sprint3_1.product.Game.GameState;

public class GeneralGame extends Game {
	
	protected int sScore = 0;
	protected int oScore = 0;
	
	public void makeMove(int row, int column) {
		if (row >= 0 && row < TOTALROWS && column >= 0 && column < TOTALCOLUMNS
				&& grid[row][column] == Cell.EMPTY) {
			grid[row][column] = (turn == 'S') ? Cell.S : Cell.NOUGHT;
			updateGameState(turn, row, column); 
			turn = (turn == 'S') ? 'O' : 'S'; 
		}
	}

	protected void updateGameState(char turn, int row, int column) {
		if(isDraw()) {
			calculateScore();
			System.out.println("oScore: " + oScore + " sScore " + sScore);
			if(oScore > sScore) {
				System.out.println("oScore: won");
				currentGameState = GameState.NOUGHT_WON;
			}
			else if(sScore > oScore) {
				System.out.println("sScore won");
				currentGameState = GameState.S_WON;
			}
			else
				currentGameState = GameState.DRAW;
			oScore = 0;
			sScore = 0;
		}
	}

	protected boolean isDraw() {
		int COLUMNS = TOTALCOLUMNS > 3 ? TOTALCOLUMNS - 1 : TOTALCOLUMNS;
		System.out.println("isDraw in General been ran" + COLUMNS);
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < COLUMNS; ++col) {				
				System.out.println(row + " " + col);
				if (grid[row][col] == Cell.EMPTY) {
					return false; 
				}
			}
		}
		System.out.println("General was a draw!");
		return true;
	}
	
	protected void calculateScore() {
	    // Check rows
	    for (int row = 0; row < TOTALROWS; row++) {
	        for (int col = 0; col <= TOTALCOLUMNS - 3; col++) {
	            if (grid[row][col] == Cell.S && grid[row][col + 1] == Cell.S && grid[row][col + 2] == Cell.S) {
	                sScore++;
	            } else if (grid[row][col] == Cell.NOUGHT && grid[row][col + 1] == Cell.NOUGHT && grid[row][col + 2] == Cell.NOUGHT) {
	                oScore++;
	            }
	        }
	    }

	    // Check columns
	    for (int col = 0; col < TOTALCOLUMNS; col++) {
	        for (int row = 0; row <= TOTALROWS - 3; row++) {
	            if (grid[row][col] == Cell.S && grid[row + 1][col] == Cell.S && grid[row + 2][col] == Cell.S) {
	                sScore++;
	            } else if (grid[row][col] == Cell.NOUGHT && grid[row + 1][col] == Cell.NOUGHT && grid[row + 2][col] == Cell.NOUGHT) {
	                oScore++;
	            }
	        }
	    }

	    // Check diagonals
	    for (int row = 0; row <= TOTALROWS - 3; row++) {
	        for (int col = 0; col <= TOTALCOLUMNS - 3; col++) {
	            if (grid[row][col] == Cell.S && grid[row + 1][col + 1] == Cell.S && grid[row + 2][col + 2] == Cell.S) {
	                sScore++;
	            } else if (grid[row][col] == Cell.NOUGHT && grid[row + 1][col + 1] == Cell.NOUGHT && grid[row + 2][col + 2] == Cell.NOUGHT) {
	                oScore++;
	            }
	        }
	    }

	    for (int row = 0; row <= TOTALROWS - 3; row++) {
	        for (int col = 2; col < TOTALCOLUMNS; col++) {
	            if (grid[row][col] == Cell.S && grid[row + 1][col - 1] == Cell.S && grid[row + 2][col - 2] == Cell.S) {
	                sScore++;
	            } else if (grid[row][col] == Cell.NOUGHT && grid[row + 1][col - 1] == Cell.NOUGHT && grid[row + 2][col - 2] == Cell.NOUGHT) {
	                oScore++;
	            }
	        }
	    }
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

//	private String gameMode = "General Game";
}
