package model;

public class Teste {

    public static void main(String args[]){
        Board tabuleiro = new Board();

        tabuleiro.setPawnOnIndexPosition(1, 0);
    
        boolean teste = tabuleiro.getPawnOnIndex(0).canMove(5, tabuleiro.getPawnsOnBoard(), tabuleiro.getSquares());
    
        System.out.println(teste);
    }
}


