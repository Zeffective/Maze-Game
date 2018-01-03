/*
 * Jesse Wu
 * Maze Game
 * Dec 2017 - Jan 2018
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        ScoreChecker scoreHandler = new ScoreChecker("HighScores.txt");
        
        String intro = 
        		"I -> UP \n"
        		+ "K -> DOWN \n"
        		+ "J -> LEFT \n"
        		+ "L -> RIGHT \n"
        		+ "\n Move the knight to the treasure chest! \n"
        		+ "However, you can't pass through the walls\n"
        		+ "or go off the board. Also, you must finish \n"
        		+ "before the timer reaches 20 or you'll lose! \n"
        		+ "Try to get the lowest time possible.\n"
        		+ "\n Press \"Replay\" to play again.";
        
        
        final JFrame frame = new JFrame("Maze Game");
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel statusPanel = new JPanel();
        JLabel clock = new JLabel("Time: 0");
        Board board = new Board(clock, scoreHandler);
        final JPanel control_panel = new JPanel();
        
        //Reset button
        final JButton reset = new JButton("Replay");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        
        //adding components to frame
        control_panel.add(reset);
        statusPanel.add(clock);
        gamePanel.add(statusPanel, BorderLayout.NORTH);
        gamePanel.add(board, BorderLayout.CENTER);   
        frame.add(control_panel, BorderLayout.SOUTH);
        frame.add(gamePanel);
        
        //setting frame properties
        frame.setLocation(300, 100);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JOptionPane.showMessageDialog(null, intro);
        
        frame.setVisible(true);
        board.reset();
    }

    //Main method run to start and run the game. 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}