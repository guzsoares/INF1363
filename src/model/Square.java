package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

class Square{
    private List<Pawn> pawns = new ArrayList<>();
    private SquareType squareType;
    private Color squareColor;

    public boolean isAbrigo(){ // verifica se a casa é abrigo
        if (this.squareType == SquareType.cAbrigo){
            return true;
        }
        return false;
    }

    public boolean isSaida(){ // verifica se a casa é saida
        if (this.squareType == SquareType.cSaida){
            return true;
        }
        return false;
    }

    public boolean isBarrier(){
        if (numPawns() == 2 && pawns.get(0).getColor() == pawns.get(1).getColor() && isSaida() == false) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() { // casa esta ocupada
        return pawns.isEmpty();
    }

    public int numPawns() { // numero de peoes ocupando a casa
        return pawns.size();
    }

    public void addPawn(Pawn pawn) { // adicionar um peao na casa
        pawns.add(pawn);
    }

    public void removePawn(Pawn pawn) { // remover um peao na casa
        pawns.remove(pawn);
    }

    public List<Pawn> getPawns() { // lista de peoes na casa
        return pawns;
    }

    public Color getSquareColor(){
        return this.squareColor;
    }

    public void setSquareColor(Color color){
        this.squareColor = color;
    }

        public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

}