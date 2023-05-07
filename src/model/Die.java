package model;
import java.util.Random;

class Die {
    private int valor;
    private Random random = new Random();
    
    public int rolar(){ //retorna valor 0 ate 6
    	valor = random.nextInt(5) +1;
    	return valor;
    }
    
    public int getValor() {
    	return valor;
    }
}