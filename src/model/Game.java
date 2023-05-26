package model;

import controller.AbstractPublisher;
import java.awt.Color;

class Game extends AbstractPublisher{
    public Player[] players;
    public Die die;
    public Board board;
    public int turn = 0;
    private int plays = 0;


    public Game(){
        players = new Player[4];
        die = new Die();
        board = new Board();

        for (int i = 0; i < 4; i++){
            Pawn[] playersPawns = new Pawn[4];
            playersPawns[0] = board.getPawnOnIndex(4*i);
            playersPawns[1] = board.getPawnOnIndex(4*i + 1);
            playersPawns[2] = board.getPawnOnIndex(4*i + 2);
            playersPawns[3] = board.getPawnOnIndex(4*i + 3);
            switch(i) {
            	case(0):
            		players[i] = new Player(model.Color.VERDE, playersPawns);
            		break;
            	case(1):
            		players[i] = new Player(model.Color.AMARELO, playersPawns);
            		break;
            	case(2):
            		players[i] = new Player(model.Color.AZUL, playersPawns);
            		break;
            	case(3):
            		players[i] = new Player(model.Color.VERMELHO, playersPawns);
            		break;
            }
        }
    }

    public void rollDie(){
        die.rollDie();
        notifySubscribersDie(getDieNumber());
    }

    public int getDieNumber(){
        return die.getDieNumber();
    }

    public boolean isGameOver(){
        for (int i = 0; i < 4; i++){
            if(board.getFinalSquares()[i][5].numPawns() == 4){
                return true;
            }
        }
        return false;
    }

    public Player[] playersResults(Player[] players){
        Player[] ranking = new Player[4];

        ranking = players;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3 - i; j++) {
                if (ranking[j].stepsCount() < ranking[j + 1].stepsCount()) {
                    Player temp = ranking[j];
                    ranking[j] = ranking[j + 1];
                    ranking[j + 1] = temp;
                }
            }
        }
        return ranking;
    }

    public Board getGameBoard(){
        return board;
    }

    public Player[] getPlayers(){
        return players;
    }

    public void updateInfo(){
        board.updatePawnsOnBoard(players);
    }

    public Color getCurrentPlayerColor(){
        switch(players[turn].getColor()){
            case VERDE:
            return Color.GREEN;

            case AZUL:
            return Color.BLUE;

            case VERMELHO:
            return Color.RED;

            case AMARELO:
            return Color.YELLOW;
            
            default:
            return Color.WHITE;
        }
    }

    public void playerTurn(){
        turn = (turn % 4);
        notifySubscribersTurn(getCurrentPlayerColor());

        if (plays == 0){

            //players[turn].updateChoices(this);
            //players[turn].playTurn(this);

            if (getDieNumber() != 6){
                plays = 0;
                turn++;
                return;
            }
            plays++;

        } else if (plays == 1){

		    //players[turn].updateChoices(this);
		    //players[turn].playTurn(this);

		    if (getDieNumber() != 6){
                plays = 0;
			    turn++;
                
			    return;
		    }

            plays++;
            

        } else if (plays == 2){

		    //players[turn].updateChoices(this);

		    if (getDieNumber() == 6){
			    //TODO: LAST PAWN MOVED MUST RETURN TO INITIAL HOUSE
                plays = 0;
			    turn++;
			    return;
		    } else {
			    //players[turn].playTurn(this);
                plays = 0;
			    turn++;
			    return;
		    }

        }
			
	}
}
