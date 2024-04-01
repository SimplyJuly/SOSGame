package sprint3_1.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sprint3_1.product.SimpleGame;
import sprint3_1.product.Game.Cell;

public class TestNoughtMoves {

	private SimpleGame game;

	@Before
	public void setUp() throws Exception {
		game = new SimpleGame();
		game.makeMove(1, 1);	// CROSS move, then it is NOUGHT's turn
	}

	// acceptance criterion 3.1
	@Test
	public void testNoughtTurnMoveVacantCell() {
		game.makeMove(0, 0); // NOUGHT move
		assertEquals("", game.getCell(0, 0), Cell.NOUGHT);
		assertEquals("", game.getTurn(), 'S');
	}

	// acceptance criterion 3.2
	@Test
	public void testNoughtTurnMoveNonVacantCell() {
		game.makeMove(0, 0); // NOUGHT move
		game.makeMove(1, 0); // CROSS move
		assertEquals("", game.getTurn(), 'O');
		game.makeMove(1, 0); // invalid NOUGHT move
		assertEquals("", game.getTurn(), 'O');
	}

	// acceptance criterion 3.3
	@Test
	public void testNoughtTurnInvalidRowMove() {
		game.makeMove(4, 0); 
		assertEquals("", game.getTurn(), 'O');
	}
	
	// acceptance criterion 3.3
	@Test
	public void testNoughtTurnInvalidColumnMove() {
		game.makeMove(0, 4); 
		assertEquals("", game.getTurn(), 'O');
	}

}
