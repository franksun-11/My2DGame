package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum [][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");

    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/wall.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/water.png")));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/earth.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/tree.png")));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Tiles/Old_version/sand.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {

            }
    }

    public void draw(Graphics2D g2){
//        g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 192, 0, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 192, 48, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 192, 96, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 192, 144, gp.tileSize, gp.tileSize, null);
//
//        g2.drawImage(tile[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 192, 192, gp.tileSize, gp.tileSize, null);
        int WorldCol = 0;
        int WorldRow = 0;

        while(WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[WorldCol][WorldRow];

            int worldX = WorldCol * gp.tileSize;
            int worldY = WorldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            WorldCol++;

            if(WorldCol == gp.maxWorldCol){
                WorldCol = 0;
                WorldRow++;
            }
        }


        }

    }
