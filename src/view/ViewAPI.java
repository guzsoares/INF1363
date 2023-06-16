package view;

import controller.MenuSubscriber;
import model.ModelAPI;
import controller.BoardSubscriber;

public class ViewAPI {
    private static ViewAPI instance;
    private GameScreen gameView;
    private ModelAPI modelAPI = ModelAPI.getInstance();
    
    public ViewAPI(){
        instance = this;
    }

    public static ViewAPI getInstance() {
        if (instance == null) {
            instance = new ViewAPI();
        }
        return instance;
    }

    public void createView(){
        gameView = new GameScreen();
    }

    public void redraw(){
        gameView.redraw();
    }

    public void showMessage(){
        gameView.showMessage(modelAPI.getCurrentPlayerColor());
    }

    public MenuSubscriber getMenuSubscriber() {
        return gameView.getGameMenu().getSubscriber();
    }

    public BoardSubscriber getBoardSubscriber() {
        return gameView.getGameBoard().getBoardSubscriber();
    }

    public void endGameMessage(String winner){
        gameView.showMessage(winner);
    }
}
