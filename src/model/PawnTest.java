package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class PawnTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	

	@Test
	public void test_movement() {//movement
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.getPawnOnIndex(0).movePawn(5,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard());
		assertEquals("Não chegou no destino",tab.getPawnOnIndex(0).getPosition(),17);
		assertEquals("Não há peão no destino",tab.getSquares()[17].getPawns().size(),1);
	}

	@Test
	public void test_capture_1() {//parou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(2,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard());
		assertEquals("Não capturou",tab.getSquares()[12].getPawns().size(),1);
		assertEquals("Não voltou pra casa inicial",tab.getPawnOnIndex(0).getPosition(),-1);
	}
	@Test
	public void test_capture_2() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(4,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard());
		assertEquals("Capturou",tab.getSquares()[12].getPawns().size(),1);
		assertNotSame("Voltou pra casa inicial",tab.getPawnOnIndex(0).getPosition(),-1);
		assertEquals("Não chegou",tab.getSquares()[14].getPawns().size(),1);
	}
	@Test
	public void test_barrier_block() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(1,12);
		tab.setPawnOnIndexPosition(5,9);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(1,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		if(tab.getPawnOnIndex(5).canMove(5,tab.getPawnsOnBoard(),tab.getSquares())) {
			tab.getPawnOnIndex(5).movePawn(5,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard());
		}
		assertTrue("Ele pode se mover",tab.getPawnOnIndex(5).canMove(5,tab.getPawnsOnBoard(),tab.getSquares()));
		assertNotSame("Não permaneceu na primeira casa",tab.getPawnOnIndex(0).getPosition(),9);
	}
	
}

