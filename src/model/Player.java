package model;

<<<<<<< HEAD
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
=======
class player {
    private Color color;
	private boolean myTurn;
	private Die myDie = new Die();
    
	public void playerTurn(){
		if (myTurn == true){
			// primeira vez do jogador
			myDie.rollDie();
			
			//verificar se die.value == 5
				//verificar se casa inicial tem peao
					//verificar se tem peoes da propria cor ocupando casa de saida.
			
			// mover o peao numero do dado(andando e verificando)
			
			// verifica s efoi um 6 para repetir a vez
			if (myDie.getDieNumber() != 6){
				myTurn = false;
				return;
			}
		}
		if (myDie.getDieNumber() == 6){
			//caso tire 6 pode jogar novamente
			myDie.rollDie();

			// mover o peao o numero do dado

			// verifica se foi um 6 para repetir a vez
			if (myDie.getDieNumber() != 6){
				myTurn = false;
				return;
			}
		}
		if (myDie.getDieNumber() == 6){
			myDie.rollDie();

			if (myDie.getDieNumber() != 6){
				// move o peao o numero do dado
				myTurn = false;
				return;
			}
			// verifica se o ultimo peao movido ta na reta final
				//se tiver move outro qlqr para casa inicial
				//se nn tiver mover ele para inicial
			myTurn = false;
			return;
		}
	}

>>>>>>> parent of 3c98abf (Novos testes)
}
