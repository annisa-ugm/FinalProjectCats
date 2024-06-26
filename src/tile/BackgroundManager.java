package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import tile.Background;
import main.KeyHandler;

public class BackgroundManager {

    private BufferedImage background1, background2, background3;
    //    private BufferedImage dead1, dead2, dead3;

    private static String[] backgroundChoice = {"malam", "siang", "colorfull"};
    private ArrayList<BufferedImage> background;
    private String choice;
    private BufferedImage choices;


    public BackgroundManager(String choice) {
        this.choice = choice.toLowerCase();
        getBackgroundImage();
    }

    public static String[] getBackgroundChoice() {
        return backgroundChoice;
    }
//
//    public static void setBackgroundChoice(String[] backgroundChoice) {
//        BackgroundManager.backgroundChoice = backgroundChoice;
//    }

    public BufferedImage getBackground1() {
        return background1;
    }

    public void setBackground1(BufferedImage background1) {
        this.background1 = background1;
    }

    public BufferedImage getBackground2() {
        return background2;
    }

    public void setBackground2(BufferedImage background2) {
        this.background2 = background2;
    }

    public BufferedImage getBackground3() {
        return background3;
    }

    public void setBackground3(BufferedImage background3) {
        this.background3 = background3;
    }

    public ArrayList<BufferedImage> getBackground() {
        return background;
    }

    public void setBackground(ArrayList<BufferedImage> background) {
        this.background = background;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void getBackgroundImage() {
        try {
            background1 = ImageIO.read(getClass().getResourceAsStream("/player/background_"+choice+"_01.png"));
            background2 = ImageIO.read(getClass().getResourceAsStream("/player/background_"+choice+"_02.png"));
            background3 = ImageIO.read(getClass().getResourceAsStream("/player/background_"+choice+"_03.png"));
            background = new ArrayList<>(Arrays.asList(background1, background2, background3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setChoices(BufferedImage choice) {
        this.choices = choice;
        getBackgroundImage();
    }

}