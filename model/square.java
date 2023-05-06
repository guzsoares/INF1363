package model;

import java.util.List;
import java.util.ArrayList;

class Square{
    private final int num;
    private List<Pawn> pawns;
    private int squareType; // 0 = casa comum, 1 = casa abrigo, 2 = reta final, 3 = barreira, 4 = casa final, 5 = casa saida

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
        if (this.squareType == 0){
            return true;
        }
        return false;
    }

    public boolean isAbrigo(){ // verifica se a casa é abrigo
        if (this.squareType == 1){
            return true;
        }
        return false;
    }

    public boolean isRetaFinal(){ // verifica se a casa é abrigo
        if (this.squareType == 2){
            return true;
        }
        return false;
    }

    public boolean isBarrier(){ // verifica se é uma barreira
        if (this.squareType == 3) {
            return true;
        }
        return false;
    }

    public boolean isFinal(){ // verifica se a casa é final
        if (this.squareType == 4){
            return true;
        }
        return false;
    }

    public boolean isSaida(){ // verifica se a casa é saida
        if (this.squareType == 5){
            return true;
        }
        return false;
    }



    // verificar se esta na classe correta

    public void setCommon(){ 
        this.squareType = 0;

    }

    public void setAbrigo(){ 
        this.squareType = 1;

    }

    public void setRetaFinal(){ 
        this.squareType = 2;

    }

    public void setBarrier(){
        if (numPawns() == 2 && pawns.get(0).getColor() == pawns.get(1).getColor() && isSaida() == false) {
            this.squareType = 3;
        }
    }

    public void setFinal(){ 
            this.squareType = 4;

    }

    public void setSaida(){ 
        this.squareType = 5;

    }


    public boolean isCapture(){ // verifica se é uma captura
        if (numPawns() == 2 && isAbrigo() == false && isSaida() == false && pawns.get(0).getColor() != pawns.get(1).getColor()){
            return true;
        }
        return false;
    }
}
