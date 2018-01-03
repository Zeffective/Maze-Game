/*
 * Jesse Wu

 * Maze Game
 * Dec 2017
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Tests certain components of the game
 */

public class GameTest {
	Tile tile1 = new Wall(50, 25, 300, 300);
	Tile tile2 = new Path(30, 50, 300, 300);
	ScoreChecker checker;
	@Test
	public void testTileArrXUpperBound() {
		tile1.setArrX(22);
		assertEquals(tile1.getArrX(), 19);
	}
	
	@Test
	public void testTileArrYUpperBound() {
		tile1.setArrY(25);
		assertEquals(tile1.getArrY(), 19);
	}
	
	@Test
	public void testTileArrXLowBound() {
		tile1.setArrX(-5);
		assertEquals(tile1.getArrX(), 0);
	}
	
	@Test 
	public void testTileArrYLowBound() {
		tile1.setArrY(-2);
		assertEquals(tile1.getArrY(), 0);
	}
	
	@Test
	public void fileReaderWhiteSpace() {
		checker = new ScoreChecker("TestFile.txt");
		checker.update();
		Score[] arr = checker.getScoreArr();
		assertTrue(arr[0].equals(new Score("1", 1)));
	}
	@Test
	public void fileReaderManySpaces() {
		checker = new ScoreChecker("TestFile.txt");
		checker.update();
		Score[] arr = checker.getScoreArr();
		assertTrue(arr[2].equals(new Score("3", 3)));
	}
	
	
	@Test
	public void fileReaderBadFormat() {
		checker = new ScoreChecker("TestFile.txt");
		checker.update();
		Score[] arr = checker.getScoreArr();
		assertTrue(arr[3].equals(new Score("0", 0)));
	}
	
	@Test
	public void genSizeTest() {
		Generator gen = new Generator();
		int[][] arr = gen.generate();
		assertEquals(arr.length, 21);
		assertEquals(arr[2].length, 21);
	}
	
	@Test
	public void boardArrTest() {//test synchronization between boardArr and arr
		JLabel clock = new JLabel("blah");
		checker = new ScoreChecker("HighScores.txt");
		Board board = new Board(clock, checker);
		board.movePlayer(0, -1);
		
		board.updateBoard();
		assertTrue(board.inSync());
	}
	
	@Test
	public void boardArrOutBoundsTest() {//test that player doesn't go out of bounds
		JLabel clock = new JLabel("blah");
		checker = new ScoreChecker("HighScores.txt");
		Board board = new Board(clock, checker);
		board.movePlayer(0, 1);
		
		board.updateBoard();
		assertTrue(board.inSync());
	}
	
	@Test
	public void boardArrInToWallTest() {
		JLabel clock = new JLabel("blah");
		checker = new ScoreChecker("HighScores.txt");
		Board board = new Board(clock, checker);
		board.movePlayer(1, 0);
		
		board.updateBoard();
		assertTrue(board.inSync());
	}
}