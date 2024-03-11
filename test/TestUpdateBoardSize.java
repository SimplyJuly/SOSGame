package sprint3_1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3_1.product.TicTacToeGame;

public class TestUpdateBoardSize {

	private TicTacToeGame game;
	
	@Before
	public void setUp() throws Exception {
		game = new TicTacToeGame();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// acceptance criteria 1.1
	@Test
	public void test() {
		game.updateBoardSize(5);
		assertEquals("", 6, game.getTotalColumns());
		assertEquals("", 5, game.getTotalRows());
		
		game.updateBoardSize(8);
		assertEquals("", 9, game.getTotalColumns());
		assertEquals("", 8, game.getTotalRows());
	}

}
