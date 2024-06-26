package Entity;

import main.GamePanel;
import main.KeyHandler;

public class Player2 extends Player{
    public Player2(GamePanel gp, KeyHandler keyHandler, String color) {
        super(gp, keyHandler, color);
        floor = (getGp().getTileSize()+2)*7;
    }

    @Override
    public void setDefaultValues(){
        setX(getGp().getTileSize()*2);
        setY((getGp().getTileSize()+2)*7);
        setSpeed(5);
        setDirection("run");
        isJumping = false;
        onAir = false;
    }
    @Override
    public void update(){
        //System.out.println("Y = "+y+"  v = "+velocity);
        if(getKeyHandler().isUpPressed2() || isJumping){
            setDirection("up");
        } else if (getKeyHandler().isDownPressed2()){
            setDirection("down");
        } else{
            setDirection("run");
        }
        setSpriteCounter(getSpriteCounter()+1);
        if (getSpriteCounter() > 5){
            setSpriteNum(getSpriteNum()+1);
            if (getSpriteNum() > 5){
                setSpriteNum(0);
            }
            setSpriteCounter(0);
            setJumpCounter(getJumpCounter()+1);
        }

        // Kode dibawah ini mengatur dino lompat
        if (getKeyHandler().isUpPressed2() && onFloor() && !isJumping){
            // saat tekan naik & player di lantai:
            getGp().playSFX(1);
            velocity = 20;
            isJumping = true;
        }
        if (velocity > 0){
            // cat lompat
            move(getSpeed());
            velocity--;
            onAir = true;
        }
        if (velocity == 0 && onAir){
            // saat di puncak lompat
            velocity = -20;
        }
        if (velocity < 0 ){
            // cat turun
            move(-getSpeed());
            velocity++;
            onAir = false;
        }
        if (velocity == 0 && onFloor()){
            isJumping = false;
        }
        if (getY() > floor){
            setY((int) floor);
        }

    }
}
