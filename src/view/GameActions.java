package view;

import javax.swing.*;
import java.awt.*;

class GameActions {
    private JPanel actionPanel;
    int boardSize = 600;
    int squareSize = boardSize/15;

    public GameActions(){
        createActionPanel();
    }
    
    private void createActionPanel() {
        actionPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                createAction(g2d);
            }
        };
        actionPanel.setPreferredSize(new Dimension(squareSize, squareSize));
        actionPanel.setLayout(null);
    }

    private void createAction(Graphics2D g2d){
        drawAbrigo(g2d, Color.BLACK, Color.BLACK);
    }

    private void drawBarreira(Graphics2D g2d, Color pawnColor){
        g2d.setColor(pawnColor);

        g2d.fillOval(0, 0, squareSize, squareSize);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(5, 5, squareSize - 10, squareSize - 10);

        g2d.setColor(pawnColor);
        g2d.fillOval(10, 10, squareSize - 20, squareSize - 20);
    }

    private void drawAbrigo(Graphics2D g2d, Color pawnColor1, Color pawnColor2){
        g2d.setColor(pawnColor1);

        g2d.fillOval(0, 0, squareSize, squareSize);

        g2d.setColor(pawnColor2);
        g2d.fillOval(5, 5, squareSize - 10, squareSize - 10);
    }

    public JPanel getActionPanel() {
        return actionPanel;
    }
}
