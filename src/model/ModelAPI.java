package model;

import controller.MenuSubscriber;
import controller.BoardSubscriber;
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
    }
    
    public void DebugRoll(int x) {
    	game.roll_x(x);
    }
    public boolean isGameOver(){
        return game.getGameOver();
    }

    public void addSubscriber(MenuSubscriber subscriber){
        game.addSubscriber(subscriber);
    }

    public void addBoardSubscriber(BoardSubscriber subscriber){
        game.addBoardSubscriber(subscriber);
    }

    public int getDieNumber(){
        return game.getDieNumber();
    }

    public Color getCurrentPlayerRealColor(){
        return game.getCurrentPlayerRealColor();
    }

    public String getCurrentPlayerColor(){

        switch (game.getPlayers()[game.getTurn()].getColor()){

            case VERDE:
            return "Verde";

            case AZUL:
            return "Azul";

            case VERMELHO:
            return "Vermelho";

            case AMARELO:
            return "Amarelo";
            
            default:
            return "Erro";
        }
    }

    public int[] getInitialSquaresNumPawns(){
        int[] numPawns = new int[4];

        numPawns[0] = game.board.getInitialSquares()[0].numPawns();
        numPawns[1] = game.board.getInitialSquares()[1].numPawns();
        numPawns[2] = game.board.getInitialSquares()[2].numPawns();
        numPawns[3] = game.board.getInitialSquares()[3].numPawns();
        return numPawns;
    }

    public int[] pawnsPosition(){
        int[] pawnsPosition = new int[16];
        for (int i = 0; i < 16; i++){
            pawnsPosition[i] = game.getGameBoard().getPawnsOnBoard()[i].getPosition();
        }
        return pawnsPosition;
    }

    public Color[] pawnsColor(){
        Color[] pawnsColor = new Color[16];
        for (int i = 0; i < 16; i++){
            switch (game.getGameBoard().getPawnsOnBoard()[i].getColor()){

            case VERDE:
            pawnsColor[i] = Color.GREEN;
            break;

            case AZUL:
            pawnsColor[i] = Color.BLUE;
            break;

            case VERMELHO:
            pawnsColor[i] = Color.RED;
            break;

            case AMARELO:
            pawnsColor[i] = Color.YELLOW;
            break;
            
            default:
            System.out.print("Erro");
            }
        }
        return pawnsColor;
    }
    public void handleClick(int squareClicked) {
    	game.handleClick(squareClicked);
    }

    public int getPlays(){
        return game.getPlays();
    }

     public boolean getPlaying(){
        return game.getPlaying();
    }

    public boolean getDebug() {
    	return game.getDEBUG();
    }
    public Game getGame() {
    	return game;
    }
    public int getTurn() {
    	return game.getTurn();
    }
    public void setTurn(int turno) {
    	game.setTurn(turno);
    }

    public void removeAllPawns(){
        game.getGameBoard().removeAllPawns();
    }

    public void reloadPawns(){
        game.updateInfo();
        game.getGameBoard().reloadPawns();
    }
    public int getPlayersPawnsPosition(int i,int j) {
    	return game.getPlayers()[i].getPlayerPawns()[j].getPosition();
    }
    public void setPlayersPawnsPosition(int i, int j,int pos) {
    	game.getPlayers()[i].getPlayerPawns()[j].setPosition(pos);
    }
    public int getPlayersPawnsSteps(int i, int j) {
    	return game.getPlayers()[i].getPlayerPawns()[j].getSteps();
    }
    public void setPlayersPawnsSteps(int i, int j,int steps) {
    	game.getPlayers()[i].getPlayerPawns()[j].setSteps(steps);;
    }
    public void setGame(Object g) {
    	game = (Game) g;
    }
  
}
