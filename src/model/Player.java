package model;

class Player {
    private Color color;
	private boolean myTurn = false;
	private Pawn[] playerPawns;
	private Board playerBoard;
	private int choice;
	private boolean[] choices = new	boolean[4];
	private int turnCount = 0;

	public Player(Color color, Pawn[] pawns, Board playerBord){
		this.color = color;
		this.playerPawns = pawns;
		this.playerBoard = playerBord;
	}

	public void playerTurn(){
		if (myTurn == true){
			turnCount++;
			playerBoard.rollDie();

			for (int i = 0; i < 4; i++){
				if(playerPawns[i].canMove(i, playerPawns, playerBoard.getSquares())){
					choices[i] = true;
				}else {
					choices[i] = false;
				}

			}

			if (!verifyChoices() && playerBoard.getDieNumber() < 6){
				return;
			}

			if (verifyChoices() && playerBoard.getDieNumber() < 6){

				return;
			}
		}
		playerBoard.isGameOver();
	}

    
	public void setTurn(boolean turn){
		this.myTurn = turn;
	}

	public boolean getTurn(){
		return this.myTurn;
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
		int result = playerPawns[0].getSteps() + playerPawns[1].getSteps() + playerPawns[2].getSteps() + playerPawns[3].getSteps();
		return result;
	}
}
