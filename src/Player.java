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


public class Player extends Tile {
    private static BufferedImage image;
    
    private final String ID;
    private final String FILE_NAME = "files/knight.png";
    
    /* setArrX and ArrY need to change when maze size changes
     * player should always start in the bottom middle of the
     * maze.
     */
    public Player(int px, int py, int courtWidth, int courtHeight) {
        super(px, py, courtWidth, courtHeight);
        setArrX(12);
        setArrY(24);
        try {
            if (image == null) {
                image = ImageIO.read(new File(FILE_NAME));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
        
        ID = JOptionPane.showInputDialog("Enter a player name");
    }
    
    public String getName() {
    	return ID;
    }
    
    @Override
    public void draw(Graphics g) {
    	g.drawImage(image, this.getPx(), this.getPy(), this.getSize(), this.getSize(), null);
    }
}