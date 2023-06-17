package model;

import java.io.Serializable;
import java.util.Random;

class Die implements Serializable {
	private static final long serialVersionUID = 1L;
    private int dieNumber;
    private Random random = new Random();
    
    public void rollDie(){ //retorna valor 0 ate 6
    	this.dieNumber = random.nextInt(6) +1;
    }
    
    public int getDieNumber() {
    	return dieNumber;
    }

    public void setDieNumber(int num){
        this.dieNumber = num;
    }
    
}