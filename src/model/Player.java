package model;

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

}
