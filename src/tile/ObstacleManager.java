package tile;

import Entity.Player;
import main.GamePanel;
import main.Sound;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;

// CLASS YANG MENGATUR OBSTACLE

public class ObstacleManager {
//    Attribute
    private GamePanel gp;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Land> listLand;
    private Player player1, player2;
    private boolean player1Died, player2Died;
    private int x, y, updateCounter, spawnCounter, speed;
    private final int defaultSpeed = 5;
    private BackgroundManager bg;
    private BufferedImage selectedBackground;

//    Constructor
    public ObstacleManager(GamePanel gp, Player player1, Player player2){
        this.gp = gp;
        x = gp.getTileSize()*4;
        y = gp.getTileSize()*3;
        obstacles = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        spawnCounter = 0;
        updateCounter = 0;
        speed = defaultSpeed;

        listLand = new ArrayList<>();
        setLand();

        // Muat gambar latar belakang
//        try {
//            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/tiles/sky-2.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    Getter Setter

    public GamePanel getGp() {
        return gp;
    }
    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
    public void setObstacles(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }
    public ArrayList<Land> getListLand() {
        return listLand;
    }
    public void setListLand(ArrayList<Land> listLand) {
        this.listLand = listLand;
    }
    public Player getPlayer1() {
        return player1;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
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
    public int getUpdateCounter() {
        return updateCounter;
    }
    public void setUpdateCounter(int updateCounter) {
        this.updateCounter = updateCounter;
    }
    public int getSpawnCounter() {
        return spawnCounter;
    }
    public void setSpawnCounter(int spawnCounter) {
        this.spawnCounter = spawnCounter;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getDefaultSpeed() {
        return defaultSpeed;
    }
    public void setLand(){
        for(int i = 0; i < 15; i++){
            Land land = new Land();
            land.setX((int) i * gp.getTileSize());
            listLand.add(land);
        }
    }

//    Method
    public void update(){
        updateCounter++;
        // Check collision pada semua obstacles
        for (Obstacle obs : obstacles) {
            checkCollision(player1, player2, obs);
        }
        // Tambah obstacle setiap satu detik
        if(updateCounter%gp.getFPS() == 1){
            spawnCounter++;
            spawnObstacle();
        }
        if (updateCounter % (gp.getFPS()*5)  == 0){
            speed++;
        }
        moveLand();
        moveObstacles();
        deleteOffscreenObstacles();
    }
    public void spawnObstacle(){
        // Random placement
        double rand = Math.random();
        int xTile = listLand.get(listLand.size()-1).getX() + gp.getTileSize()*3 + gp.getTileSize() * (int) (rand * 3);

        // Random amount
        int obsCount;
        if(speed <= 5){
            obsCount = (int) (Math.random()*2);
        } else if (speed <= 15) {
            obsCount = (int) (Math.random()*3);
        } else if (speed <= 30) {
            obsCount = (int) (Math.random()*4);
        } else if (speed <= 45) {
            obsCount = (int) (Math.random()*5);
        } else {
            obsCount = (int) (Math.random()*6);
        }

        for (int j= 0; j < obsCount; j++){
            boolean isType1 = Math.random() < 0.5;
            obstacles.add(new Obstacle(gp, xTile + gp.getTileSize() * j, gp.getTileSize()*3, isType1));
        }
    }

    public void moveObstacles(){
        for (Obstacle obs: obstacles) {
            obs.setX(obs.getX()-speed);
        }
    }
    public void moveLand(){
        for(Land land : listLand){
            land.setX(land.getX()-speed);
        }
        Land firstLand = listLand.get(0);
        if(firstLand.getX() + gp.getTileSize() <  - (gp.getTileSize()*2)){
            firstLand.setX(listLand.get(listLand.size()-1).getX() + gp.getTileSize());
            listLand.add(firstLand);
            listLand.remove(0);
        }
    }
    public void deleteOffscreenObstacles(){
        obstacles.removeIf(obs -> obs.getX() < -(gp.getTileSize()+10));
    }
    public void checkCollision(Player player1, Player player2, Obstacle obs){
        int p1x1 = player1.getSolidAreaX() ;
        int p1x2 = player1.getSolidAreaX() + player1.getSolidArea().width;
        int p1y1 = player1.getSolidAreaY();
        int p1y2 = player1.getSolidAreaY() + player1.getSolidArea().height;

        int p2x1 = player2.getSolidAreaX() ;
        int p2x2 = player2.getSolidAreaX() + player2.getSolidArea().width;
        int p2y1 = player2.getSolidAreaY();
        int p2y2 = player2.getSolidAreaY() + player2.getSolidArea().height;

        int ox1 = obs.getSolidAreaX();
        int ox2 = obs.getSolidAreaX() + obs.getSolidArea().width;
        int oy1 = obs.getSolidAreaY();
        int oy2 = obs.getSolidAreaY() + obs.getSolidArea().height;

        // Intersection antara player dan obstacle
        if (p1x1 < ox2 && p1x2 > ox1 && p1y1 < oy2 && p1y2 > oy1){
            player1Died = true;
        }
        if (p2x1 < ox2 && p2x2 > ox1 && p2y1 < oy2 + gp.getTileSize()*5 && p2y2 > oy1 + + gp.getTileSize()*5){
            player2Died = true;
        }

        if (player1Died && player2Died){
            // Both player died at the same time
            player1Died = false;
            player2Died = false;
            gp.stopMusic();
            gp.playSFX(3);
            player1.playDamageAnimation();
            player2.playDamageAnimation();
            gp.setWinner("NOBODY");
            gp.setGameState(gp.getGameOverState());
        } else if (player1Died) {
            player1Died = false;
            gp.stopMusic();
            gp.playSFX(3);
            player1.playDamageAnimation();
            gp.setWinner(player2.getSkin().getColor());
            gp.setGameState(gp.getGameOverState());
        } else if (player2Died) {
            player2Died = false;
            gp.stopMusic();
            gp.playSFX(3);
            player2.playDamageAnimation();
            gp.setWinner(player1.getSkin().getColor());
            gp.setGameState(gp.getGameOverState());
        }
    }

    public void draw(Graphics2D g2) {
        // Mengatur latar belakang
        BufferedImage selectedBackground = gp.getSelectedBackground();

        if (selectedBackground != null) {
            g2.drawImage(selectedBackground, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        } else {
            g2.drawImage(selectedBackground, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
        }

        // Loop untuk menampilkan setiap rintangan di ArrayList obstacles
        for (Obstacle obs: obstacles) {
            if (obs.isType1()) {
                g2.drawImage(obs.getImage(), obs.getX(), obs.getY(), gp.getTileSize(), gp.getTileSize(), null);
                g2.drawImage(obs.getImage(), obs.getX(), obs.getY() + gp.getTileSize() * 5, gp.getTileSize(), gp.getTileSize(), null);
            } else {
                g2.drawImage(obs.getImage2(), obs.getX(), obs.getY(), gp.getTileSize(), gp.getTileSize(), null);
                g2.drawImage(obs.getImage2(), obs.getX(), obs.getY() + gp.getTileSize() * 5, gp.getTileSize(), gp.getTileSize(), null);
            }

            // Debug: Menggambar hitbox rintangan
//            g2.setColor(Color.RED);
//            g2.drawRect(obs.getX(), obs.getY(), gp.getTileSize(), gp.getTileSize());
//            g2.drawRect(obs.getX(), obs.getY() + gp.getTileSize() * 5, gp.getTileSize(), gp.getTileSize());
        }

        for (Land land : listLand) {
            g2.drawImage(land.getImage(), land.getX(), gp.getTileSize() * 4, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawImage(land.getImage(), land.getX(), gp.getTileSize() * 9, gp.getTileSize(), gp.getTileSize(), null);

            // Debug: Menggambar hitbox land
//            g2.setColor(Color.BLUE);
//            g2.drawRect(land.getX(), gp.getTileSize() * 4, gp.getTileSize(), gp.getTileSize());
//            g2.drawRect(land.getX(), gp.getTileSize() * 9, gp.getTileSize(), gp.getTileSize());
        }

        // Debug: Menggambar hitbox pemain (asumsikan objek pemain dapat diakses)
//        g2.setColor(Color.GREEN);
//        g2.drawRect(player1.getX(), player1.getY(), gp.getTileSize(), gp.getTileSize());
//        g2.drawRect(player2.getX(), player2.getY(), gp.getTileSize(), gp.getTileSize());
    }


    public void resetObstacles(){
        obstacles.clear();
        x = gp.getTileSize()*4;
        y = gp.getTileSize()*3;
        speed = defaultSpeed;
    }

}
