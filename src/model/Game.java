package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import controller.AbstractPublisher;
import java.awt.Color;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


class Game extends AbstractPublisher implements Serializable{
    public Player[] players;
    public Die die;
    public Board board;
    public int turn = 0;
    private int plays = 0;
    private boolean playing = false;
    private boolean isGameOver = false;


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

            if (plays == 2 && getDieNumber() == 6){
                players[turn].punishPlayer(board.getSquares(), board.getInitialSquares());
                notifyBoardUpdate();
                playing = false;
                playerTurn();
                return;
            }
            
            if (players[turn].verifyChoices() == false){
                notifyBoardUpdate();
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
    }
    public void setGameOver(boolean state) {
    	isGameOver = state;
    }
    public void isGameOver(){
    	setGameOver(false);
        for (int i = 0; i < 4; i++){
            if(board.getFinalSquares()[i][5].numPawns() == 4){
            	setGameOver(true);
            	return;
            }
        }
       
    }

    public boolean getGameOver(){
        return isGameOver;
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
        isGameOver();
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
                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
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
                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -2){
                    for (Pawn pawns : initialSquares[1].getPawns()){
                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -3){
                    for (Pawn pawns : initialSquares[2].getPawns()){
                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                    }

                } else if (position == -4){
                    for (Pawn pawns : initialSquares[3].getPawns()){
                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
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

                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
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

                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
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

                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
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

                        if (players[turn].getChoice(pawns.getId()) == true){
                            players[turn].makeMove(pawns.getId(), this);
                            notifyBoardUpdate();
                            playing = false;
                            playerTurn();
                            return;
                        }
                        
                    }
                }
            }
        }
        public boolean autogame() {
        		
        	while(true){
        	
        			rollDie();   	
        			System.out.println("turno "+ (turn%4));
        			if(players[turn%4].getNumChoices() > 1) {
        				turn = (turn % 4);
        	            players[turn].makeMove(players[turn].getFirstChoice(), this);
        	            notifyBoardUpdate();
        	            playing = false; 
        	            playerTurn();
        	            
        			}
        			if(getGameOver() == true) {
        				return getGameOver();
        			}
        			
        			
        		}
       
        }
        public void savegame() {
        	try {
        		FileOutputStream f = new FileOutputStream(new File("save.txt"));
        		ObjectOutputStream o = new ObjectOutputStream(f);
        		o.writeObject(this);
        		o.close();
        		FileInputStream fi = new FileInputStream(new File("save.txt"));
        		ObjectInputStream oi = new ObjectInputStream(fi);
        		fi.close();
        		oi.close();
        	}
        	catch (FileNotFoundException e) {
    			System.out.println("File not found");
    		} catch (IOException e) {
    			System.out.println("Error initializing stream");
    		} 
    		
        }
        public Game loadgame() {
        	try {

        		FileInputStream fi = new FileInputStream(new File("save.txt"));
        		ObjectInputStream oi = new ObjectInputStream(fi);
        		Object loaded = oi.readObject();
        		oi.close();
        		return (Game) loaded;
        		
        	}
        	catch (FileNotFoundException e) {
    			System.out.println("File not found");
    		} catch (IOException e) {
    			System.out.println("Error initializing stream");
    		} 
        	 catch (Exception ex) {
                 ex.printStackTrace();
                 return null;
             }
        	return null;
    		
        }

       }


