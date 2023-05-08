package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class PawnTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test_breakbarrier() {
		Board tab = new Board();
		Pawn[] pawns = tab.getPawnsOnBoard();
		Square[] quadrados = tab.getSquares();
		Pawn um = pawns[0];
		Pawn dois = pawns[1];
		Square test = quadrados[22];
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue("Peão 1 da barreira não pode mexer",um.canMove(6,pawns,quadrados));
		assertTrue("Peão 2 da barreira não pode mexer",dois.canMove(6,pawns,quadrados));
		assertFalse("Peão 1 fora pode mexer",pawns[2].canMove(6,pawns,quadrados));
		assertFalse("Peão 2 fora pode mexer",pawns[3].canMove(6,pawns,quadrados));

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
		tab.getPawnOnIndex(5).capturePawn(2,tab.getSquares(),tab.getInitialSquares());
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
		tab.getPawnOnIndex(5).capturePawn(4,tab.getSquares(),tab.getInitialSquares());
		tab.getPawnOnIndex(5).movePawn(4,tab.getSquares(),tab.getFinalSquares() ,tab.getPawnsOnBoard());
		assertEquals("Não capturou",tab.getSquares()[12].getPawns().size(),1);
		assertNotSame("Não voltou pra casa inicial",tab.getPawnOnIndex(0).getPosition(),-1);
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

