package view;

import java.awt.*;
import model.ModelAPI;

class PawnShow {
    private final int pawnSize = 20;
    private ModelAPI modelAPI = ModelAPI.getInstance();
    private int[] numPawns = modelAPI.getInitialSquaresNumPawns();

    public void drawPawns(Graphics2D g2d){
        if (numPawns[0] > 0){
            drawPawn(g2d, Color.GREEN, 410, 50);
            if (numPawns[0] > 1){
                drawPawn(g2d, Color.GREEN, 530, 50);
            }
            if (numPawns[0] > 2){
                drawPawn(g2d, Color.GREEN, 410, 170);
            }
            if (numPawns[0] > 3){
                drawPawn(g2d, Color.GREEN, 530, 170);
            }
        }

        if (numPawns[1] > 0){
            drawPawn(g2d, Color.YELLOW, 410, 410);
            if (numPawns[1] > 1){
                drawPawn(g2d, Color.YELLOW, 530, 410);
            }
            if (numPawns[1] > 2){
                drawPawn(g2d, Color.YELLOW, 410, 530);
            }
            if (numPawns[1] > 3){
                drawPawn(g2d, Color.YELLOW, 530, 530);
            }
        }

        if (numPawns[2] > 0){
            drawPawn(g2d, Color.BLUE, 50, 410);
            if (numPawns[2] > 1){
                drawPawn(g2d, Color.BLUE, 170, 410);
            }
            if (numPawns[2] > 2){
                drawPawn(g2d, Color.BLUE, 50, 530);
            }
            if (numPawns[2] > 3){
                drawPawn(g2d, Color.BLUE, 170, 530);
            }
        }

        if (numPawns[3] > 0){
            drawPawn(g2d, Color.RED, 50, 50);
            if (numPawns[3] > 1){
                drawPawn(g2d, Color.RED, 170, 50);
            }
            if (numPawns[3] > 2){
                drawPawn(g2d, Color.RED, 50, 170);
            }
            if (numPawns[3] > 3){
                drawPawn(g2d, Color.RED, 170, 170);
            }
        }
    }

    private void drawPawn(Graphics2D g2d, Color pawnColor, int x, int y){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x - 2, y - 2, pawnSize + 4, pawnSize + 4);
        g2d.setColor(pawnColor);
        g2d.fillOval(x, y, pawnSize, pawnSize);
    }
    
}
