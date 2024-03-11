package sprint3_1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3_1.product.TicTacToeGame;

public class TestUpdateGameMode {
	
	private TicTacToeGame game;

	@Before
	public void setUp() throws Exception {
		game = new TicTacToeGame();
	}

	@After
	public void tearDown() throws Exception {
	}

	//acceptance criteria 2.1
	@Test
	public void test() {
		//ensuring that the default is the simple game
		assertEquals("", "Simple Game", game.getGameMode());
		
		//changing game mode to general game
		game.updateGameMode("General Game");
		assertEquals("", "General Game", game.getGameMode());
		
		//changing game mode back to simple game
		game.updateGameMode("Simple Game");
		assertEquals("", "Simple Game", game.getGameMode());
		
	}

}
