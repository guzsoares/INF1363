package model;

import java.util.List;
import java.util.ArrayList;

class Square{
    private final int num;
    private List<Pawn> pawns;
    private SquareType squareType;

    public Square(int num) {
        this.num = num;
        this.pawns = new ArrayList<>();
    }

    public int getNum() { // numero da casa atual
        return num;
    }

    public boolean isOccupied() { // casa esta ocupada 
        return pawns.isEmpty();
    }

    public int numPawns() { // numero de peoes ocupando a casa
        return pawns.size();
    }

    public List<Pawn> getPawns() { // lista de peoes na casa
        return pawns;
    }

    public void addPawn(Pawn pawn) { // adicionar um peao na casa
        pawns.add(pawn);
    }

    public void removePawn(Pawn pawn) { // remover um peao na casa
        pawns.remove(pawn);
    }

    public boolean isCommon(){ // verifica se a casa é comum
        if (this.squareType == SquareType.cComum){
            return true;
        }
        return false;
    }

    public boolean isAbrigo(){ // verifica se a casa é abrigo
        if (this.squareType == SquareType.cAbrigo){
            return true;
        }
        return false;
    }

    public boolean isRetaFinal(){ // verifica se a casa é abrigo
        if (this.squareType == SquareType.rFinal){
            return true;
        }
        return false;
    }

    public boolean isFinal(){ // verifica se a casa é final
        if (this.squareType == SquareType.cFinal){
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


    public boolean isCapture(){ // verifica se é uma captura
        if (numPawns() == 2 && isAbrigo() == false && isSaida() == false && pawns.get(0).getColor() != pawns.get(1).getColor()){
            return true;
        }
        return false;
    }
    

}