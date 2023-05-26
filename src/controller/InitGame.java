package controller;

import view.ViewAPI;

class InitGame {
    private ViewAPI viewAPI = ViewAPI.getInstance();
    public static void main(String[] args) {
        GameScreen frame = viewAPI.getGameScreen();
    }
}
