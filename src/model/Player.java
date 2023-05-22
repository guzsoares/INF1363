package model;

class Player {
    private Color color;
	private Pawn[] playerPawns;
	private Game game = Game.getInstance();
	private boolean[] choices = new	boolean[4];
	private boolean myTurn = false;
	private int choice;

	public Player(Color color, Pawn[] pawns){
		this.color = color;
		this.playerPawns = pawns;
	}

	public void playerTurn(){
		if (this.myTurn == true){ // inicio do turno

			game.rollDie();
			updateChoices();

			playTurn();

			if (game.getDieNumber() != 6){
				this.myTurn = false;
				return;
			}

			game.rollDie();
			updateChoices();

			playTurn();

			if (game.getDieNumber() != 6){
				this.myTurn = false;
				return;
			}

			game.rollDie();
			updateChoices();

			if (game.getDieNumber() == 6){
				//TODO: LAST PAWN MOVED MUST RETURN TO INITIAL HOUSE
				this.myTurn = false;
				return;
			} else {
				playTurn();
				this.myTurn = false;
				return;
			}
			
		}
	}


	public boolean playTurn(){

		if (verifyChoices() == false){
			return false; // nao fez jogada
		}
		else if (verifyChoices() == true){

			while (choices[choice] == false){
				// redo choice
			}


			if(playerPawns[choice].movePawn(game.getDieNumber(), game.getGameBoard().getSquares(), game.getGameBoard().getFinalSquares(), game.getGameBoard().getPawnsOnBoard())){
				return true; // fez jogada
			}

		}

		return false; // nao fez jogada
	}

	public void updateChoices(){

		for (int i = 0; i < 4; i++){
			if (playerPawns[i].canMove(game.getDieNumber(), game.getGameBoard().getPawnsOnBoard(), game.getGameBoard().getSquares())){
				choices[i] = true;
			} else {
				choices[i] = false;
			}
		}
	}

	public boolean makeMove(int choice){

		if (playerPawns[choice].movePawn(game.getDieNumber(), game.getGameBoard().getSquares(), game.getGameBoard().getFinalSquares(), game.getGameBoard().getPawnsOnBoard())){
			game.updateInfo();
			return true;
		}

		return false;
	}

	public boolean verifyChoices(){
		for (int i = 0; i < choices.length; i++){
			if(choices[i] == true){
				return true;
			}
		}
		return false;
	}
	
	public int stepsCount(){
		return playerPawns[0].getSteps() + playerPawns[1].getSteps() + playerPawns[2].getSteps() + playerPawns[3].getSteps();
	}

	public Color getColor() {
		return this.color;
	}
	
	public Pawn[] getPlayerPawns(){
		return playerPawns;
	}
}
