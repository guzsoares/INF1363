package view;

import javax.swing.*;
import java.awt.*;

class GameScreen extends JFrame{
    public final int xScreen = 850;
    public final int yScreen = 630;

    private BoardBuilder boardBuilder;
    private GameMenu gameMenu;

    private JButton dieButton;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton saveGameButton;

    private JLabel textLabel;


    public GameScreen() {
        this.gameMenu = new GameMenu();
        this.boardBuilder = new BoardBuilder(xScreen, yScreen);
        createButtons();


        setTitle("Ludo, O Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xScreen, yScreen);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        setVisible(true);
        
    }

    @Override
    public void paint(Graphics g){
        boardBuilder.paint(g);

        super.paint(g);
    }

    public void redraw(){
        repaint();
    }

    private void createButtons() {
        dieButton = gameMenu.dieButton();
        newGameButton = gameMenu.newGameButton();
        loadGameButton = gameMenu.loadGameButton();
        saveGameButton = gameMenu.saveGameButton();

        add(dieButton);
        add(newGameButton);
        add(loadGameButton);
        add(saveGameButton);
    }


    public static void main(String[] args) {
        GameScreen frame = new GameScreen();
    }
}
