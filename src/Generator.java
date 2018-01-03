/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

/*
 * generates the array that the maze is based on.
 * 0 is path
 * 1 is a wall
 * 2 is player
 * 3 is target
 * Uses recursive backtracking to generate the maze, makes a 11x11 grid of path tiles
 * first (separating them using walls)
 */

import java.util.*;

public class Generator {
	private final int SIZE = 25;//should be odd for symmetry
	private final int VISIT_SIZE;
	
    private int[][] arr;   
    private boolean[][] visited;
    private Random randomizer;
    private enum Dir {UP, RIGHT, DOWN, LEFT};
    private List<Dir> directions;
    
    public Generator() {
    	directions = new ArrayList<Dir>();
    	directions.add(Dir.UP);
    	directions.add(Dir.RIGHT);
    	directions.add(Dir.DOWN);
    	directions.add(Dir.LEFT);
    	
    	randomizer = new Random();
    	
        arr = new int[SIZE][SIZE];
        VISIT_SIZE = (int) Math.round(SIZE/2.0);
        visited = new boolean[VISIT_SIZE][VISIT_SIZE];//traversed cells are true
    }
    
    //returns randomized maze in int array form
    public int[][] generate() {   
    	reset();
    	//randomly choose starting cell
    	int rX = randomizer.nextInt(VISIT_SIZE - 1);
    	int rY = randomizer.nextInt(VISIT_SIZE - 1);
    	
    	carve(rX * 2, rY * 2, rX, rY);
    	//set target and player pos after maze is generated
    	arr[SIZE/2][0] = 3;
    	arr[SIZE/2][SIZE - 1] = 2;
    	
        return arr;
    }
    
    //recursive function that makes the maze
    public void carve(int aX, int aY, int vX, int vY) {
    	visited[vX][vY] = true;
    	List<Dir> tempDirection = new ArrayList<Dir>(directions);	
    	
		for (int i = 0; i < 4; i++) {
			if (!tempDirection.isEmpty()) {
				Dir direction = tempDirection.get(randomizer.nextInt(tempDirection.size()));
	    	
	        	switch(direction) {
	        		case UP: 
	        			if (vY - 1 >= 0 && !visited[vX][vY - 1]) {
	        				arr[aX][aY - 1] = 0;
	        				carve(aX, aY - 2, vX, vY - 1);	
	        			}
	        			tempDirection.remove(Dir.UP);
	        			break;
	        		case RIGHT: 
	        			if (vX + 1 < VISIT_SIZE&& !visited[vX + 1][vY]) {
	        				arr[aX + 1][aY] = 0;
	        				carve(aX + 2, aY, vX + 1, vY);
	        			}
	        			tempDirection.remove(Dir.RIGHT);
	        			break;
	        		case DOWN: 
	        			if (vY + 1 < VISIT_SIZE && !visited[vX][vY + 1]) {
	        				arr[aX][aY + 1] = 0;
	        				carve(aX, aY + 2, vX, vY + 1);
	        			}
	        			tempDirection.remove(Dir.DOWN);
	        			break;
	        		case LEFT: 
	        			if (vX - 1 >= 0 && !visited[vX - 1][vY]) {
	        				arr[aX - 1][aY] = 0;
	        				carve(aX - 2, aY, vX - 1, vY);
	        			}
	        			tempDirection.remove(Dir.LEFT);
	        			break;
	        		default:
	        			break;
	        	}
			}
		}
    }
    
    //makes arr become a grid again so carve can be run again
    private void reset() {
        for (int i = 0; i < arr.length; i++) {
        	for (int j = 0; j < arr[i].length; j++) {
        		if (i % 2 == 1 || j % 2 == 1) {
        			arr[i][j] = 1;
        		}
        		else {
        			arr[i][j] = 0;
        		}
        	}
        }
        
        for (int i = 0; i < visited.length; i++) {
        	for (int j = 0; j < visited[i].length; j++) {
        		visited[i][j] = false;
        	}
        }
    }
}