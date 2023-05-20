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
        createMenu();


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
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        boardBuilder.drawBoard(g2d);
    }

    public void redraw(){
        repaint();
    }

    private void createMenu() {
        dieButton = gameMenu.dieButton();
        newGameButton = gameMenu.newGameButton();
        loadGameButton = gameMenu.loadGameButton();
        saveGameButton = gameMenu.saveGameButton();
        textLabel = gameMenu.textLabel();

        add(textLabel);
        add(dieButton);
        add(newGameButton);
        add(loadGameButton);
        add(saveGameButton);
    }


    public static void main(String[] args) {
        GameScreen frame = new GameScreen();
    }
}
