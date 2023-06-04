package model;

import java.util.List;

class Pawn {
    private int position;
	private int steps;
    private final Color color;
	private final int id;
    
    public Pawn(Color color, int initialPosition, int id){ // Construtor da classe Peao, onde é definida as propriedades do peão, como cor e posição inicial
        this.color = color;
        this.position = initialPosition;
		this.id = id;
    }

	public boolean movePawn(int dieNumber, Square[] boardSquares,Square[][] finalSquares, Pawn[] pawnsOnBoard, Square[] initialSquares){

		/* Primeiro a função calcula o overflow (caso o peão esteja na reta final, a nova posição de acordo com o dado
		 e ele realiza uma normalização no vetor para estar correspondente a casa do peão, depois a função vai verificar se 
		 é possível mover esse peão, caso sim, ele verifica se está na casa inicial, caso sim, ele define a nova posição do 
		 peão como sua casa de saída, se o peão puder realizar uma captura, ele captura o peão na casa destino e reseta seus
		 status para os status iniciais do jogo

		 A função realiza a mesma lógica para o tabuleiro nos quadrados que não são iniciais
		 */

		int overflow = (this.steps + dieNumber) - 51;
		int newPosition = (position + dieNumber) % 52;

		if (canMove(dieNumber, pawnsOnBoard, boardSquares) == false){
			return false;
		}

		if (this.position == -1 && dieNumber == 5){
			switch (this.color){
				case AZUL:
				newPosition = 26;
				break;
				case VERMELHO:
				newPosition = 39;
				break;
				case AMARELO:
				newPosition = 13;
				break;
				case VERDE:
				newPosition = 0;
				break;
			}
		}

		if (this.position < 52){
			if (canCapture(boardSquares, newPosition)){
				capturePawn(boardSquares, initialSquares, newPosition);
			}
	
			if (outInitialSquare(dieNumber, initialSquares, boardSquares, pawnsOnBoard)){
				return true;
			}
		}

		if (this.position >= 0){

		if (this.steps + dieNumber > 51 && this.steps < 51){
			switch(this.color){
				case AZUL:
				boardSquares[position].removePawn(this);
				finalSquares[2][overflow - 1].addPawn(this);
				newPosition = 299 + overflow;
				break;
				case VERMELHO:
				boardSquares[position].removePawn(this);
				finalSquares[3][overflow - 1].addPawn(this);
				newPosition = 399 + overflow;
				break;
				case AMARELO:
				boardSquares[position].removePawn(this);
				finalSquares[1][overflow - 1].addPawn(this);
				newPosition = 199 + overflow;
				break;
				case VERDE:
				boardSquares[position].removePawn(this);
				finalSquares[0][overflow - 1].addPawn(this);
				newPosition = 99 + overflow;
				break;
			}
		} else if (this.steps >= 51) {
			switch(this.color){
				case AZUL:
				finalSquares[2][this.steps - 51].removePawn(this);
				finalSquares[2][overflow - 1].addPawn(this);
				newPosition = 299 + overflow;
				break;
				case VERMELHO:
				finalSquares[3][this.steps - 51].removePawn(this);
				finalSquares[3][overflow - 1].addPawn(this);
				newPosition = 399 + overflow;
				break;
				case AMARELO:
				finalSquares[1][this.steps - 51].removePawn(this);
				finalSquares[1][overflow - 1].addPawn(this);
				newPosition = 199 + overflow;
				break;
				case VERDE:
				finalSquares[0][this.steps - 51].removePawn(this);
				finalSquares[0][overflow - 1].addPawn(this);
				newPosition = 99 + overflow;
				break;
			}
		} else {
			boardSquares[position].removePawn(this);
			boardSquares[newPosition].addPawn(this);
		}
	}
		this.setPosition(newPosition);
		this.addSteps(dieNumber);

		return true;
	}

	public boolean canMove(int dieNumber, Pawn[] pawnsOnBoard, Square[] boardSquares){
		if (steps == 57){
			return false;
		}
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
		} else if (position >= 0 && position <= 51){
			int futurePosition = dieNumber + position;
			
			if (futurePosition > 51){
				futurePosition = futurePosition % 52;
			}

			// caso exista barreira no caminho (return false)
			for (int i = position + 1; i <= futurePosition; i++){
				if (i > 51){
					i = i - 52;
				}
				if (boardSquares[i].isBarrier()){
					// caso seja uma barreira 
					return false;
				}
			}

			if (boardSquares[futurePosition].isAbrigo() && boardSquares[futurePosition].numPawns() >= 2){
				// caso seja um abrigo e ja esteja ocupado por dois peoes
				return false;
			}

			if (boardSquares[futurePosition].isSaida() && boardSquares[futurePosition].numPawns() >= 2){
				// caso seja uma saida e ja esteja ocupada por dois peoes
				
				return false;
			}

			if (boardSquares[futurePosition].isSaida() && boardSquares[futurePosition].numPawns() == 1){
				List<Pawn> pawns = boardSquares[futurePosition].getPawns();
				if (pawns.get(0).getColor() != boardSquares[futurePosition].getSquareColor()){
					return false;
				}
			}
			return true;
		} else if (steps > 51){
			// caso esteja na reta final
			if ((steps + dieNumber) - 57 == 0){
				return true;
			}
		}
        return false;
    }

	public boolean outInitialSquare(int dieNumber, Square[] initialSquares, Square[] boardSquares, Pawn[] pawnsOnBoard){

		if (dieNumber == 5 && this.position == -1){

			switch(this.color){
				case AZUL:
				initialSquares[2].removePawn(this);
				boardSquares[26].addPawn(this);
				this.setPosition(26);
				break;
				case VERMELHO:
				initialSquares[3].removePawn(this);
				boardSquares[39].addPawn(this);
				this.setPosition(39);
				break;
				case AMARELO:
				initialSquares[1].removePawn(this);
				boardSquares[13].addPawn(this);
				this.setPosition(13);
				break;
				case VERDE:
				initialSquares[0].removePawn(this);
				boardSquares[0].addPawn(this);
				this.setPosition(0);
				break;
			}

			this.addSteps(1);
			return true;
		}

		return false;
	}

	public boolean canCapture(Square[] boardSquares, int newPosition){

		if (boardSquares[newPosition].numPawns() == 1){
			if (this.position == -1){
				// condicional para verificar se o peão está saindo da casa inicial dele
			}
			else if (boardSquares[newPosition].isAbrigo() || boardSquares[newPosition].isSaida()){
				return false;
			}
			List<Pawn> pawns = boardSquares[newPosition].getPawns();
			if (pawns.get(0).getColor() == this.color){
				return false;
			}
			return true;
		}
		return false;
	}

	public void capturePawn(Square[] boardSquares, Square[] initialSquares, int newPosition){

		System.out.println("aqui");

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

		System.out.println("alo" + initialSquares[1].numPawns());
	}

	public void addSteps(int steps){ // adiciona o numero de passos
		this.steps += steps;
	}

    public int getPosition(){ // retorna a posicao do peao
        return this.position;
    }

    public Color getColor(){ // retorna a cor do peao
        return this.color;
    }

	public int getSteps(){ // retorna o numero de passos
		return this.steps;
	}

	public Pawn getPawn(){ // retorna o peao
		return this;
	}

	public int getId(){
		return this.id;
	}

	public void setSquare(Square[] boardSquares){ // metodo criada para testes
		boardSquares[this.position].addPawn(this);
	}

	public void setSteps(int steps){ // define o numero de passos
		this.steps = steps;
	}
	
	public void setPosition(int newPosition){ // define a posicao do peao
        this.position = newPosition;

    }

}
