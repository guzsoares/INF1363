package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameMenu {
    private File lastFile;
    private JPanel menuPanel;

    public GameMenu(){
        createMenuPanel();
    }

    public JButton dieButton() {
        JButton button = new JButton("Lancar Dado");
        button.setBounds(25, 450, 200, 50);
        return button;
    }

    public JLabel textLabel() {
        JLabel label = new JLabel("Á Jogar:");
        label.setBounds(55, 220, 200, 50);
        Font font = label.getFont();
        float tamanhoFonte = font.getSize() + 20; // aumentar o tamanho da fonte em 4 pontos
        Font novaFonte = font.deriveFont(tamanhoFonte);
        label.setFont(novaFonte);
        return label;
    }

    public JButton newGameButton() {
        JButton button = new JButton("Novo Jogo");
        button.setBounds(25, 20, 200, 50);
        return button;
    }

    public JButton loadGameButton() {
        JButton button = new JButton("Carregar Jogo");
        button.setBounds(25, 90, 200, 50);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lastFile = fileChooser.getSelectedFile();
                }
            }
        });
        
        return button;
    }

    public JButton saveGameButton() {
        JButton button = new JButton("Salvar Jogo");
        button.setBounds(25, 160, 200, 50);
        return button;
    }

    public File getFile(){
        return lastFile;
    }

    private void createMenu(JPanel menuPanel) {
        menuPanel.add(dieButton());
        menuPanel.add(newGameButton());
        menuPanel.add(loadGameButton());
        menuPanel.add(saveGameButton());
        menuPanel.add(textLabel());
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(250, 630));
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setLayout(null);
        createMenu(menuPanel);
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

}
