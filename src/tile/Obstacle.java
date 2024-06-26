package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  Obstacle {
//    Attribute
    private BufferedImage image;
    private BufferedImage image2;
    private Rectangle solidArea;
    private int x, y, width, height;
    private GamePanel gp;

//    Constructor
private boolean isType1;

    public Obstacle(GamePanel gp, int x, int y, boolean isType1) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.width = gp.getTileSize();
        this.height = gp.getTileSize();
        this.isType1 = isType1;

        // SolidArea merupakan collider box obstacle
        this.solidArea = new Rectangle();
        solidArea.x = (int) (gp.getTileSize()*0.2*0.5);
        solidArea.y = getSolidAreaY();
        solidArea.width = (int) (gp.getTileSize()*0.9);
        solidArea.height = (int) (gp.getTileSize()*0.2);

        getObstacleImage();
        getObstacleImage2();
    }


//    Getter Setter
    public BufferedImage getImage() {
        return image;
    }
    public BufferedImage getImage2() {
        return image2;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public void setImage2(BufferedImage image2) {
        this.image2 = image2;
    }
    public Rectangle getSolidArea() {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public GamePanel getGp() {
        return gp;
    }
    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
    public boolean isType1() {
        return isType1;
    }
    public void getObstacleImage(){
        // Me-load image
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/obsn2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void getObstacleImage2(){
        // Me-load image 2
        try {
            this.image2 = ImageIO.read(getClass().getResourceAsStream("/tiles/obsn3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Me-return posisi solidArea pada layar
    public int getSolidAreaX(){
        return x;
    }
    public int getSolidAreaY(){
        return (int) ( y + (0.8*gp.getTileSize()));
    }
}
