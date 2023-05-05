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
        if (diceNumber == 6 && position == -1){ // -1 significa que o peão está na casa inicial e pode ir para a casa de saída
            return true;
        }
        else if (position >= 0) { // Verifica se o peão pode ir para uma nova posição
            
            int newPosition = position + diceNumber;

            if (newPosition <= 51) { // 51 é o valor da última possição que um peão pode se movimentar, sendo 52 a última posição possível
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

    public void move(int diceNumber) {
        if (diceNumber == 6 && this.position == -1) {
            // Se o dado for 6 e o peão ainda não estiver no tabuleiro, pode se mover para a casa de saída
            this.position = 0;
        } else if (position >= 0) {
            // Move o peão para a nova posição
            this.position += diceNumber;
        }
    }

    public int getPosition(){
        return this.position;
    }

    public int getColor(){
        return this.color;
    }

}
