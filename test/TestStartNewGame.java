package sprint3_1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3_1.product.SimpleGame;

public class TestStartNewGame {
	
	private SimpleGame game;
	@Before
	public void setUp() throws Exception {
		game = new SimpleGame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// creates a 5x5 board with general game
		game.updateBoardSize(5);
		game.updateGameMode("General Game");
		assertEquals("", 6, game.getTotalColumns());
		assertEquals("", 5, game.getTotalRows());
		assertEquals("", "General Game", game.getGameMode());
		
		// creates a 8x8 board with simple game 
		game.updateBoardSize(8);
		game.updateGameMode("Simple Game");
		assertEquals("", 9, game.getTotalColumns());
		assertEquals("", 8, game.getTotalRows());
		assertEquals("", "Simple Game", game.getGameMode());
	}

}
