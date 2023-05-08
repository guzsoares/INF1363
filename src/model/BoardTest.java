package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		Board tab = new Board();
		fail("Not yet implemented");
	}
	
	@Test
	public void test_casas_iniciais() {
		Board tab = new Board();
		Square[] ini = tab.getInitialSquares();
		assertEquals("Verde não tem 3 peões na casa inicial",ini[0].numPawns(),3);
		assertEquals("Amarelo não tem 3 peões na casa inicial",ini[1].numPawns(),3);
		assertEquals("Azul não tem 3 peões na casa inicial",ini[2].numPawns(),3);
		assertEquals("Vermelho não tem 3 peões na casa inicial",ini[3].numPawns(),3);
		
	}
	
	@Test
	public void test_casas_saida() {
		Board tab = new Board();
		Square[] ini = tab.getSquares();
		assertEquals("Peão verde não está na casa de saída",ini[0].numPawns(),1);
		assertEquals("Amarelo não está na casa de saídanão tem 3 peões",ini[13].numPawns(),1);
		assertEquals("Azul não está na casa de saída",ini[26].numPawns(),1);
		assertEquals("Vermelho não está na casa de saídas",ini[39].numPawns(),1);
		
	}
	@Test
	public void test_end_game() {
		Board tab = new Board();
		Square[][] fin = tab.getFinalSquares();
		fin[0][5].addPawn(tab.getPawnOnIndex(0));
		fin[0][5].addPawn(tab.getPawnOnIndex(1));
		fin[0][5].addPawn(tab.getPawnOnIndex(2));
		fin[0][5].addPawn(tab.getPawnOnIndex(3));
		assertTrue("O verde não ganhou",tab.isGameOver());
		
	}
	
	@Test
	public void test_ranking() {
		Board tab = new Board();
		Square[][] fin = tab.getFinalSquares();
		fin[0][5].addPawn(tab.getPawnOnIndex(0));
		fin[0][5].addPawn(tab.getPawnOnIndex(1));
		fin[0][5].addPawn(tab.getPawnOnIndex(2));
		fin[1][5].addPawn(tab.getPawnOnIndex(6));
		fin[2][5].addPawn(tab.getPawnOnIndex(7));
		fin[2][5].addPawn(tab.getPawnOnIndex(9));
		Player[] aux = new Player[4];
		aux = tab.playersResults(tab.getPlayers());
		assertEquals("Verde fora do ranking",aux[0].getColor(),Color.VERDE);
		assertEquals("Amarelo fora do ranking",aux[2].getColor(),Color.AMARELO);
		assertEquals("Azul fora do ranking",aux[1].getColor(),Color.AZUL);
		assertEquals("Vermelho fora do ranking",aux[3].getColor(),Color.VERMELHO);
		
		
		
		
	}

}

