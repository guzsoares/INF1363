package view;

import javax.swing.*;
import java.awt.*;

class GameScreen extends JFrame{
    public final int xScreen = 850;
    public final int yScreen = 628;

    private BoardBuilder boardBuilder;
    private GameMenu gameMenu;
    private GameActions gameActions;

    public GameScreen() {
        this.gameMenu = new GameMenu();
        this.boardBuilder = new BoardBuilder();
        this.gameActions = new GameActions();


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

        //showMessage("verde");
        
    }

    public void redraw(){
        repaint();
    }

    public void showMessage(String jogadorVencedor){
        JOptionPane.showMessageDialog(null, "O jogo foi encerrado, o jogador " + jogadorVencedor + " venceu!" );

        int answer = JOptionPane.showOptionDialog(null, "Deseja jogar outra partida?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (answer == JOptionPane.YES_OPTION) {
            System.out.println("Usuário escolheu 'Sim'");
        } else {
            System.out.println("Usuário escolheu 'Não'");
        }
    }

    public GameMenu getGameMenu(){
        return this.gameMenu;
    }
}
