package view;

import javax.swing.*;
import java.awt.*;
 
class BoardBuilder{
    private JPanel boardPanel;
    private final int boardSize = 600;
    private final int offset = 0;

    public BoardBuilder() {
        createBoardPanel();
    }

    private void createBoardPanel() {
        boardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                drawBoard(g2d);
            }
        };
        boardPanel.setPreferredSize(new Dimension(600, 600));
        boardPanel.setBackground(Color.WHITE);
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public void drawBoard(Graphics2D g2d){
        final int TAMANHO_QUADRADO = (boardSize) / 15;
        final int QUADRADO_MAIOR = TAMANHO_QUADRADO * 6;

        drawInitialAndFinals(g2d, TAMANHO_QUADRADO);
        drawGrid(g2d, TAMANHO_QUADRADO);
        drawAbrigo(g2d, TAMANHO_QUADRADO);
        drawSpawns(g2d, QUADRADO_MAIOR, TAMANHO_QUADRADO);
        drawTriangles(g2d, TAMANHO_QUADRADO);
    }

    private void drawSpawns(Graphics2D g2d, int QUADRADO_MAIOR, int TAMANHO_QUADRADO){ // Função para desenhar os quadrados iniciais
        g2d.setColor(Color.RED);
        g2d.fillRect(0, offset, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, offset, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO + offset, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO + offset, QUADRADO_MAIOR, QUADRADO_MAIOR);


        for (int i = 0; i < 4; i++){

            g2d.setColor(Color.WHITE);
            g2d.fillOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
    
            g2d.setColor(Color.BLACK);
            g2d.drawOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
    }

    private void drawGrid(Graphics2D g2d, int TAMANHO_QUADRADO){
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int x = i * TAMANHO_QUADRADO;
                int y = j * TAMANHO_QUADRADO;
                g2d.drawRect(x, y + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            }
        }

    }

    private void drawAbrigo(Graphics2D g2d, int TAMANHO_QUADRADO){
        g2d.fillRect(TAMANHO_QUADRADO, TAMANHO_QUADRADO * 8 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO * 6 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO * 13 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
    }

    private void drawTriangles(Graphics2D g2d, int TAMANHO_QUADRADO){
        g2d.setColor(Color.GREEN);

        int[] greenXPoints = {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] greenYPoints= {6 * TAMANHO_QUADRADO + offset, 6 * TAMANHO_QUADRADO + offset, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2 + offset};

        g2d.fillPolygon(greenXPoints, greenYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(greenXPoints,greenYPoints, 3);

        
        g2d.setColor(Color.RED);

        int[] redXPoints = {6 * TAMANHO_QUADRADO, 6 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] redYPoints= {6 * TAMANHO_QUADRADO + offset, 9 * TAMANHO_QUADRADO + offset, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2 + offset};

        g2d.fillPolygon(redXPoints, redYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(redXPoints,redYPoints, 3);


        g2d.setColor(Color.BLUE);

        int[] blueXPoints = {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] blueYPoints= {9 * TAMANHO_QUADRADO + offset, 9 * TAMANHO_QUADRADO + offset, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2 + offset};

        g2d.fillPolygon(blueXPoints, blueYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(blueXPoints,blueYPoints, 3);


        g2d.setColor(Color.YELLOW);

        int[] yellowXPoints = {9 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] yellowYPoints= {6 * TAMANHO_QUADRADO + offset, 9 * TAMANHO_QUADRADO + offset, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2 + offset};

        g2d.fillPolygon(yellowXPoints, yellowYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(yellowXPoints,yellowYPoints, 3);
    }

    private void drawInitialAndFinals(Graphics2D g2d, int TAMANHO_QUADRADO){
        // desenha retas finais e casas de saida
        g2d.setColor(Color.RED);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect((i * TAMANHO_QUADRADO) + TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }

        g2d.fillRect(TAMANHO_QUADRADO, TAMANHO_QUADRADO * 6 + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.GREEN);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect(7 * TAMANHO_QUADRADO,(i * TAMANHO_QUADRADO) + TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect((i * TAMANHO_QUADRADO) + 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO * 8  + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.BLUE);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect(7 * TAMANHO_QUADRADO,(i * TAMANHO_QUADRADO) + 9 * TAMANHO_QUADRADO  + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO * 13  + offset, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
    }

}