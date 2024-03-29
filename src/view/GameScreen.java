package view;

import javax.swing.*;

import java.awt.*;

class GameScreen extends JFrame{ 
    public final int xScreen = 865;
    public final int yScreen = 640;

    private BoardBuilder boardBuilder;
    private GameMenu gameMenu;

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

        contentPane.add(menuPanel, BorderLayout.LINE_END);
        contentPane.add(boardPanel, BorderLayout.LINE_START);

        setVisible(true);
        
    }

    public void redraw(){
        repaint();
    }

    public void showMessage(String jogadorVencedor){
        JOptionPane.showMessageDialog(null, "O jogo foi encerrado, o jogador " + jogadorVencedor + " venceu!" );

        int answer = JOptionPane.showOptionDialog(null, "Deseja jogar outra partida?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (answer == JOptionPane.YES_OPTION) {
            gameMenu.newGameAction();
        } else {
            return;
        }
    }

    public void showWarning(String warning){
        JOptionPane.showMessageDialog(null, warning);
    }

    public void showRanking(String ranking){
        JOptionPane.showMessageDialog(null, ranking);
    }

    public GameMenu getGameMenu(){
        return this.gameMenu;
    }
    
    public BoardBuilder getGameBoard() {
        return this.boardBuilder;
    }
}
