package model;

import static org.junit.Assert.*;


import org.junit.Test;

public class BoardTest {
	
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

}

