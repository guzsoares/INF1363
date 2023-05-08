package model;

public class Teste {

    public static void main(String args[]){
<<<<<<< HEAD
       for (int i = 0; i < 4; i++){
        for (int j = 0; j < 4; j++){
        }
       }

       for (int j = 0; j < 4; j++){
        System.out.println(4*j);
        System.out.println(4*j + 1);
        System.out.println(4*j + 2);
        System.out.println(4*j + 3);
        }
}
=======
        Board tabuleiro = new Board();

        tabuleiro.setPawnOnIndexPosition(1, 2);
    

        Square[] teste = tabuleiro.getSquares();
    
        for (int i = 0; i < tabuleiro.getSquares().length; i++){
            System.out.println(teste[i].getSquareType());
        }
    }
>>>>>>> parent of 3c98abf (Novos testes)
}


