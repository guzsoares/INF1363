package model;

import java.util.ArrayList;
import java.util.List;
import model.Square;

class Board {
    private Pawn[] pawnsOnBoard = new Pawn[16];
    private Square[] playableSquares = new Square[52];
    private Square[][] finalSquares = new Square[4][6];
    private Square[] initialSquares = new Square[4];

    public Board(){
        for (int i = 0; i < playableSquares.length; i++){
            playableSquares[i].setSquareType(SquareType.cComum);
        }

        // colocando os abrigos
        playableSquares[48].setSquareType(SquareType.cAbrigo);
        playableSquares[9].setSquareType(SquareType.cAbrigo);
        playableSquares[22].setSquareType(SquareType.cAbrigo);
        playableSquares[35].setSquareType(SquareType.cAbrigo);

        // colocando as saidas
        playableSquares[39].setSquareType(SquareType.cSaida);
        playableSquares[26].setSquareType(SquareType.cSaida);
        playableSquares[0].setSquareType(SquareType.cSaida);
        playableSquares[13].setSquareType(SquareType.cSaida);

        // definindo as cores das saidas
        playableSquares[39].setSquareColor(Color.VERMELHO);
        playableSquares[26].setSquareColor(Color.AZUL);
        playableSquares[0].setSquareColor(Color.VERDE);
        playableSquares[13].setSquareColor(Color.AMARELO);

        // definindo retas finais e quadrados iniciais
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                finalSquares[i][j].setSquareType(SquareType.rFinal);
            }
            finalSquares[i][5].setSquareType(SquareType.cFinal);
            initialSquares[i].setSquareType(SquareType.cInicial);
        }

        // definindo cores de cada spawn

        initialSquares[0].setSquareColor(Color.VERDE);
        initialSquares[1].setSquareColor(Color.AMARELO);
        initialSquares[2].setSquareColor(Color.AZUL);
        initialSquares[3].setSquareColor(Color.VERMELHO);

        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i] = new Pawn(Color.VERDE, -1);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+4] = new Pawn(Color.AMARELO, -1);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+8] = new Pawn(Color.AZUL, -1);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+12] = new Pawn(Color.VERMELHO, -1);
        }


    }
}


/*
-------------------------------------------------------------------------------------------
|  /  |  /  |  /  |  /  |  /  |  /  |  50 |  51 |  52 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  49 |  F  |  1  |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  48 |  F  |  2  |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  47 |  F  |  3  |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  46 |  F  |  4  |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  45 |  F  |  5  |  /  |  /  |  /  |  /  |  /  |  /  |
|  39 |  40 |  41 |  42 |  43 |  44 |  E  |  E  |  E  |  6  |  7  |  8  |  9  |  10 |  11 |
|  38 |  F  |  F  |  F  |  F  |  F  |  E  |  E  |  E  |  F  |  F  |  F  |  F  |  F  |  12 |
|  37 |  36 |  35 |  34 |  33 |  32 |  E  |  E  |  E  |  18 |  17 |  16 |  15 |  14 |  13 |
|  /  |  /  |  /  |  /  |  /  |  /  |  31 |  F  |  19 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  30 |  F  |  20 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  29 |  F  |  21 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  28 |  F  |  22 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  27 |  F  |  23 |  /  |  /  |  /  |  /  |  /  |  /  |
|  /  |  /  |  /  |  /  |  /  |  /  |  26 |  25 |  24 |  /  |  /  |  /  |  /  |  /  |  /  |
-------------------------------------------------------------------------------------------

DIREITA CIMA-->VERDE
DIREITA BAIXO -->AMARELO

ESQUERDA CIMA--> VERMELHO


ESQUERDA BAIXO -->AZUL
*/