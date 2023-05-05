package pawn;

import java.util.List;

class Pawn {
    private int position;
    private final int color;
    
    public Pawn(int color, int initialPosition){ // Construtor da classe Peao, onde é definida as propriedades do peão, como cor e posição inicial
        this.color = color;
        this.position = initialPosition;
    }

    public boolean canMove(int diceNumber, List<Pawn> pawnsOnBoard ){
        if (diceNumber == 5 && position == -1){ // -1 significa que o peão está na casa inicial e pode ir para a casa de saída
            return true;
        }
        else if (position >= 0) { // Verifica se o peão pode ir para uma nova posição
            
            int newPosition = position + diceNumber;

            if (newPosition < 52) { // 52 é a última casa do tabuleiro
                for (Pawn otherPawn : pawnsOnBoard){
                    if (otherPawn.getPosition() == newPosition && otherPawn.getColor() != this.color) {
                        // Esse condicional verifica se existe alguma peça ocupando o destino do peão
                        // Caso o peão seja inimigo, ele pode ser capturado

                        return true;
                    }
                }
                // Esse significa que não existe nenhuma peça na casa de destino

                return true;
            }
        }
        // O peão não tem movimentos possíveis

        return false;
    }

    public void setPosition(int newPosition){
        this.position = newPosition;
    }

    public int getPosition(){ // Retorna a posição atual do peão, isso pode ser acessado por outras classes no pacote Model
        return this.position;
    }

    public int getColor(){ // Retorna a cor do peão, isso pode ser acessado por outras classes no pacote Model
        return this.color;
    }

}
