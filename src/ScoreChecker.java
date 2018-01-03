/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

import javax.swing.*;
import java.io.*;


/**
 * ScoreChecker, handles all of the I/O and displays high scores based on txt file
 */

public class ScoreChecker {
	private final Score MAX = new Score("MAX", 300);
	
	private Score[] topScores = new Score[6];//index 0 is first, 4 is 5th, 5 is temp storage
	private String fileName;
	
	public ScoreChecker(String name) {
		fileName = name;
		update();
	}
	
	//if score is better than top 5, it gets added via text file
	public void addScore(String s, int t) {
		topScores[5] = new Score(s, t);
		
		//sort the array to see if the new score beats any of the top 5
		for (int i = 0; i < topScores.length - 1; i++) {
			if (topScores[i] != null && topScores[i + 1] != null) {
				if (topScores[i].getTime() > topScores[i + 1].getTime()) {
					Score temp = topScores[i];
					topScores[i] = topScores[i + 1];
					topScores[i + 1] = temp;
					i = -1;
				}
			}
		}
		topScores[5] = MAX;
		//write it to the file, index 5 is not written b/c it's temp storage
		try {
			FileWriter writer = new FileWriter(fileName);
			for (int i = 0; i < topScores.length - 1; i++) {
				String line;
				if (i == topScores.length - 2) {
					line = topScores[i].getName() + "," + topScores[i].getTime();
				}
				else {
					line = topScores[i].getName() + "," + topScores[i].getTime() + "\n";
				}
				writer.write(line);
			}
			writer.close();
		}
		catch (IOException e) {
			System.out.println("No such file to write to");
			System.exit(0);
		}
	}
	
	public void update() {//reads from txt file to update arr holding scores
		String[] temp;
		
		try {
			FileReader fileIn = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileIn);
			
			for (int i = 0; i < 5; i++) {
				String line = br.readLine();
				if (line != null) {
					line = line.replaceAll("\\s+","");
					temp = line.split(",");
					if (temp.length == 2) {
						topScores[i] = new Score(temp[0], Integer.parseInt(temp[1]));
					}
					else {
						topScores[i] = new Score("N/A", 0);
					}
				}
				else {
					topScores[i] = new Score("N/A", 0);
				}
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("No such file to read");
			System.exit(0);
		}
	}
	
	public void showScores() {		
		String message = "Best Times: \n" +
				"\n" + topScores[0].getName() + ": " + topScores[0].getTime() +
				"\n" + topScores[1].getName() + ": " + topScores[1].getTime() +
				"\n" + topScores[2].getName() + ": " + topScores[2].getTime() +
				"\n" + topScores[3].getName() + ": " + topScores[3].getTime() +
				"\n" + topScores[4].getName() + ": " + topScores[4].getTime();

		JOptionPane.showMessageDialog(null, message);
	}
	
	public Score[] getScoreArr() {
		return topScores;
	}
}
