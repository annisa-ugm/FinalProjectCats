package main;

import Entity.PlayerSkin;
import tile.BackgroundManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    private boolean isSettingsScreen = false;

    public boolean isSettingsScreen() {
        return isSettingsScreen;
    }

    private int backgroundChoice = 0;
    public void setSettingsScreen(boolean settingsScreen) {
        isSettingsScreen = settingsScreen;
    }

    //    Attribute
    private GamePanel gp;
    private UIAssets uiAssets;
    private Graphics2D g2;
    private Font arial_16B;
    private Font pixelGamer, galaticaGrid,jungleAdventurer, minecrafter_3, fontMenu, fontCredits, fontTitle, fontGameOver;
    private int menuNum = 0;
    private int player1Skin, player2Skin = 0;
    private PlayerSkin[] playerSkins;
    private BackgroundManager[] backgrounds;
    private BufferedImage [] background;
    private int backgroundchoice = 0;

    // Title Screen Sub-state
    private int titleScreenState = 0;
    private final int titleScreenMenu = 0;
    private final int titleScreenCredits = 1;
    private final int titleScreenSettings = 2;
    private final int titleScreenCharacter = 3;
    private final int titleScreenBackground = 4;

    // pause
    private boolean soundSettingsScreen = false;

    //    Constructor
    public UI(GamePanel gp) {
        this.gp = gp;
        this.uiAssets = new UIAssets();
        arial_16B = new Font("Arial", Font.BOLD, 10);
        setupFonts();

        playerSkins = new PlayerSkin[4];
        setUpPlayerSkins();

        backgrounds = new BackgroundManager[3];
        setUpBackgrounds();

        background = new BufferedImage[3];
        // Isi array background dengan gambar latar yang sesuai
        try {
            background[0] = ImageIO.read(getClass().getResourceAsStream("/player/background_malam_01.png"));
            background[1] = ImageIO.read(getClass().getResourceAsStream("/player/background_siang_02.png"));
            background[2] = ImageIO.read(getClass().getResourceAsStream("/player/background_colorfull_03.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        fontGameOver = pixelGamer.deriveFont(Font.PLAIN,10F);
        fontMenu = jungleAdventurer.deriveFont(Font.PLAIN, 28F);
        fontCredits = galaticaGrid.deriveFont(Font.PLAIN, 20F);
        fontTitle = minecrafter_3.deriveFont(Font.PLAIN, 40F);
    }

    public boolean isSoundSettingsScreen() {
        return soundSettingsScreen;

    }
    public void setSoundSettingsScreen(boolean soundSettingsScreen) {
        this.soundSettingsScreen = soundSettingsScreen;
    }

    //    Getter Setter
    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public UIAssets getUiAssets() {
        return uiAssets;
    }

    public void setUiAssets(UIAssets uiAssets) {
        this.uiAssets = uiAssets;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public Font getArial_16B() {
        return arial_16B;
    }

    public void setArial_16B(Font arial_16B) {
        this.arial_16B = arial_16B;
    }

    public Font getMinecrafter_3() {
        return minecrafter_3;
    }

    public void setMinecrafter_3(Font minecrafter_3) {
        this.minecrafter_3 = minecrafter_3;
    }


    public Font getGalaticaGrid() {
        return galaticaGrid;
    }

    public void setGalaticaGrid(Font galaticaGrid) {
        this.galaticaGrid = galaticaGrid;
    }

    public Font getPixelGamer() {
        return pixelGamer;
    }

    public void setPixelGamer(Font pixelGamer) {
        this.pixelGamer = pixelGamer;
    }

    public Font getJungleAdventurer() {
        return jungleAdventurer;
    }

    public void setJungleAdventurer(Font jungleAdventurer) {
        this.jungleAdventurer = jungleAdventurer;
    }

    public Font getFontMenu() {
        return fontMenu;
    }

    public void setFontMenu(Font fontMenu) {
        this.fontMenu = fontMenu;
    }

    public Font getFontCredits() {
        return fontCredits;
    }

    public void setFontCredits(Font fontCredits) {
        this.fontCredits = fontCredits;
    }

    public Font getFontTitle() {
        return fontTitle;
    }

    public void setFontTitle(Font fontTitle) {
        this.fontTitle = fontTitle;
    }

    public Font getFontGameOver() {
        return fontGameOver;
    }

    public void setFontGameOver(Font fontGameOver) {
        this.fontGameOver = fontGameOver;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public int getPlayer1Skin() {
        return player1Skin;
    }

    public void setPlayer1Skin(int player1Skin) {
        this.player1Skin = player1Skin;
    }

//    public int getBackgroundchoice() {
//        return backgroundchoice;
//    }
//
//    public void setBackgroundchoice(int Backgroundchoice) { this.backgroundchoice = Backgroundchoice;}

    public int getPlayer2Skin() {
        return player2Skin;
    }

    public void setPlayer2Skin(int player2Skin) {
        this.player2Skin = player2Skin;
    }

    public PlayerSkin[] getPlayerSkins() {
        return playerSkins;
    }

    // background
    public BufferedImage[] getBackgrounds() {
        // Ubah agar mengembalikan array BufferedImage
        return background;
    }



    public void setPlayerSkins(PlayerSkin[] playerSkins) {
        this.playerSkins = playerSkins;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }
//ini

    public void setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
    }

    public int getTitleScreenMenu() {
        return titleScreenMenu;
    }

    public int getTitleScreenCredits() {
        return titleScreenCredits;
    }

    public int getTitleScreenBackground() {
        return titleScreenBackground;
    }
    //ini

    public int getTitleScreenSettings() {
        return titleScreenSettings;
    }

    public int getTitleScreenCharacter() {
        return titleScreenCharacter;
    }

    //    Method
    public void draw(Graphics2D g2){
        this.g2 = g2;
        if (gp.getGameState() == gp.getTitleState()){
            if (titleScreenState == titleScreenMenu){
                drawMenuScreen();
            } else if (titleScreenState == titleScreenCredits){
                drawCreditsScreen();
            } else if (titleScreenState == titleScreenSettings) {
                drawSettingsScreen();
            } else if (titleScreenState == titleScreenCharacter) {
                drawCharacterScreen();
            } else if (titleScreenState == titleScreenBackground) {
                drawBackgroundScreen();
            }
        }
        else if (gp.getGameState() == gp.getPlayState()){
            //drawFPS();
            drawScore();
        }
        else if (gp.getGameState() == gp.getPauseState()){
            drawPauseScreen();
        } else if (gp.getGameState() == gp.getSettingsState()) {
            drawSettingsScreen();
        } else if (gp.getGameState() == gp.getGameOverState()){
            drawGameOverScreen();
        }
    }
    public void drawSkinError(){
        g2.setFont(fontCredits);
        String text = "Player skin can't be the same !";
        g2.setColor(Color.orange);
        int x = getXforCenteredText(text);
        int y = gp.getTileSize()*9;
        // shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        // main text
        g2.setColor(Color.ORANGE);
        g2.drawString(text, x, y);
    }
    public int getQuarterX(String text, boolean right){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        if (right){
            return (gp.getScreenWidth()/4)*3 - textLength/2 - gp.getTileSize()/2;
        } else {
            return gp.getScreenWidth()/4 - textLength/2 + gp.getTileSize()/2;
        }
    }

    public void drawMenuScreen() {
        // Background Color Menu
        g2.setColor(new Color(109, 64, 160));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Title text settings
        g2.setFont(fontTitle);
        String text = "KITTY CHASE";
        int x = getXforCenteredText(text);
        int y = (int)(gp.getTileSize() * 2.5);

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);

        // Draw title text
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        // Player images
        int playerSize = gp.getTileSize()*2;
        y += gp.getTileSize();
        x = (gp.getScreenWidth() - (playerSize*4))/2;
        g2.drawImage(playerSkins[0].getUp1(), x, y, playerSize,playerSize, null);
        x += gp.getTileSize()*2;
        g2.drawImage(playerSkins[1].getUp1(), x, y, playerSize,playerSize, null);
        x += gp.getTileSize()*4;
        g2.drawImage(playerSkins[2].getUp1(), x, y, -playerSize,playerSize, null);
        x += gp.getTileSize()*2;
        g2.drawImage(playerSkins[3].getUp1(), x, y, -playerSize,playerSize, null);

        // Menu
        g2.setFont(fontMenu);

        text = "PLAY";
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 3;
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }

        text = "CREDITS";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text,x, y);
        if (menuNum == 3) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }
    }

    public void drawCharacterScreen(){
        // Logic
        player1Skin = (player1Skin > 3) ? 0 : (player1Skin < 0) ? 3 : player1Skin;
        player2Skin = (player2Skin > 3) ? 0 : (player2Skin < 0) ? 3 : player2Skin;

        // Background Color character
        g2.setColor(new Color(109, 64, 160));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Title
        g2.setFont(fontTitle);
        String text = "Choose Your";
        int x = getXforCenteredText(text);
        int y = (int) (gp.getTileSize()*1.5);

        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString("Player", getXforCenteredText("Player")+2, y+gp.getTileSize()+2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        g2.drawString("Player", getXforCenteredText("Player"), y+gp.getTileSize());

        // Player Skin
        int playerSize = gp.getTileSize()*4;
        x = (int)(gp.getScreenWidth()/3.5) - playerSize/2;
        y += gp.getTileSize()*1.5;
        g2.drawImage(playerSkins[player1Skin].getRun3(), x, y, playerSize,playerSize, null);

        x = (int)(gp.getScreenWidth()/3) + playerSize/2;
        g2.drawImage(playerSkins[player2Skin].getRun3(), x, y, playerSize,playerSize, null);

        // Options Baru
        g2.setFont(fontMenu);
        text = PlayerSkin.getSkinColor()[player1Skin];
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 5;
        g2.setColor(Color.black);
        g2.drawString(text, getQuarterX(text, false) + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, getQuarterX(text, false), y);
        text = ">             <";
        g2.setColor(Color.black);
        g2.drawString(text, getQuarterX(text, false) + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, getQuarterX(text, false), y);

        text = PlayerSkin.getSkinColor()[player2Skin];
        g2.setColor(Color.black);
        g2.drawString(text, getQuarterX(text, true) + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, getQuarterX(text, true), y);
        text = ">             <";
        g2.setColor(Color.black);
        g2.drawString(text, getQuarterX(text, true) + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, getQuarterX(text, true), y);

        if(player1Skin == player2Skin){
            drawSkinError();
        }
    }

    public void nextBackgroundChoice() {
        backgroundChoice = (backgroundChoice + 1) % backgrounds.length;
    }

    public void previousBackgroundChoice() {
        backgroundChoice = (backgroundChoice - 1 + backgrounds.length) % backgrounds.length;
    }

    public int getBackgroundchoice() {
        return backgroundChoice;
    }

    public void setBackgroundchoice(int backgroundChoice) {
        this.backgroundChoice = backgroundChoice;
    }


    public void drawBackgroundScreen() {
        // Logic
        backgroundChoice = (backgroundChoice > 2) ? 0 : (backgroundChoice < 0) ? 2 : backgroundChoice;

        // Background Color Background
        g2.setColor(new Color(109, 64, 160));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Title
        g2.setFont(fontMenu);
        String text = "Choose Your Locations";
        int x = getXforCenteredText(text);
        int y = (int) (gp.getTileSize()*1.5);

        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString("Background", getXforCenteredText("Background")+2, y+gp.getTileSize()+2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        g2.drawString("Background", getXforCenteredText("Background"), y+gp.getTileSize());

        // Background
        int backgroundSize = gp.getTileSize()*4;
        x = (int)(gp.getScreenWidth()/2) - backgroundSize/2;
        y += gp.getTileSize()*1.5;
        g2.drawImage(backgrounds[backgroundChoice].getBackground1(), x, y, backgroundSize, backgroundSize, null);

        // Options Baru
        text = BackgroundManager.getBackgroundChoice()[backgroundChoice];
        String combinedText = "> " + text + " <";
        int combinedTextWidth = g2.getFontMetrics().stringWidth(combinedText);

        x = (gp.getScreenWidth() - combinedTextWidth) / 2;
        y += gp.getTileSize() * 5;

        g2.setColor(Color.black);
        g2.drawString(combinedText, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(combinedText, x, y);
    }

    // Other methods and logic...

//        g2.setFont(fontMenu);
//        text = BackgroundManager.getBackgroundChoice()[backgroundchoice];
//        x = getXforCenteredText(text);
//        y += gp.getTileSize() * 5;
//        g2.setColor(Color.black);
//        g2.drawString(text, getQuarterX(text, false) + 2, y + 2);
//        g2.setColor(Color.orange);
//        g2.drawString(text, getQuarterX(text, false), y);
//        text = ">             <";
//        g2.setColor(Color.black);
//        g2.drawString(text, getQuarterX(text, false) + 2, y + 2);
//        g2.setColor(Color.orange);
//        g2.drawString(text, getQuarterX(text, false), y);


//    public void drawBackgroundScreen() {
//        // Background Color
//        g2.setColor(new Color(109, 64, 160));
//        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
//
//        // Title
//        g2.setFont(new Font("Arial", Font.BOLD, 48));
//        String text = "Choose Background : ";
//        int x = getXforCenteredText(text);
//        int y = (int) (gp.getTileSize() * 1.5);
//
//        g2.setColor(Color.black);
//        g2.drawString(text, x + 2, y + 2);
//
//        g2.setColor(Color.orange);
//        g2.drawString(text, x, y);
//
//        // Draw current background image
//        int backgroundSize = gp.getTileSize() * 4;
//        x = (gp.getScreenWidth() - backgroundSize) / 2;
//        y += gp.getTileSize() * 1.5;
//
//        g2.drawImage(gp.getUi().getCurrentBackgroundImage(), x, y, backgroundSize, backgroundSize, null);
//
//        // Options
//        g2.setFont(new Font("Arial", Font.PLAIN, 24));
//        String optionText = ">             <";
//        g2.setColor(Color.black);
//        g2.drawString(optionText, getXforCenteredText(optionText) + 2, y + backgroundSize + 2);
//        g2.setColor(Color.orange);
//        g2.drawString(optionText, getXforCenteredText(optionText), y + backgroundSize);
//    }


    public void drawCreditsScreen(){
        g2.setColor(new Color(109, 64, 160));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setFont(fontMenu);

        String credit = "Made By : ";
        int creditX = getXforCenteredText(credit);
        int creditY = (int) (gp.getTileSize()*1.5);
//        new options in made by
        g2.setColor(Color.black);
        g2.drawString(credit, creditX + 2, creditY + 2);
        g2.setColor(Color.orange);
        g2.drawString(credit, creditX, creditY);

//        g2.setFont(fontMenu);
        String line1 = "Annisa Mutia Rahman";
        int line1X = getXforCenteredText(line1);
        int line1Y = creditY + gp.getTileSize();
//        new options
        g2.setColor(Color.black);
        g2.drawString(line1, line1X + 2, line1Y + 2);
        g2.setColor(Color.orange);
        g2.drawString(line1, line1X, line1Y);

        String line2 = "Belda Putri Pramono";
        int line2X = getXforCenteredText(line2);
        int line2Y = line1Y + gp.getTileSize()/2;
//        new options
        g2.setColor(Color.BLACK);
        g2.drawString(line2, line2X + 2, line2Y + 2);
        g2.setColor(Color.orange);
        g2.drawString(line2, line2X, line2Y);

        g2.setFont(fontMenu);
        String text = "Dosen Pengampu:";
        int x = getXforCenteredText(text);
        int y = line2Y + gp.getTileSize()*2;
//        new options
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        g2.setFont(fontMenu);
        text = "Margareta Hardiyanti, S.Kom., M.Eng.";
        y += gp.getTileSize();
        x = getXforCenteredText(text);
//        new options
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
    }
    public void drawSettingsScreen(){
        g2.setColor(new Color(109, 64, 160));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Draw "SETTINGS" text
        g2.setFont(fontCredits);
        String text = "SETTINGS";
        int x = getXforCenteredText(text);
        int y = (int)(gp.getTileSize() * 2);

        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        drawSettings();
    }
    public void drawSettings(){
        int x = 170;
        int y = (int)(gp.getTileSize());
        int rectX;
        int rectY = (int)(gp.getTileSize());

        //MUSIC + BAR
        g2.setFont(fontMenu);
        String text = "MUSIC";
        y += gp.getTileSize() * 3;
        // shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            // shadow
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);

            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }
        rectX = gp.getTileSize() + gp.getTileSize() * 5;
        rectY += gp.getTileSize() * 2.5;
        g2.drawRect(rectX, rectY, 120, 24);
        int volumeWidth = 24 * gp.getMusic().getVolumeScale();
        g2.fillRect(rectX, rectY, volumeWidth, 24);

        //SOUND + BAR
        text = "SOUND";
        y += gp.getTileSize();
        // draw shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        // draw main text
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            // shadow
            g2.setColor(Color.BLACK);
            g2.drawString(">", x - 30 + 2, y + 2);
            // main text
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }
        rectY += gp.getTileSize();
        g2.drawRect(rectX, rectY, 120, 24);
        volumeWidth = 24 * gp.getSfx().getVolumeScale();
        g2.fillRect(rectX, rectY, volumeWidth, 24);
    }

    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setFont(fontMenu);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.getScreenHeight() / 2 - gp.getTileSize(); // Menempatkan judul di tengah vertikal layar

        // Draw Title
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        // Draw "RESUME"
        y += gp.getTileSize() * 1;
        text = "RESUME";
        x = getXforCenteredText(text);
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 1;
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }


        // Draw "QUIT"
        y += gp.getTileSize() * 1;
        text = "QUIT";
        x = getXforCenteredText(text);
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.setColor(Color.black);
            g2.drawString(">", x - 30 + 2, y + 2);
            g2.setColor(Color.orange);
            g2.drawString(">", x - 30, y);
        }
    }

    public void drawGameOverScreen(){
        g2.setColor(Color.orange);

        g2.setFont(fontTitle);
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.getTileSize();

        // shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        g2.setFont(fontTitle);
        text = gp.getWinner() + " Wins!";
        x = getXforCenteredText(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        // shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        g2.setFont(fontCredits);
        text = "SCORE : "+gp.getScore();
        x = getXforCenteredText(text);
        y += gp.getTileSize();

        // shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        // main text
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);

        if (gp.setHighScore(gp.getScore())){
            text = "NEW HIGHSCORE!!";
            x = getXforCenteredText(text);
            y += gp.getTileSize()*3;

            // shadow
            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 2, y + 2);

            // main text
            g2.setColor(Color.orange);
            g2.drawString(text, x, y);
        }

        text = "Highscore: "+gp.getHighScore();
        x = getXforCenteredText(text);
        y += gp.getTileSize()*2.5;
        // shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        // main text
        g2.setColor(Color.orange);
        g2.drawString(text, x, y);
    }

    public void drawFPS(){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            g2.setColor(Color.orange);
            g2.drawString("FPS : "+gp.getActualFPS(), gp.getScreenWidth()-120, 30);
    }
    public void drawScore(){
        g2.setFont(fontCredits);
        g2.setColor(Color.orange);
        g2.drawString(String.format("%05d", gp.getScore()), gp.getScreenWidth()- (int) (gp.getTileSize()*2.5), (int)(gp.getTileSize()*0.7));
    }

    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.getScreenWidth()/2 - textLength/2;
    }
    public void setupFonts(){
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/JungleAdventurer.ttf");
            jungleAdventurer = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Minecrafter_3.ttf");
            minecrafter_3 = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/GalacticaGrid.ttf");
            galaticaGrid = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/PixelGamer.otf");
            pixelGamer = Font.createFont(Font.TRUETYPE_FONT, is);

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUpBackgrounds(){
        backgrounds[0] = new BackgroundManager("malam");
        backgrounds[1] = new BackgroundManager("siang");
        backgrounds[2] = new BackgroundManager("colorfull");
    }

    public void setUpPlayerSkins(){
        playerSkins[0] = new PlayerSkin("abu");
        playerSkins[1] = new PlayerSkin("oyen");
        playerSkins[2] = new PlayerSkin("blacky");
        playerSkins[3] = new PlayerSkin("telon");
    }
}


