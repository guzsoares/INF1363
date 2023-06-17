package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
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
    private int combobox_val;
    private JComboBox cb;

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
    public JButton debugdieButton() {
        JButton button = new JButton("Lancar Dado debug");
        button.setBounds(25, 220, 200, 50);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (modelAPI.isGameOver() == true){
                    return;
                }
                setComboboxnum((int)cb.getSelectedItem());
                modelAPI.DebugRoll(combobox_val);
                dieImage = dieShow.showDiceImage(dieNumber);
            }
        });
        return button;
    }
    public void setComboboxnum(int a) {
 	   combobox_val = a;
 	   
    }
   public JComboBox<Integer> diceValueComboBox() {
	   Integer[] diceValues = {1, 2, 3, 4, 5, 6}; // Valores possíveis do dado
       JComboBox<Integer> comboBox = new JComboBox<>(diceValues);
       comboBox.setBounds(25, 520, 200, 50);
       
       
       return comboBox;
   }
  


    public JButton newGameButton() {
        JButton button = new JButton("Novo Jogo");
        button.setBounds(25, 20, 200, 50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGameAction();
            }
        });
        return button;
    }
    	
    public JButton loadGameButton() {
        JButton button = new JButton("Carregar Jogo");
        button.setBounds(25, 90, 200, 50);
        
      /*  button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                	 File f = fileChooser.getSelectedFile();
                	 String filepath = f.getPath();
                    //TODO: FUNCAO DE CARREGAMENTO DE ARQUIVO
                	
                    System.out.println(fileChooser.getSelectedFile());
                    System.out.println("Carregar arquivo selecionado");
                }
            }
        }*/
    
        
        return button;
    }
    
    public JButton saveGameButton() {
        JButton button = new JButton("Salvar Jogo");
        button.setBounds(25, 160, 200, 50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(button);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                 
                    System.out.println("Jogo salvo em: " + filePath);
                    
                }
            }
        });
        return button;
    }
    private void createMenu(JPanel menuPanel) {
        
        menuPanel.add(newGameButton());
        menuPanel.add(loadGameButton());
        menuPanel.add(saveGameButton());
        menuPanel.add(dieButton());
        if(modelAPI.getDebug() == true) {
        	cb = diceValueComboBox() ;
        	menuPanel.add(cb);
        	menuPanel.add(debugdieButton());
        }
    
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

    public void newGameAction(){
        modelAPI.createGame();
        turnColor = Color.GRAY;
        dieNumber = 0;
        dieImage = null;
        viewAPI.redraw();

        modelAPI.addSubscriber(viewAPI.getMenuSubscriber());
        modelAPI.addBoardSubscriber(viewAPI.getBoardSubscriber());
    }

}
