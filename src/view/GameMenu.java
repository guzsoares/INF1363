package view;

import javax.swing.*;

import controller.MenuSubscriber;
import model.ModelAPI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GameMenu implements MenuSubscriber{
    private ModelAPI modelAPI = ModelAPI.getInstance();
    private ViewAPI viewAPI = ViewAPI.getInstance();
    private DieShow dieShow = new DieShow();

    private MenuSubscriber subscriber;
    private Image dieImage;
    private JPanel menuPanel;
    private Color turnColor = Color.GRAY;
    private int dieNumber = 0;

    public GameMenu(){
        this.subscriber = this;
        createMenuPanel();
    }

    public JButton dieButton() {
        JButton button = new JButton("Lancar Dado");
        button.setBounds(25, 450, 200, 50);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (modelAPI.isGameOver() == true){
                    return;
                }

                modelAPI.rollDie();
                dieImage = dieShow.showDiceImage(dieNumber);
            }
        });
        return button;
    }

    public JLabel textLabel() {
        JLabel label = new JLabel("Á Jogar:");
        label.setBounds(55, 220, 200, 50);
        Font font = label.getFont();
        float tamanhoFonte = font.getSize() + 20;
        Font novaFonte = font.deriveFont(tamanhoFonte);
        label.setFont(novaFonte);
        return label;
    }

    public JButton newGameButton() {
        JButton button = new JButton("Novo Jogo");
        button.setBounds(25, 20, 200, 50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelAPI.createGame();
                turnColor = Color.GRAY;
                dieNumber = 0;
                dieImage = null;
                viewAPI.redraw();

                modelAPI.addSubscriber(viewAPI.getMenuSubscriber());
                modelAPI.addBoardSubscriber(viewAPI.getBoardSubscriber());
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
                    //TODO: FUNCAO DE CARREGAMENTO DE ARQUIVO
                    System.out.println(fileChooser.getSelectedFile());
                    System.out.println("Carregar arquivo selecionado");
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
                //TODO: FUNCAO DE SALVAR JOGO
                System.out.println("Realizar salvamento do jogo!");
            }
        });
        return button;
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

    @Override
    public void updateDie(int newValue){
        this.dieNumber = newValue;
        viewAPI.redraw();
    }

    @Override
    public void updateTurn(Color color){
        if (modelAPI.isGameOver() == true){
            viewAPI.showMessage();
            return;
        }
        viewAPI.redraw();
        this.turnColor = color;
    }

    public MenuSubscriber getSubscriber(){
        return subscriber;
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
