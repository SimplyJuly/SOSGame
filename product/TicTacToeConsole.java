package sprint3_1.product;

import java.util.Scanner;
import sprint3_1.product.Game.Cell;
import sprint3_1.product.Game.GameState;

public class TicTacToeConsole {
	private Game game;

	public TicTacToeConsole(Game board) {
		this.game = board;
	}

	public void displayBoard() {
		for (int row = 0; row < game.getTotalRows(); row++) {
			System.out.println("-------");
			System.out.print("|" + symbol(game.getCell(row, 0)));
			System.out.print("|" + symbol(game.getCell(row, 1)));
			System.out.print("|" + symbol(game.getCell(row, 2)));
			System.out.println("|");
		}
		System.out.println("-------");
	}

	private char symbol(Cell cell) {
		if (cell == Cell.S)
			return 'X';
		else if (cell == Cell.NOUGHT)
			return 'O';
		else
			return ' ';
	}

	private boolean isOver() {
		GameState state = game.getGameState();
		if (state == GameState.PLAYING)
			return false;
		if (game.getGameState() == GameState.DRAW) {
			System.out.println("Draw!");
		} else if (game.getGameState() == GameState.S_WON) {
			System.out.println("X Won!");
		} else if (game.getGameState() == GameState.NOUGHT_WON) {
			System.out.println("O Won!");
		}
		return true;
	}

	public void play() {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		System.out.println("Welcome to the TicTacToe game!");
		while (!done) {
			int row, column;
			System.out.println("Current player: " + game.getTurn());
			System.out.print("Move at row: ");
			row = in.nextInt();
			System.out.print("Move at column: ");
			column = in.nextInt();
			if (row < 0 || row > game.getTotalRows() || column < 0 || column > game.getTotalColumns())
				System.out.println("Invalid move at (" + row + "," + column + ")");
			else {
				game.makeMove(row, column);
				displayBoard();
				done = isOver();
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		new TicTacToeConsole(new SimpleGame()).play();
	}
}
