package model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board tab = new Board();
		fail("Not yet implemented");
	}
	
	@Test
	public void barrier_check() {
		Board tab = new Board();
		Random random = new Random();
		Square[] quadrados = tab.getPlayableSquares();
		int rand = random.nextInt(51);
		if(rand == 51) {
			assertTrue(quadrados[0].isBarrier());
		}
		else {
			assertTrue(quadrados[rand+1].isBarrier());
		}
		
		
	}

}

