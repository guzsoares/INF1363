package model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class PawnTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test_breakbarrier() {
		Board tab = new Board();
		Pawn[] pawns = tab.getPawnsonBoard();
		Random random = new Random();
		Square[] quadrados = tab.getPlayableSquares();
		int rand = random.nextInt(51);//quadrado aleatório comum ou abrigo
		int rand2 = random.nextInt(3);//escolhe uma cor de peão
		Pawn um = pawns[(4*rand2)];
		Pawn dois = pawns[(4*rand2)+1];
		Square test;
		SquareType aux = quadrados[rand].getSquaretype(quadrados[rand]);
		if(aux == SquareType.cComum) {
			test = quadrados[rand];
		}
		else {
			rand++;
			test = quadrados[rand];
		}
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue(um.canMove(6,pawns,quadrados));
		assertTrue(dois.canMove(6,pawns,quadrados));
		assertFalse(pawns[(4*rand2)+2].canMove(6,pawns,quadrados));
		assertFalse(pawns[(4*rand2)+3].canMove(6,pawns,quadrados));

	}

	@Test
	public void test_capture() {
		Board tab = new Board();
		Square[] quadrados = tab.getPlayableSquares();
		
		Pawn um = new Pawn(VERDE,12);
		Pawn dois = new Pawn(AMARELO,10);
		
	}

}
