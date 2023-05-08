package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

    @Test
    public void test() {
        Die dado = new Die();
        //50 testes para contornar fatores probabilisticos e ter o teste mais fidedigno.
        for(int i = 0; i< 50; i++) {
            dado.rollDie();
            int resultado = dado.getDieNumber();
            assertTrue("Numero inválido",resultado >= 1 && resultado <= 6);
        
        }
        
    }

}