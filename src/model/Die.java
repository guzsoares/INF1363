package model;

import java.util.Random;

class Die {
    private int dieNumber;
    private Random random = new Random();
    
    public void rollDie(){ //retorna valor 0 ate 6
    	this.dieNumber = random.nextInt(5) +1;
    }
    
    public int getDieNumber() {
    	return dieNumber;
    }

    public void setDieNumber(int num){
        this.dieNumber = num;
    }
}