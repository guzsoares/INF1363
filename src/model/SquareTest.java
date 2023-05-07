package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	

	@Test
	public void test_saida_barrier(){
		Square test = new Square();
		Pawn um = new Pawn(Color.VERMELHO, 0);
		Pawn dois = new Pawn(Color.VERMELHO, 0);
		test.setSquareType(SquareType.cSaida);
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue(test.isBarrier());
		
	}
	
}
