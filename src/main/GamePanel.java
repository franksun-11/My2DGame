package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;// 16 * 16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 * 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;

    // system
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    // entity and object
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];






    //set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; //draw the screen 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            // 1.update info such as character position
            update();
            // 2.draw the screen with the updated info
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        for(int i = 0; i < obj.length ; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
