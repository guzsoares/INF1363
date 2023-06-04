package model;

class Board {
    private Pawn[] pawnsOnBoard = new Pawn[16];
    private Square[] playableSquares = new Square[52];
    private Square[][] finalSquares = new Square[4][6];
    private Square[] initialSquares = new Square[4];

    public Board(){
        setupBoard();
        setupPawns();
    }

    private void setupBoard(){

        // inicializa quadrados
        for (int i = 0; i < playableSquares.length; i++) {
            playableSquares[i] = new Square();
        }

        for (int i = 0; i < initialSquares.length; i++) {
            initialSquares[i] = new Square();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                finalSquares[i][j] = new Square();
            }
            finalSquares[i][5] = new Square();
        }

        for (int i = 0; i < playableSquares.length ; i++){
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
            pawnsOnBoard[i] = new Pawn(Color.VERDE, -1, i);
            initialSquares[0].addPawn(pawnsOnBoard[i]);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+4] = new Pawn(Color.AMARELO, -1, i);
            initialSquares[1].addPawn(pawnsOnBoard[i+4]);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+8] = new Pawn(Color.AZUL, -1, i);
            initialSquares[2].addPawn(pawnsOnBoard[i+8]);
        }
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[i+12] = new Pawn(Color.VERMELHO, -1, i);
            initialSquares[3].addPawn(pawnsOnBoard[i+12]);
        }
    }

    public void updatePawnsOnBoard(Player players[]){
        for (int i = 0; i < 4; i++){
            pawnsOnBoard[4*i] = players[i].getPlayerPawns()[0];
            pawnsOnBoard[4*i + 1] = players[i].getPlayerPawns()[1];
            pawnsOnBoard[4*i + 2] = players[i].getPlayerPawns()[2];
            pawnsOnBoard[4*i + 3] = players[i].getPlayerPawns()[3];
        }
    }
    
    private void setupPawns(){ // coloca 1 peão em cada casa de saída
        
        pawnsOnBoard[0].setPosition(0);
        pawnsOnBoard[0].addSteps(1);
        playableSquares[0].addPawn(pawnsOnBoard[0]);
        initialSquares[0].removePawn(pawnsOnBoard[0]);

        pawnsOnBoard[4].setPosition(13);
        pawnsOnBoard[4].addSteps(1);
        playableSquares[13].addPawn(pawnsOnBoard[4]);
        initialSquares[1].removePawn(pawnsOnBoard[4]);

        pawnsOnBoard[8].setPosition(26);
        pawnsOnBoard[8].addSteps(1);
        playableSquares[26].addPawn(pawnsOnBoard[8]);
        initialSquares[2].removePawn(pawnsOnBoard[8]);

        pawnsOnBoard[12].setPosition(39);
        pawnsOnBoard[12].addSteps(1);
        playableSquares[39].addPawn(pawnsOnBoard[12]);
        initialSquares[3].removePawn(pawnsOnBoard[12]);

    }

    public Pawn[] getPawnsOnBoard(){
        return this.pawnsOnBoard;
    }

    public Pawn getPawnOnIndex(int index){
        return this.pawnsOnBoard[index];
    }

    public Square[] getSquares(){
        return this.playableSquares;
    }

    public Square[] getInitialSquares(){
        return this.initialSquares;
    }

    public Square[][] getFinalSquares(){
        return this.finalSquares;
    }

    public void setPawnOnIndexPosition(int index, int position){
        getPawnOnIndex(index).setPosition(position);
    }

    public void setPawnOnIndexSquare(int index, Square[] boardSquares){
        getPawnOnIndex(index).setSquare(boardSquares);
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