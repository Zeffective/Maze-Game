/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */

/*
 * represents a score that can be written to a txt file
 */

public class Score {
	private int time;
	private String playerName;
	
	public Score(String s, int t) {
		playerName = s;
		time = t;
	}
	
	public int getTime() {
		return time;
	}
	
	public String getName() {
		return playerName;
	}
	
	public boolean equals(Score s) {
		boolean b = (playerName.equals(s.getName())) && (time == s.getTime());
		if (!(s instanceof Score)) {
			b = false;
		}
		return b;
	}
}
