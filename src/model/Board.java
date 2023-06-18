package model;

import java.io.Serializable;

class Board implements Serializable {
	private static final long serialVersionUID = 1L;
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

    public void removeAllPawns(){
        initialSquares[0].removePawn(pawnsOnBoard[1]);
        initialSquares[0].removePawn(pawnsOnBoard[2]);
        initialSquares[0].removePawn(pawnsOnBoard[3]);

        initialSquares[1].removePawn(pawnsOnBoard[5]);
        initialSquares[1].removePawn(pawnsOnBoard[6]);
        initialSquares[1].removePawn(pawnsOnBoard[7]);

        initialSquares[2].removePawn(pawnsOnBoard[9]);
        initialSquares[2].removePawn(pawnsOnBoard[10]);
        initialSquares[2].removePawn(pawnsOnBoard[11]);

        initialSquares[3].removePawn(pawnsOnBoard[13]);
        initialSquares[3].removePawn(pawnsOnBoard[14]);
        initialSquares[3].removePawn(pawnsOnBoard[15]);

        playableSquares[0].removePawn(pawnsOnBoard[0]);
        playableSquares[13].removePawn(pawnsOnBoard[4]);
        playableSquares[26].removePawn(pawnsOnBoard[8]);
        playableSquares[39].removePawn(pawnsOnBoard[12]);
    }

    public void reloadPawns(){
        for (int i = 0; i < 16; i++){
            if (pawnsOnBoard[i].getPosition() < 0){
                if (i < 4 && pawnsOnBoard[i].getPosition() == -1){
                    initialSquares[0].addPawn(pawnsOnBoard[i]);
                } else if (i < 8 && pawnsOnBoard[i].getPosition() == -1){
                    initialSquares[1].addPawn(pawnsOnBoard[i]);
                }else if (i < 12 && pawnsOnBoard[i].getPosition() == -1){
                    initialSquares[2].addPawn(pawnsOnBoard[i]);
                }else if (i < 16 && pawnsOnBoard[i].getPosition() == -1){
                    initialSquares[3].addPawn(pawnsOnBoard[i]);
                }
            } else if (pawnsOnBoard[i].getPosition() >= 0 && pawnsOnBoard[i].getPosition() <= 51){
                playableSquares[pawnsOnBoard[i].getPosition()].addPawn(pawnsOnBoard[i]);
            } else if (pawnsOnBoard[i].getPosition() > 99){

                if (pawnsOnBoard[i].getPosition() >= 100 && pawnsOnBoard[i].getPosition() <= 105){
                    int newPos = pawnsOnBoard[i].getPosition() - 100;
                    finalSquares[0][newPos].addPawn(pawnsOnBoard[i]);
                }

                else if (pawnsOnBoard[i].getPosition() >= 200 && pawnsOnBoard[i].getPosition() <= 205){
                    int newPos = pawnsOnBoard[i].getPosition() - 200;
                    finalSquares[1][newPos].addPawn(pawnsOnBoard[i]);
                }

                else if (pawnsOnBoard[i].getPosition() >= 300 && pawnsOnBoard[i].getPosition() <= 305){
                    int newPos = pawnsOnBoard[i].getPosition() - 300;
                    finalSquares[2][newPos].addPawn(pawnsOnBoard[i]);
                }

                else if (pawnsOnBoard[i].getPosition() >= 400 && pawnsOnBoard[i].getPosition() <= 405){
                    int newPos = pawnsOnBoard[i].getPosition() - 400;
                    finalSquares[3][newPos].addPawn(pawnsOnBoard[i]);
                }
            }
        }
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