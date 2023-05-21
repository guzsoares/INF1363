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
        String dir = System.getProperty("user.dir");
        return dir + "/Imagens/Dado" + diceValue + ".png";
    }
}