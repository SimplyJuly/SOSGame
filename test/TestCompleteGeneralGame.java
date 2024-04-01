package sprint3_1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3_1.product.TicTacToeGUI;
import sprint3_1.product.GeneralGame;
import sprint3_1.product.Game.GameState;

public class TestCompleteGeneralGame {

	private GeneralGame game;
	@Before
	public void setUp() throws Exception {
		game = new GeneralGame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		game.makeMove(0, 0);
		game.makeMove(1, 1);		
		game.makeMove(0, 1);
		game.makeMove(1, 0);		
		game.makeMove(0, 2);
		assertEquals("", game.getGameState(), GameState.S_WON); 
		new TicTacToeGUI(game); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
