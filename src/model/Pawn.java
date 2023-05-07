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
				if ((steps + dieNumber) - 57 <= 0){
					return true;
				}
			}

			return true;
		}
        return false;
    }

	public void movePawn(int dieNumber, Square[] boardSquares,Square[][] finalSquares, Pawn[] pawnsOnBoard){
		int newPosition = position + dieNumber;
		newPosition = newPosition % 51;


		if (this.steps + dieNumber > 51){
			int overflow = (this.steps + dieNumber) - 51;
			switch(this.color){
				case AZUL:
				boardSquares[position].removePawn(this);
				finalSquares[2][overflow].addPawn(this);
				break;
				case VERMELHO:
				boardSquares[position].removePawn(this);
				finalSquares[3][overflow].addPawn(this);
				break;
				case AMARELO:
				boardSquares[position].removePawn(this);
				finalSquares[1][overflow].addPawn(this);
				break;
				case VERDE:
				boardSquares[position].removePawn(this);
				finalSquares[0][overflow].addPawn(this);
				break;
			}
		}else if (this.steps > 51) {
			int overflow = (this.steps + dieNumber) - 51;
			switch(this.color){
				case AZUL:
				finalSquares[2][this.steps - 51].removePawn(this);
				finalSquares[2][overflow].addPawn(this);
				case VERMELHO:
				finalSquares[3][this.steps - 51].removePawn(this);
				finalSquares[3][overflow].addPawn(this);
				case AMARELO:
				finalSquares[1][this.steps - 51].removePawn(this);
				finalSquares[1][overflow].addPawn(this);
				case VERDE:
				finalSquares[0][this.steps - 51].removePawn(this);
				finalSquares[0][overflow].addPawn(this);
			}
		}else {
			boardSquares[position].removePawn(this);
			boardSquares[newPosition].addPawn(this);
		}

		this.position = newPosition;
		this.steps += dieNumber;
	}

	public boolean outInitialSquare(int dieNumber, Square[] initialSquares, Square[] boardSquares, Pawn[] pawnsOnBoard){
		if (dieNumber == 5 && position == -1){
			switch(this.color){
				case AZUL:
				initialSquares[2].removePawn(this);
				boardSquares[26].addPawn(this);
				this.position = 26;
				break;
				case VERMELHO:
				initialSquares[3].removePawn(this);
				boardSquares[39].addPawn(this);
				this.position = 39;
				break;
				case AMARELO:
				initialSquares[1].removePawn(this);
				boardSquares[13].addPawn(this);
				this.position = 13;
				break;
				case VERDE:
				initialSquares[0].removePawn(this);
				boardSquares[0].addPawn(this);
				this.position = 0;
				break;
			}

			this.steps += 1;
			return true;
		}
		return false;
	}

	public void capturePawn(int dieNumber, Square[] boardSquares, Square[] initialSquares){
		int newPosition = this.position + dieNumber;

		if (boardSquares[newPosition].numPawns() == 0){
			return;
		}
		List<Pawn> pawns = boardSquares[newPosition].getPawns();
		Pawn capturedPawn = pawns.get(0);

		boardSquares[capturedPawn.position].removePawn(capturedPawn);
		switch(capturedPawn.getColor()){
			case AZUL:
			initialSquares[2].addPawn(capturedPawn);
			break;
			case VERMELHO:
			initialSquares[3].addPawn(capturedPawn);
			break;
			case AMARELO:
			initialSquares[1].addPawn(capturedPawn);
			break;
			case VERDE:
			initialSquares[0].addPawn(capturedPawn);
			break;
		}

		capturedPawn.setSteps(0);
		capturedPawn.setPosition(-1);
	}

    public void setPosition(int newPosition){
        this.position = newPosition;

    }

	public void setSquare(Square[] boardSquares){
		boardSquares[this.position].addPawn(this);
	}

    public int getPosition(){ // Retorna a posição atual do peão, isso pode ser acessado por outras classes no pacote Model
        return this.position;
    }

    public Color getColor(){ // Retorna a cor do peão, isso pode ser acessado por outras classes no pacote Model
        return this.color;
    }
    
	public void setSteps(int steps){
		this.steps = steps;
	}

	public void addSteps(int steps){
		this.steps += steps;
	}

	public int getSteps(int steps){
		return this.steps;
	}

	public Pawn getPawn(){
		return this;
	}

}
