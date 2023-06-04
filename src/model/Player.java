package model;

class Player {
    private Color color;
	private Pawn[] playerPawns;
	private boolean[] choices = new	boolean[4];
	private int numChoices = 0;
	private int lastMove;

	public Player(Color color, Pawn[] pawns){
		this.color = color;
		this.playerPawns = pawns;

		for (int i = 0; i < 4; i++){
			choices[i] = false;
		}
	}

	public void updateChoices(Game game){

		for (int i = 0; i < 4; i++){
			choices[i] = false;
		}

		if (game.getDieNumber() == 5){
			for (int i = 0; i < 4; i++){
				if (playerPawns[i].getPosition() == -1){
					if (playerPawns[i].canMoveOutInitial(game.getDieNumber(), game.getGameBoard().getPawnsOnBoard())){
						choices[i] = true;
						setNumChoices(1);
					} else {
						choices[i] = false;
					}
				}
			}
		} 


		if (verifyChoices() == false){

			for (int i = 0; i < 4; i++){
				if (playerPawns[i].canMove(game.getDieNumber(), game.getGameBoard().getPawnsOnBoard(), game.getGameBoard().getSquares())){
					choices[i] = true;
				} else {
					choices[i] = false;
				}
			}

			this.numChoices = 0;
			for (int i = 0; i < 4; i++){
				if (choices[i] == true){
					this.numChoices++;
				}
			}

		}


	}

	public boolean makeMove(int choice, Game game){

		if (playerPawns[choice].movePawn(game.getDieNumber(), game.getGameBoard().getSquares(), game.getGameBoard().getFinalSquares(), game.getGameBoard().getPawnsOnBoard(), game.getGameBoard().getInitialSquares())){
			game.updateInfo();
			lastMove = choice;
			game.isGameOver();
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

	public int getNumChoices(){
		return this.numChoices;
	}

	public int getFirstChoice() {
		for (int i = 0; i < 4; i++){
			if (choices[i] == true){
				return i;
			}
		}

		return -1;
	}

	public boolean getChoice(int idx){
		return choices[idx];
	}

	public void setNumChoices(int num){
		this.numChoices = num;
	}

	public void punishPlayer(Square[] boardSquares, Square[] initialSquares){

		if (playerPawns[lastMove].getPosition() > 99 || playerPawns[lastMove].getPosition() == -1){
			return;
		}

		boardSquares[playerPawns[lastMove].getPosition()].removePawn(playerPawns[lastMove]);
		switch(playerPawns[lastMove].getColor()){
			case AZUL:
			initialSquares[2].addPawn(playerPawns[lastMove]);
			break;
			case VERMELHO:
			initialSquares[3].addPawn(playerPawns[lastMove]);
			break;
			case AMARELO:
			initialSquares[1].addPawn(playerPawns[lastMove]);
			break;
			case VERDE:
			initialSquares[0].addPawn(playerPawns[lastMove]);
			break;
		}

		playerPawns[lastMove].setSteps(0);
		playerPawns[lastMove].setPosition(-1);
	}
}
