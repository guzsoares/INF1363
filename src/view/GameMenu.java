package view;

import javax.swing.*;
import java.awt.*;

public class GameMenu {

    public JButton dieButton() {
        JButton button = new JButton("Lancar Dado");
        button.setBounds(625, 420, 200, 50);
        return button;
    }

    public JLabel textLabel() {
        JLabel label = new JLabel("Á Lançar:");
        label.setBounds(650, 250, 200, 50);
        Font font = label.getFont();
        float tamanhoFonte = font.getSize() + 20; // aumentar o tamanho da fonte em 4 pontos
        Font novaFonte = font.deriveFont(tamanhoFonte);
        label.setFont(novaFonte);
        return label;
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
