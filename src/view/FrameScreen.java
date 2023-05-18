package view;

import javax.swing.*;
import java.awt.*;
 
class FrameScreen extends JFrame{
    private final int xScreen = 850;
    public final int yScreen = 600;
    private final int xMenu = 600;
    private final int yMenu = 600;

    private final int TAMANHO_QUADRADO = yScreen / 15;
    private final int QUADRADO_MAIOR = TAMANHO_QUADRADO * 6;

    public FrameScreen() {
        setTitle("Ludo, O Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(xScreen, yScreen);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        // Configuração de antialiasing para melhorar a qualidade gráfica
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // desenha fundo do menu
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(xMenu, 0, xScreen - xMenu, yMenu);

        // desenha retas finais e casas de saida
        g2d.setColor(Color.RED);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect((i * TAMANHO_QUADRADO) + TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }

        g2d.fillRect(TAMANHO_QUADRADO, TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.GREEN);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect(7 * TAMANHO_QUADRADO,(i * TAMANHO_QUADRADO) + TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect((i * TAMANHO_QUADRADO) + 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        g2d.setColor(Color.BLUE);
        for (int i = 0; i < 5; i ++){
            g2d.fillRect(7 * TAMANHO_QUADRADO,(i * TAMANHO_QUADRADO) + 9 * TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }
        g2d.fillRect(TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO, TAMANHO_QUADRADO);


        // Define a cor da borda
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int x = i * TAMANHO_QUADRADO;
                int y = j * TAMANHO_QUADRADO;
                g2d.drawRect(x, y, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            }
        }
        // desenha abrigos

        g2d.fillRect(TAMANHO_QUADRADO, TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO * 6, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        g2d.fillRect(TAMANHO_QUADRADO * 8, TAMANHO_QUADRADO * 13, TAMANHO_QUADRADO, TAMANHO_QUADRADO);

        // desenha quadrados iniciais

        g2d.setColor(Color.RED);
        g2d.fillRect(0, 0, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, 0, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, QUADRADO_MAIOR, QUADRADO_MAIOR);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, QUADRADO_MAIOR + 3 * TAMANHO_QUADRADO, QUADRADO_MAIOR, QUADRADO_MAIOR);


        for (int i = 0; i < 4; i++){

            g2d.setColor(Color.WHITE);
            g2d.fillOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.fillOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
    
            g2d.setColor(Color.BLACK);
            g2d.drawOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
            g2d.drawOval(TAMANHO_QUADRADO * 4 + (i % 2) * 360, TAMANHO_QUADRADO * 4 + (i / 2) * 360, TAMANHO_QUADRADO, TAMANHO_QUADRADO);
        }

        // desenha triangulos casas finais
        g2d.setColor(Color.GREEN);

        int[] greenXPoints = {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] greenYPoints= {6 * TAMANHO_QUADRADO, 6 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};

        g2d.fillPolygon(greenXPoints, greenYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(greenXPoints,greenYPoints, 3);

        
        g2d.setColor(Color.RED);

        int[] redXPoints = {6 * TAMANHO_QUADRADO, 6 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] redYPoints= {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};

        g2d.fillPolygon(redXPoints, redYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(redXPoints,redYPoints, 3);


        g2d.setColor(Color.BLUE);

        int[] blueXPoints = {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] blueYPoints= {9 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};

        g2d.fillPolygon(blueXPoints, blueYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(blueXPoints,blueYPoints, 3);


        g2d.setColor(Color.YELLOW);

        int[] yellowXPoints = {9 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};
        int[] yellowYPoints= {6 * TAMANHO_QUADRADO, 9 * TAMANHO_QUADRADO, 7 * TAMANHO_QUADRADO + TAMANHO_QUADRADO/2};

        g2d.fillPolygon(yellowXPoints, yellowYPoints, 3);

        g2d.setColor(Color.BLACK);

        g2d.drawPolygon(yellowXPoints,yellowYPoints, 3);

    }

    public static void main(String[] args) {
        FrameScreen frame = new FrameScreen();
    }

}