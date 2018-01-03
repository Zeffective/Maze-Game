/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Player, tile that can move based on keyboard arrow keys, attain items,
 * and cannot pass through walls. Game goes to next round when player
 * reaches the target tile.
 */

public class Path extends Tile {
    private final String FILE_NAME = "files/floor.png";
    private static BufferedImage image;
    
    public Path(int px, int py, int courtWidth, int courtHeight) {
        super(px, py, courtWidth, courtHeight);
    
        try {
            if (image == null) {
                image = ImageIO.read(new File(FILE_NAME));
            }
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g) {
    	g.drawImage(image, this.getPx(), this.getPy(), this.getSize(), this.getSize(), null);
    }
}