/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Tile, represents some element on the board
 * is always square
 */

public abstract class Tile {
   public static final int SIZE = 30; 
   
   private int px; 
   private int py;
   private int arrX;
   private int arrY;
   private int maxX;
   private int maxY;
   private int maxArrX;
   private int maxArrY;
   
   public Tile (int px, int py, int courtWidth, int courtHeight) {
       //super(0, 0, px, py, SIZE, SIZE, courtWidth, courtHeight);
       this.px = px;
       this.py = py;
       
       maxX = courtWidth - SIZE;
       maxY = courtHeight - SIZE;
       arrX = getPx()/SIZE;
       arrY = getPy()/SIZE;
       maxArrX = 24;
       maxArrY = 24;
   }
   
   public int getPx() {
       return this.px;
   }

   public int getPy() {
       return this.py;
   }

   public int getArrX() {
       return arrX;
   }
   
   public int getArrY() {
       return arrY;
   }
   
   public void setPx(int px) {
       this.px = px;
       clip();
   }

   public void setPy(int py) {
       this.py = py;
       clip();
   }
   public void setArrX(int x) {
       arrX = x;
       arrClip();
   }
   
   public void setArrY(int y) {
       arrY = y;
       arrClip();
   } 
   
   /**
    * Prevents the object from going outside of the bounds of the area designated for the object.
    * (i.e. Object cannot go outside of the active area the user defines for it).
    */ 
   private void clip() {//maybe override this in player class so that player can't go into walls
       this.px = Math.min(Math.max(this.px, 0), this.maxX);
       this.py = Math.min(Math.max(this.py, 0), this.maxY);
   }
   
   //limits for min need to change when maze size changes
   public void arrClip() {
       arrX = Math.min(maxArrX, Math.max(arrX, 0));
       arrY = Math.min(maxArrY, Math.max(arrY, 0));
   }

   int getSize() {
       return SIZE;
   }   
   
   public abstract void draw(Graphics g);
}