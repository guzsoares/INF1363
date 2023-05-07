package model;

public class Teste {

    public static void main(String args[]){
        Board tabuleiro = new Board();

        tabuleiro.setPawnOnIndexPosition(1, 2);
    
        Die myDie = new Die();

        myDie.rollDie();

        System.out.println(myDie.getDieNumber());
    }
}


