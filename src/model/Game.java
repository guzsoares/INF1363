package model;

class Game {
    private static Game instance;
    public Player[] players;
    public Die die;
    public Board board;


    public Game(){
        instance = this;
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
            		players[i] = new Player(Color.VERDE, playersPawns);
            		break;
            	case(1):
            		players[i] = new Player(Color.AMARELO, playersPawns);
            		break;
            	case(2):
            		players[i] = new Player(Color.AZUL, playersPawns);
            		break;
            	case(3):
            		players[i] = new Player(Color.VERMELHO, playersPawns);
            		break;
            }
        }
    }

    public static Game getInstance() {
        if (instance == null) {
            synchronized (Game.class) {
                if (instance == null) {
                    instance = new Game();
                }
            }
        }
        return instance;
    }

    public void rollDie(){
        die.rollDie();
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

    public static void main(String[] args) {
        Game game = new Game();
        game.die.setDieNumber(5);
        game.players[0].makeMove(0);
        boolean moved = game.players[0].makeMove(1);
        System.out.println(game.players[0].getPlayerPawns()[1].getPosition());
    }
}
