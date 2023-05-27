package model;

import controller.Subscriber;
import java.awt.Color;

public class ModelAPI {
    private static ModelAPI instance;
    private Game game;

    public ModelAPI(){
        instance = this;
    }
    
    public static ModelAPI getInstance() {
        if (instance == null) {
            instance = new ModelAPI();
        }
        return instance;
    }

    public void createGame(){
        game = new Game();
    }

    public void rollDie(){
        game.rollDie();

        game.playerTurn();
    }

    public void addSubscriber(Subscriber subscriber){
        game.addSubscriber(subscriber);
    }

    public int getDieNumber(){
        return game.getDieNumber();
    }

    public Color getCurrentPlayerColor(){
        return game.getCurrentPlayerColor();
    }

    public int[] getInitialSquaresNumPawns(){
        int[] numPawns = new int[4];

        numPawns[0] = game.board.getInitialSquares()[0].numPawns();
        numPawns[1] = game.board.getInitialSquares()[1].numPawns();
        numPawns[2] = game.board.getInitialSquares()[2].numPawns();
        numPawns[3] = game.board.getInitialSquares()[3].numPawns();
        return numPawns;
    }
}
