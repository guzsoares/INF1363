package view;

import javax.swing.*;

public class GameMenu {

    public JButton dieButton() {
        JButton button = new JButton("Lancar Dado");
        button.setBounds(625, 420, 200, 50);
        return button;
    }

    public JButton newGameButton() {
        JButton button = new JButton("Novo Jogo");
        button.setBounds(625, 50, 200, 50);
        return button;
    }

    public JButton loadGameButton() {
        JButton button = new JButton("Carregar Jogo");
        button.setBounds(625, 120, 200, 50);
        return button;
    }

    public JButton saveGameButton() {
        JButton button = new JButton("Salvar Jogo");
        button.setBounds(625, 190, 200, 50);
        return button;
    }

}
