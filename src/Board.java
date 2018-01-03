/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * Board, JPanel that represents the maze and holds everything in the maze
 * does the logic behind moving the player and win/ lose too
 */

public class Board extends JPanel {
    public static final int PLAYER_VELOCITY = 20;
    public static final int INTERVAL = 25;
    
    private final int BOARD_WIDTH;
    private final int BOARD_HEIGHT;
    private final int loseTime = 44;
    
    private int x;
    private int y;
    private int inc = Tile.SIZE;
    private int count = 0;
    private int time = 0;
    private boolean playing = false; 
    private int[][] arr;
    private int[][] oldArr;
    
    private Tile[][] boardArr;
    private Target target;
    private Player player;
    private ScoreChecker scoreHandler;
    private JLabel clock;
    
    Generator gen;
    int[][] maze;
    
    public Board(JLabel clock, ScoreChecker scoreHandler) {
    	//get randomized maze
    	gen = new Generator();
    	maze = gen.generate();

        x = maze.length;
        y = maze[0].length;
        BOARD_WIDTH = x * inc - 1;//so grahpics don't overlap
        BOARD_HEIGHT = y * inc - 1;
        
        boardArr = new Tile[x][y];
        arr = maze;
        oldArr = arr;
        
        this.clock = clock;
        this.scoreHandler = scoreHandler;
        
        //setting boardArr based on arr
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                
                switch (maze[i][j]) {
                    case 0: boardArr[i][j] = new Path(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        break;
                    case 1: boardArr[i][j] = 
                        new Wall(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        break;
                    case 2: 
                        boardArr[i][j] = new Path(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        break;
                     
                    case 3: target = new Target(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        boardArr[i][j] = target;
                        break;
                    default:
                        break;
                }
            }
        }
        
        //need this for keyboard controls
        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                for (int i = 0; i < x; i++) {//store arr before change
                    for (int j = 0; j < y; j++) {
                        oldArr[i][j] = arr[i][j];
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_J) {
                    movePlayer(-1, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_L) {
                    movePlayer(1, 0);
                } else if (e.getKeyCode() == KeyEvent.VK_K) {
                    movePlayer(0, 1);
                } else if (e.getKeyCode() == KeyEvent.VK_I) {
                    movePlayer(0, -1);       
                }
            }
        });
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
       
        //Internal timer for the game
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (playing) {
            		tick();
            	}
            }
        });
        
        timer.start();
        
        requestFocusInWindow();
    }
    
    /* 
     * takes in array indexes to move player tile by (ex. x = -1 moves left)
     * left tile is now player
     * original tile is a path
     * however, must check that it isn't moving into wall
     */
    public void movePlayer(int x, int y) {
        int newX = Math.min(arr.length - 1, Math.max(player.getArrX() + x, 0));
        int newY = Math.min(arr[0].length - 1, Math.max(player.getArrY() + y, 0));
        int newVal = arr[newX][newY];
        
        if (newVal != 1) {
	            arr[player.getArrX()][player.getArrY()] = 0;
	            player.setArrX(newX);
	            player.setArrY(newY);
	            arr[player.getArrX()][player.getArrY()] = 2;
        } 
        else {
            //nothing happens, can't move into wall
        }
    }
    
    //update boardArr based on arr
    public void updateBoard() {
        //walls never change
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (arr[i][j]) {
                    case 0: boardArr[i][j] = new Path(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        break;
                    case 2:
                        player.setPx(i * inc);
                        player.setPy(j * inc);
                        boardArr[i][j] = player;
                        break;
                    case 3: target = new Target(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        boardArr[i][j] = target;
                        break;
                    default:
                        break;
                }
            }
        }
        oldArr = arr;
    }
    
    //updates and checks fields and GUI every time the timer increments
    void tick() {
    	if (playing) {
            //tick goes every 35 ms, so every 35*30=1050ms=1.05s the displayed timer goes up by 1
            count++;
            time = count/40;
            clock.setText("Time: " + time);
            
            if (player.getPx() == target.getPx() && player.getPy() == target.getPy()) {//player reached target
            	youWin();
            }
            else if (time > loseTime) {
            	youLose();
            }
            else {
            	// update the display
                updateBoard();
            	repaint();
            }
    	}
    }
    
    public void youWin() {
    	playing = false;
    	JOptionPane.showMessageDialog(null, "You finished!"); 
    	scoreHandler.addScore(player.getName(), time);
    	scoreHandler.update();
    	scoreHandler.showScores();
    }
    
    public void youLose() {
    	playing = false;
    	JOptionPane.showMessageDialog(null, "You ran out of time.");
    	
    }
    
    //resets game
    public void reset() {
    	playing = false;
    	
    	maze = gen.generate();
        
        boardArr = new Tile[x][y];
        arr = maze;
        oldArr = arr;
        
        count = 0;
        time = 0;
        
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (maze[i][j]) {
                    case 0: boardArr[i][j] = new Path(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        break;
                    case 1: boardArr[i][j] = 
                        new Wall(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);//wall
                        break;
                    case 2: player = 
                        new Player(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        boardArr[i][j] = player;
                        break;
                    case 3: target = new Target(i * inc, j * inc, BOARD_WIDTH, BOARD_HEIGHT);
                        boardArr[i][j] = target;
                        break;
                    default:
                        break;
                }
            }
        }
        requestFocusInWindow();
        playing = true;
    }
    
    public boolean inSync() {
    	boolean b = true;
    	
    	for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                switch (maze[i][j]) {
                    case 0: if (!(boardArr[i][j] instanceof Path)) {
                    			b = false;
                    			return b;
                    		}
                        break;
                    case 1: if (!(boardArr[i][j] instanceof Wall)) {
		            			b = false;
		            			return b;
		            		}
                        break;
                    case 2: if (!(boardArr[i][j] instanceof Player)) {
		            			b = false;
		            			return b;
		            		}
                        break;
                    case 3: if (!(boardArr[i][j] instanceof Target)) {
		            			b = false;
		            			return b;
		            		}
                        break;
                    default:
                        break;
                }
            }
        }
    	
    	return b;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (playing) {
	        for (int i = 0; i < boardArr.length; i++) {
	            for ( int j = 0; j < boardArr[i].length; j++) {
	                boardArr[i][j].draw(g);
	            }
	        }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}