package model;

import java.util.List;



class Pawn {
    private int position;
	private int steps;
    private final Color color;
    
    public Pawn(Color color, int initialPosition){ // Construtor da classe Peao, onde é definida as propriedades do peão, como cor e posição inicial
        this.color = color;
        this.position = initialPosition;
    }

    public boolean canMove(int dieNumber, Pawn[] pawnsOnBoard, Square[] boardSquares){
		if (dieNumber == 5 && position == -1){
			switch (this.color){
				case AMARELO:
				for (Pawn otherPawn : pawnsOnBoard){
					if (otherPawn.getColor() == this.color && otherPawn.getPosition() == 13){
						return false;
					}
				}
				return true;
				case AZUL:
				for (Pawn otherPawn : pawnsOnBoard){
					if (otherPawn.getColor() == this.color && otherPawn.getPosition() == 26){
						return false;
					}
				}
				return true;
				case VERMELHO:
				for (Pawn otherPawn : pawnsOnBoard){
					if (otherPawn.getColor() == this.color && otherPawn.getPosition() == 39){
						return false;
					}
				}
				return true;
				case VERDE:
				for (Pawn otherPawn : pawnsOnBoard){
					if (otherPawn.getColor() == this.color && otherPawn.getPosition() == 0){
						return false;
					}
				}
				return true;
			}
		}
		else if (position >= 0){
			int futurePosition = dieNumber + position;

			// ajustando a posicao futura para no caso de passar do ultimo ponto do vetor

			// TODO: FAZER CASO PARA O AZUL
			if (futurePosition > 51){
				futurePosition = futurePosition - 51;
			}

			// caso exista barreira no caminho (return false)
			for (int i = position; i < dieNumber; i++){
				if (i > 51){
					i = i - 52;
				}

				if (boardSquares[i].isBarrier()){
					// caso seja uma barreira 
					return false;
				}
			}


			if (boardSquares[futurePosition].isAbrigo() && boardSquares[futurePosition].numPawns() == 2){
				// caso seja um abrigo e ja esteja ocupado por dois peoes
				return false;
			}

			if (boardSquares[futurePosition].isSaida() && boardSquares[futurePosition].numPawns() == 2){
				// caso seja uma saida e ja esteja ocupada por dois peoes
				
				return false;
			}

			if (steps > 51){
				// caso esteja na reta final
				if ((steps + dieNumber) - 57 >= 0){
					return true;
				}
			}

			return true;
		}
        return false;
    }

	public void movePawn(int dieNumber, Square[] initialSquares, Square[] boardSquares[],Square[][] finalSquares){
		if (dieNumber == 5 && position == -1){
			switch (this.color){
				case AMARELO:
				this.steps += 1;
				this.setPosition(13);
				return;
				case AZUL:
				this.steps += 1;
				this.setPosition(26);
				return;
				case VERMELHO:
				this.steps += 1;
				this.setPosition(39);
				return;
				case VERDE:
				this.steps += 1;
				this.setPosition(0);
				return;
			}
		}

	}

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public int getPosition(){ // Retorna a posição atual do peão, isso pode ser acessado por outras classes no pacote Model
        return this.position;
    }

    public Color getColor(){ // Retorna a cor do peão, isso pode ser acessado por outras classes no pacote Model
        return this.color;
    }
    

}
