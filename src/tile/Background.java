package tile;

import Entity.PlayerSkin;
import main.GamePanel;
import main.KeyHandler;
import tile.BackgroundManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BackgroundManager choice;
    private GamePanel gp;
    private KeyHandler keyHandler;
    private BufferedImage image;
    private BackgroundManager backgroundManager;

    public Background(GamePanel gp, KeyHandler keyHandler, String backgroundChoice) {
//        skin = new PlayerSkin(defaultColor);
        choice = new BackgroundManager(backgroundChoice);
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.backgroundManager = new BackgroundManager(backgroundChoice);

//        this.image = backgroundManager.getBackground1();
    }

    public BackgroundManager getChoice() {
        return choice;
    }

    public void setChoice(BackgroundManager choice) {
        this.choice = choice;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BackgroundManager getBackgroundManager() {
        return backgroundManager;
    }

    public void setBackgroundManager(BackgroundManager backgroundManager) {
        this.backgroundManager = backgroundManager;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
    }

}


