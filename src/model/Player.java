package model;

class Player {
    private Color color;
	private Pawn[] playerPawns;
	private Board playerBoard;
	private boolean[] choices = new	boolean[4];
	private boolean myTurn = false;

	public Player(Color color, Pawn[] pawns, Board playerBoard){
		this.color = color;
		this.playerPawns = pawns;
		this.playerBoard = playerBoard;
	}

	public void playerTurn(){
		if (this.myTurn == true){ // inicio do turno
			playerBoard.rollDie();
			updateChoices();

			if (verifyChoices() == false && playerBoard.getDieNumber() < 6){
				myTurn = false;
				return; // encerra o turno
			}











		}
	}

	public void updateChoices(){

		for (int i = 0; i < 4; i++){
			if (playerPawns[i].canMove(playerBoard.getDieNumber(), playerBoard.getPawnsOnBoard(), playerBoard.getSquares())){
				choices[i] = true;
			} else {
				choices[i] = false;
			}
		}
	}

	public boolean makeMove(int choice){

		if (playerPawns[choice].movePawn(playerBoard.getDieNumber(), playerBoard.getSquares(), playerBoard.getFinalSquares(), playerBoard.getPawnsOnBoard())){
			playerBoard.updatePawnsOnBoard();
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
