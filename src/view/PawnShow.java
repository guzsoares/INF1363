package view;

import java.awt.*;
import model.ModelAPI;
import java.util.List;
import java.util.ArrayList;

class PawnShow {
    private final int pawnSize = 20;
    private ModelAPI modelAPI = ModelAPI.getInstance();
    final int boardSize = 600;
    final int TAMANHO_QUADRADO = (boardSize) / 15;
    final int offset = 10;

    public void drawPawns(Graphics2D g2d){
        int[] numPawns = modelAPI.getInitialSquaresNumPawns();

        if (numPawns[0] > 0){
            drawPawn(g2d, Color.GREEN, TAMANHO_QUADRADO * 10 + offset, TAMANHO_QUADRADO + offset);
            if (numPawns[0] > 1){
                drawPawn(g2d, Color.GREEN, TAMANHO_QUADRADO * 13 + offset, TAMANHO_QUADRADO + offset);
            }
            if (numPawns[0] > 2){
                drawPawn(g2d, Color.GREEN, TAMANHO_QUADRADO * 10 + offset, TAMANHO_QUADRADO * 4 + offset);
            }
            if (numPawns[0] > 3){
                drawPawn(g2d, Color.GREEN, TAMANHO_QUADRADO * 13 + offset, TAMANHO_QUADRADO * 4 + offset);
            }
        }

        if (numPawns[1] > 0){
            drawPawn(g2d, Color.YELLOW, TAMANHO_QUADRADO * 10 + offset, TAMANHO_QUADRADO * 10 + offset);
            if (numPawns[1] > 1){
                drawPawn(g2d, Color.YELLOW, TAMANHO_QUADRADO * 13 + offset, TAMANHO_QUADRADO * 10 + offset);
            }
            if (numPawns[1] > 2){
                drawPawn(g2d, Color.YELLOW, TAMANHO_QUADRADO * 10 + offset, TAMANHO_QUADRADO * 13 + offset);
            }
            if (numPawns[1] > 3){
                drawPawn(g2d, Color.YELLOW, TAMANHO_QUADRADO * 13 + offset, TAMANHO_QUADRADO * 13 + offset);
            }
        }

        if (numPawns[2] > 0){
            drawPawn(g2d, Color.BLUE, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO * 10 + offset);
            if (numPawns[2] > 1){
                drawPawn(g2d, Color.BLUE, TAMANHO_QUADRADO * 4 + offset, TAMANHO_QUADRADO * 10 + offset);
            }
            if (numPawns[2] > 2){
                drawPawn(g2d, Color.BLUE, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO * 13 + offset);
            }
            if (numPawns[2] > 3){
                drawPawn(g2d, Color.BLUE, TAMANHO_QUADRADO * 4 + offset, TAMANHO_QUADRADO * 13 + offset);
            }
        }

        if (numPawns[3] > 0){
            drawPawn(g2d, Color.RED, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO + offset);
            if (numPawns[3] > 1){
                drawPawn(g2d, Color.RED, TAMANHO_QUADRADO * 4 + offset, TAMANHO_QUADRADO + offset);
            }
            if (numPawns[3] > 2){
                drawPawn(g2d, Color.RED, TAMANHO_QUADRADO + offset, TAMANHO_QUADRADO * 4 + offset);
            }
            if (numPawns[3] > 3){
                drawPawn(g2d, Color.RED, TAMANHO_QUADRADO * 4 + offset, TAMANHO_QUADRADO * 4 + offset);
            }
        }

        int[] pawnsPosition = modelAPI.pawnsPosition();
        Color[] pawnsColor = modelAPI.pawnsColor();

        List<Integer> duplicatePawns = getDuplicateIndices(pawnsPosition);

        for (int i = 0; i < 16; i++){

            if (pawnsPosition[i] >= 0){
                Point coords = convertPosition(pawnsPosition[i]);
                drawPawn(g2d, pawnsColor[i], coords.x + offset, coords.y + offset);
            }
        }

        if (!duplicatePawns.isEmpty()){
            while (duplicatePawns.size() != 0){
                int id1 = duplicatePawns.get(0);
                int id2 = duplicatePawns.get(1);

                if (pawnsColor[id1] == pawnsColor[id2]){
                    Point coords = convertPosition(pawnsPosition[id1]);
                    drawBarreira(g2d, pawnsColor[id1], coords.x, coords.y);
                } else {
                    Point coords = convertPosition(pawnsPosition[id1]);
                    drawAbrigo(g2d, pawnsColor[id1], pawnsColor[id2], coords.x, coords.y);
                }

                duplicatePawns.remove(1);
                duplicatePawns.remove(0);

            }
        }


    }

    private void drawPawn(Graphics2D g2d, Color pawnColor, int x, int y){
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x - 2, y - 2, pawnSize + 4, pawnSize + 4);
        g2d.setColor(pawnColor);
        g2d.fillOval(x, y, pawnSize, pawnSize);
    }

    private Point convertPosition(int position){
        Point coords = new Point();
        if (position >= 0 && position <= 4){
            coords.x = TAMANHO_QUADRADO * 8;
            coords.y = TAMANHO_QUADRADO + TAMANHO_QUADRADO * position;
        } else if (position >= 5 && position <= 10){
            coords.x = TAMANHO_QUADRADO * 9 + TAMANHO_QUADRADO * (position - 5);
            coords.y = TAMANHO_QUADRADO * 6;
        } else if (position == 11){
            coords.x = TAMANHO_QUADRADO * 14;
            coords.y = TAMANHO_QUADRADO * 7;
        } else if (position >= 12 && position <= 17){
            coords.x = TAMANHO_QUADRADO * 9 - TAMANHO_QUADRADO * (position - 17);
            coords.y = TAMANHO_QUADRADO * 8;
        } else if (position >= 18 && position <= 23){
            coords.x = TAMANHO_QUADRADO * 8;
            coords.y = TAMANHO_QUADRADO * 9 + TAMANHO_QUADRADO * (position - 18);
        } else if (position == 24){
            coords.x = TAMANHO_QUADRADO * 7;
            coords.y = TAMANHO_QUADRADO * 14;
        } else if (position >= 25 && position <= 30){
            coords.x = TAMANHO_QUADRADO * 6;
            coords.y = TAMANHO_QUADRADO * 9 - TAMANHO_QUADRADO * (position - 30);
        } else if (position >= 31 && position <= 36){
            coords.x = TAMANHO_QUADRADO * 5 - TAMANHO_QUADRADO * (position - 31);
            coords.y = TAMANHO_QUADRADO * 8;
        } else if (position == 37){
            coords.x = 0;
            coords.y = TAMANHO_QUADRADO * 7;
        } else if (position >= 38 && position <= 43){
            coords.x = 0 + TAMANHO_QUADRADO * (position - 38);
            coords.y = TAMANHO_QUADRADO * 6;
        } else if (position >= 44 && position <= 49){
            coords.x = TAMANHO_QUADRADO * 6;
            coords.y = TAMANHO_QUADRADO * 5 - TAMANHO_QUADRADO * (position - 44);
        } else if (position >= 50 && position <= 51){
            coords.x = TAMANHO_QUADRADO * 6 + TAMANHO_QUADRADO * (position - 49);
            coords.y = 0;
        } else if (position >= 100 && position <= 105) {
            coords.x = TAMANHO_QUADRADO * 7;
            coords.y = TAMANHO_QUADRADO + TAMANHO_QUADRADO * (position - 100);
        } else if (position >= 200 && position <= 205) {
            coords.x = TAMANHO_QUADRADO * 8 - TAMANHO_QUADRADO * (position - 205);
            coords.y = TAMANHO_QUADRADO * 7;
        } else if (position >= 400 && position <= 405) {
            coords.x = TAMANHO_QUADRADO + TAMANHO_QUADRADO * (position - 400);
            coords.y = TAMANHO_QUADRADO * 7;
        } else if (position >= 300 && position <= 305) {
            coords.x = TAMANHO_QUADRADO * 7;
            coords.y = TAMANHO_QUADRADO * 8 - TAMANHO_QUADRADO * (position - 305);
        }

        return coords;
    }

    private void drawBarreira(Graphics2D g2d, Color pawnColor, int x, int y){
        g2d.setColor(pawnColor);

        g2d.fillOval(x + 6, y + 6, pawnSize + 8, pawnSize + 8);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + 8, y + 8, pawnSize + 4, pawnSize + 4);

        g2d.setColor(pawnColor);
        g2d.fillOval(x + 10, y + 10, pawnSize, pawnSize);
    }

    private void drawAbrigo(Graphics2D g2d, Color pawnColor1, Color pawnColor2, int x , int y){
        g2d.setColor(pawnColor1);

        g2d.fillOval(x + 6, y + 6, pawnSize + 8, pawnSize + 8);

        g2d.setColor(pawnColor2);
        g2d.fillOval(x + 10, y + 10, pawnSize, pawnSize);
    }

    public List<Integer> getDuplicateIndices(int[] array) {
        List<Integer> duplicateIndices = new ArrayList<>();

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j] && array[i] > 0 && array[i] < 52) {
                    duplicateIndices.add(i); // Adiciona o índice ao resultado
                    duplicateIndices.add(j);
                }
            }
        }

        return duplicateIndices;
    }
    
}
