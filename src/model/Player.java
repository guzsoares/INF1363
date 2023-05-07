package model;

class player {
    private int contaSeis;
    private Color color;
    private int valor;
    
    public boolean atualizaSeis(int valor){
    	if(valor == 6) {
    		contaSeis++;
    		
    	} else {
    		contaSeis = 0;
    	}
    	
    	if(contaSeis == 3){
    		contaSeis = 0;
    		return true;
    	}
    		return false;
    }

}