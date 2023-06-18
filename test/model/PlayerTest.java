package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	@Test
	public void test_canmove() {
		Game tab = new Game();
		tab.rollDie();
		assertFalse("Verde não tem 3 peões na casa inicial",tab.players[0].getNumChoices() == 0);	
	}
	@Test
	public void test_multiplemoves() {
		Game tab = new Game();
		for(int i = 0;i<8;i++) {
			tab.roll_x(5);
		}
		tab.roll_x(1);
		assertTrue("Verde não tem 3 peões na casa inicial",tab.players[0].getNumChoices() == 2);	
	}
	@Test
	public void test_punish() {
		Game tab = new Game();
		for(int i = 0;i<13;i++) {
			tab.roll_x(6);
		}
		assertTrue("Verde não tem 3 peões na casa inicial",tab.board.getInitialSquares()[0].getPawns().size() == 4);	
	}
	@Test
	public void test_play_again() {
		Game tab = new Game();
		tab.roll_x(6);
		assertTrue("Verde não tem 3 peões na casa inicial",tab.players[0].verifyChoices());	
	}
}
