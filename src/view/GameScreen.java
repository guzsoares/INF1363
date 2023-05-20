package view;

import javax.swing.*;
import java.awt.*;

class GameScreen extends JFrame{
    public final int xScreen = 850;
    public final int yScreen = 628;

    private BoardBuilder boardBuilder;
    private GameMenu gameMenu;

    private JButton dieButton;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton saveGameButton;

    private JLabel textLabel;


    public GameScreen() {
        this.gameMenu = new GameMenu();
        this.boardBuilder = new BoardBuilder();


        setTitle("Ludo, O Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xScreen, yScreen);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel boardPanel = boardBuilder.getBoardPanel();
        JPanel menuPanel = gameMenu.getMenuPanel();
        contentPane.add(boardPanel);
        contentPane.add(menuPanel, BorderLayout.EAST);

        setVisible(true);
        
    }

    public void redraw(){
        repaint();
    }

    public static void main(String[] args) {
        GameScreen frame = new GameScreen();
    }
}
