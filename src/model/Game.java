package model;

import controller.AbstractPublisher;
import java.awt.Color;

class Game extends AbstractPublisher{
    public Player[] players;
    public Die die;
    public Board board;
    public int turn = 0;
    private int plays = 0;
    private boolean playing = false;


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
        if (playing == false){
            playing = true;
            die.rollDie();
            notifySubscribersDie(getDieNumber());
            notifySubscribersTurn(getCurrentPlayerColor());

            players[turn].updateChoices(this);
            if (players[turn].verifyChoices() == false){
                notifyBoardUpdate();
                playing = false;
                playerTurn();
            }
        }
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

    public void updateInfo(){
        board.updatePawnsOnBoard(players);
    }

    public void playerTurn(){
        turn = (turn % 4);

        notifySubscribersTurn(getCurrentPlayerColor());

        if (plays == 0){

            if (getDieNumber() != 6){
                plays = 0;
                turn++;
                return;
            }
            plays++;

        } else if (plays == 1){

		    if (getDieNumber() != 6){
                plays = 0;
			    turn++;
                
			    return;
		    }

            plays++;
            

        } else if (plays == 2){

		    if (getDieNumber() == 6){
			    //TODO: LAST PAWN MOVED MUST RETURN TO INITIAL HOUSE
                plays = 0;
			    turn++;
			    return;
		    } else {
                plays = 0;
			    turn++;
			    return;
		    }

        }
			
	}

    public Color getCurrentPlayerColor(){
        turn = (turn % 4);

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

    public Board getGameBoard(){
        return board;
    }

    public Player[] getPlayers(){
        return players;
    }

    public int getDieNumber(){
        return die.getDieNumber();
    }

        public void handleClick(int position){
            turn = (turn % 4);
            if (playing == false){
                return;
            }
            Square[] boardSquares = board.getSquares();
            Square[] initialSquares = board.getInitialSquares();
            Square[][] finalSquares = board.getFinalSquares();


            if (position >= 0 && position <= 51){
                if (boardSquares[position].numPawns() == 0){
                    return;
                }

                for (Pawn pawns : boardSquares[position].getPawns()){
                    if (pawns.getColor() == players[turn].getColor()){
                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }
                }
            }

            if (position < 0){

                if (position == -1){
                    for (Pawn pawns : initialSquares[0].getPawns()){
                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -2){
                    for (Pawn pawns : initialSquares[1].getPawns()){
                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -3){
                    for (Pawn pawns : initialSquares[2].getPawns()){
                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -4){
                    for (Pawn pawns : initialSquares[3].getPawns()){
                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                }
            }

            if (position > 99){

                if (position >= 100 && position <= 105){
                    Square[] gFinalSquares = finalSquares[0];

                    int index  = position - 100;

                    for (Pawn pawns: gFinalSquares[index].getPawns()){

                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }

                    }
                } else if (position >= 200 && position <= 205){
                    Square[] yFinalSquares = finalSquares[1];

                    int index  = position - 200;

                    for (Pawn pawns: yFinalSquares[index].getPawns()){

                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                        
                    }
                } else if (position >= 300 && position <= 305){
                    Square[] bFinalSquares = finalSquares[2];

                    int index  = position - 300;

                    for (Pawn pawns: bFinalSquares[index].getPawns()){

                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                        
                    }
                } else if (position >= 400 && position <= 405){
                    Square[] rFinalSquares = finalSquares[3];

                    int index  = position - 400;

                    for (Pawn pawns: rFinalSquares[index].getPawns()){

                        if (players[turn].makeMove(pawns.getId(), this) == true){
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                        
                    }
                }
            }
        }
}
