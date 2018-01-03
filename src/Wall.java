/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * wall, takes up a tile and doesn't allow things to pass through it
 */

public class Wall extends Tile {
    public static final Color color = Color.black; 
    private final String FILE_NAME = "files/brickwall.png";
    private static BufferedImage image;
    
    public Wall(int px, int py, int courtWidth, int courtHeight) {
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