package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class PawnTest {
	
	@Test
	public void test_movement_1() {//movement
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.getPawnOnIndex(0).movePawn(5,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Não chegou no destino",tab.getPawnOnIndex(0).getPosition(),17);
	}
	@Test
	public void test_movement_2() {//movement
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.getPawnOnIndex(0).movePawn(5,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Não há peão no destino",tab.getSquares()[17].getPawns().size(),1);
	}
	@Test
	public void test_capture_1() {//parou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(2,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Não capturou",tab.getSquares()[12].getPawns().size(),1);
	}
	@Test
	public void test_capture_2() {//parou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(2,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Não voltou pra casa inicial",tab.getPawnOnIndex(0).getPosition(),-1);
	}
	@Test
	public void test_capture_3() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(4,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Capturou",tab.getSquares()[12].getPawns().size(),1);
	}
	@Test
	public void test_capture_4() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(4,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertNotSame("Voltou pra casa inicial",tab.getPawnOnIndex(0).getPosition(),-1);
	}
	@Test
	public void test_capture_5() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(5,10);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		tab.getPawnOnIndex(5).movePawn(4,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard(), tab.getInitialSquares());
		assertEquals("Não chegou",tab.getSquares()[14].getPawns().size(),1);
	}
	@Test
	public void test_barrier_block_1() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(1,12);
		tab.setPawnOnIndexPosition(5,9);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(1,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		assertFalse("Ele pode se mover",tab.getPawnOnIndex(5).canMove(5,tab.getPawnsOnBoard(),tab.getSquares()));
	}
	@Test
	public void test_barrier_block_2() {//passou no quadrado
		Board tab = new Board();
		tab.setPawnOnIndexPosition(0,12);
		tab.setPawnOnIndexPosition(1,12);
		tab.setPawnOnIndexPosition(5,9);
		tab.setPawnOnIndexSquare(0,tab.getSquares());
		tab.setPawnOnIndexSquare(1,tab.getSquares());
		tab.setPawnOnIndexSquare(5,tab.getSquares());
		assertEquals("Não permaneceu na primeira casa",tab.getPawnOnIndex(5).getPosition(),9);
	}
	
}

