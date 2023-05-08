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
		assertFalse(test.isBarrier());
		
	}
	@Test
	public void test_common_barrier(){
		Square test = new Square();
		Pawn um = new Pawn(Color.VERMELHO, 22);
		Pawn dois = new Pawn(Color.VERMELHO, 22);
		test.setSquareType(SquareType.cComum);
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue(test.isBarrier());
		
	}
	@Test
	public void test_common_barrier_2(){
		Square test = new Square();
		Pawn um = new Pawn(Color.VERMELHO, 22);
		Pawn dois = new Pawn(Color.VERDE, 21);
		test.setSquareType(SquareType.cComum);
		test.addPawn(um);
		test.addPawn(dois);
		assertFalse(test.isBarrier());
		
	}
	
	@Test
	public void test_abrigo_barrier(){
		Square test = new Square();
		Pawn um = new Pawn(Color.VERMELHO, 22);
		Pawn dois = new Pawn(Color.VERMELHO, 22);
		test.setSquareType(SquareType.cAbrigo);
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue(test.isBarrier());
		
	}
	
	@Test
	public void test_abrigo_barrier_2(){
		Square test = new Square();
		Pawn um = new Pawn(Color.VERMELHO, 22);
		Pawn dois = new Pawn(Color.VERDE, 21);
		test.setSquareType(SquareType.cAbrigo);
		test.addPawn(um);
		test.addPawn(dois);
		assertTrue(test.isBarrier());
	}
	
}
