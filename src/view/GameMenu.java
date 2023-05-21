package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameMenu {
    private Image dieImage;
    private File lastFile;
    private JPanel menuPanel;
    private DieShow dieShow = new DieShow();
    private Color turnColor = Color.RED;
    private int dieNumber = 4;

    public GameMenu(){
        createMenuPanel();
    }

    public JButton dieButton() {
        JButton button = new JButton("Lancar Dado");
        button.setBounds(25, 450, 200, 50);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dieImage = dieShow.showDiceImage(dieNumber);
            }
        });
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
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Realizar criação de novo jogo!");
            }
        });
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
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Realizar salvamento do jogo!");
            }
        });
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
        menuPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(turnColor);
                g2d.fillRect(60,310,120,120);
                g2d.setColor(Color.GRAY);
                g2d.fillRect(70,320,100,100);
                
                // Desenhando a imagem
                g.drawImage(dieImage, 70, 320, null); // Desenha a imagem nas coordenadas (50, 50)
            }
        };
        menuPanel.setPreferredSize(new Dimension(250, 630));
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setLayout(null);
        createMenu(menuPanel);
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void setColor(Color color){
        this.turnColor = color;
    }

    public void setNumber(int Number){
        this.dieNumber = Number;
    }

}
