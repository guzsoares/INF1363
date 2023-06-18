package model;
import controller.AbstractPublisher;
import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class Game extends AbstractPublisher implements Serializable{
	private static final long serialVersionUID = 1L;
    public Player[] players;
    public Die die;
    public Board board;
    public int turn = 0;
    private int plays = 0;
    private boolean playing = false;
    private boolean isGameOver = false;
    private Player winner;
    
    private boolean DEBUG = true;


    public Game(){
        this.turn = 0;
        this.plays = 0;
        this.playing = false;
        this.isGameOver = false;
        this.players = new Player[4];
        this.die = new Die();
        this.board = new Board();

        for (int i = 0; i < 4; i++){
            Pawn[] playersPawns = new Pawn[4];
            playersPawns[0] = this.board.getPawnOnIndex(4*i);
            playersPawns[1] = this.board.getPawnOnIndex(4*i + 1);
            playersPawns[2] = this.board.getPawnOnIndex(4*i + 2);
            playersPawns[3] = this.board.getPawnOnIndex(4*i + 3);
            switch(i) {
            	case(0):
            		this.players[i] = new Player(model.Color.VERDE, playersPawns);
            		break;
            	case(1):
            		this.players[i] = new Player(model.Color.AMARELO, playersPawns);
            		break;
            	case(2):
            		this.players[i] = new Player(model.Color.AZUL, playersPawns);
            		break;
            	case(3):
            		this.players[i] = new Player(model.Color.VERMELHO, playersPawns);
            		break;
            }
        }
    }

    public void rollDie(){
        if (playing == false){
            System.out.println(playersResults(players));
            playing = true;
            die.rollDie();
            play();
        }
    }

    public void roll_x(int x){
        if (playing == false){
            playing = true;
            die.setDieNumber(x);
            play();

        }
    }

    public void play(){
        notifySubscribersDie(getDieNumber());
        notifySubscribersTurn(getCurrentPlayerRealColor());

        players[turn].updateChoices(this);

        if (plays == 2 && getDieNumber() == 6){
            if (players[turn].punishPlayer(board.getSquares(), board.getInitialSquares()) == true){
                notifyBoardUpdate();
                playing = false;
                playerTurn();
                return;
            }
        }
            
        if (players[turn].verifyChoices() == false){
            playing = false;
            playerTurn();
            return;
        }

        turn = (turn % 4);
        if (players[turn].getNumChoices() == 1){
            players[turn].makeMove(players[turn].getFirstChoice(), this);

            notifyBoardUpdate();
            playing = false;
            playerTurn();
            return;
        }
    }

    public void isGameOver(){
    	isGameOver = false;
        for (int i = 0; i < 4; i++){
            if(board.getFinalSquares()[i][5].numPawns() == 4){
            	isGameOver = true;
            	return;
            }
        }
    }
  
    public String playersResults(Player[] players){
        Player[] ranking = new Player[4];

        ranking = players;

        Arrays.sort(ranking, Comparator.comparingInt(Player::stepsCount).reversed());

        winner = ranking[0];

        String result = "1 - " + ranking[0].getColor() + "\n2 - "  + ranking[1].getColor() + "\n3 - "  + ranking[2].getColor() + "\n4 - "   + ranking[3].getColor();

        return result;
    }

    public void playerTurn(){
        turn = (turn % 4);
        isGameOver();
        notifySubscribersTurn(getCurrentPlayerRealColor());

        if (players[turn].hasExtra() == true){
            playing = true;

            players[turn].updateChoices(this);

            if (players[turn].verifyChoices() == false){
                playing = false;
                players[turn].resetExtra();
                playerTurn();
            }
            return;
        }

        if (plays > 2){
            plays = 0;
            turn++;
            return;
        }
        
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

    public void handleClick(int position){

        if (isGameOver == true){
            return;
        }

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

            if (players[turn].hasExtra() == true){
            players[turn].updateChoices(this);

            for (Pawn pawns : boardSquares[position].getPawns()){
                if (pawns.getColor() == players[turn].getColor()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        int lastDieNumber = getDieNumber();
                        die.setDieNumber(6);
                        notifySubscribersDie(getDieNumber());
                        players[turn].makeMove(pawns.getId(), this);
                        notifyBoardUpdate();
                        playing = false;
                        die.setDieNumber(lastDieNumber);
                        players[turn].resetExtra();
                        playerTurn();
                        return;
                    }
                }
            }
        }
            for (Pawn pawns : boardSquares[position].getPawns()){
                if (pawns.getColor() == players[turn].getColor()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }
            }
        }

        if (position < 0){
            if (position == -1){
                for (Pawn pawns : initialSquares[0].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }

            } else if (position == -2){
                for (Pawn pawns : initialSquares[1].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }

            } else if (position == -3){
                for (Pawn pawns : initialSquares[2].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }

            } else if (position == -4){
                for (Pawn pawns : initialSquares[3].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
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
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }

                }
            } else if (position >= 200 && position <= 205){
                Square[] yFinalSquares = finalSquares[1];
                int index  = position - 200;

                for (Pawn pawns: yFinalSquares[index].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }
            } else if (position >= 300 && position <= 305){
                Square[] bFinalSquares = finalSquares[2];
                int index  = position - 300;

                for (Pawn pawns: bFinalSquares[index].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }
            } else if (position >= 400 && position <= 405){
                Square[] rFinalSquares = finalSquares[3];
                int index  = position - 400;

                for (Pawn pawns: rFinalSquares[index].getPawns()){
                    if (players[turn].getChoice(pawns.getId()) == true){
                        moveFunction(pawns);
                        return;
                    }
                }
            }
        }
    }

    private void moveFunction(Pawn pawns){
        players[turn].makeMove(pawns.getId(), this);
        notifyBoardUpdate();
        playing = false;
        playerTurn();
    }

    public void updateInfo(){
        board.updatePawnsOnBoard(players);
    }

    public boolean getDEBUG() {
        return DEBUG;
    }

    public boolean getGameOver(){
        return isGameOver;
    }

    public int getTurn(){
        turn = turn % 4;
        return turn;
    }

    public int getPlays(){
        return plays;
    }

    public boolean getPlaying(){
        return playing;
    }

    public Color getCurrentPlayerRealColor(){
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

    public Player getWinner(){
        return winner;
    }

    public void setTurn(int turno) {
    	turn = turno;
    }

}


