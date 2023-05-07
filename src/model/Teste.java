package model;

public class Teste {

    public static void main(String args[]){
        Board tabuleiro = new Board();

        tabuleiro.setPawnOnIndexPosition(1, 2);
    

        Square[] teste = tabuleiro.getSquares();
    
        for (int i = 0; i < tabuleiro.getSquares().length; i++){
            System.out.println(teste[i].getSquareType());
        }
    }
}


