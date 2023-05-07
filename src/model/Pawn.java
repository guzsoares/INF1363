package model;

import java.util.List;



class Pawn {
    private int position;
    private final Color color;
    
    public Pawn(Color color, int initialPosition){ // Construtor da classe Peao, onde é definida as propriedades do peão, como cor e posição inicial
        this.color = color;
        this.position = initialPosition;
    }

    public boolean canMove(int dieNumber, Pawn[] pawnsOnBoard, Square[] boardSquares){
		if (dieNumber == 5 && position == -1){
			return true;
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
					return false;
				}
			}


			if (boardSquares[futurePosition].isAbrigo() && boardSquares[futurePosition].numPawns() == 2){
				return false;
			}

			if (boardSquares[futurePosition].isSaida() && boardSquares[futurePosition].numPawns() == 2){
				return false;
			}



		}
        return false;
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