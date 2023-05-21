package view;

import javax.swing.*;
import java.awt.*;

class DieShow{
    private Image diceImage;

    public Image showDiceImage(int diceValue) {
        ImageIcon imageIcon = new ImageIcon(getDiceImagePath(diceValue));
        diceImage = imageIcon.getImage();
        return diceImage;
    }

    private String getDiceImagePath(int diceValue) {
        // Substitua "caminho/para/as/imagens/" pelo caminho real onde as imagens do dado estão armazenadas
        return "/Users/gustavo/Documents/PUC-Rio/INF1363/Imagens/Dado" + diceValue + ".png";
    }
}