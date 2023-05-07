package model;

class player {
    private Color color;
	private boolean myTurn;
	private Die myDie = new Die();
    
	public void playerTurn(){
		if (myTurn == true){
			// primeira vez do jogador
			myDie.rollDie();

			// mover o peao numero do dado

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
			// move o peao para casa inicial
			myTurn = false;
			return;
		}
	}

}