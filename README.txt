=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays. The board of the maze is a square grid, so 2d arrays are appropriate
  for modeling the grid and the elements on it. It can store board elements (tiles)
  in an index analgous to its position in the gui.

  2. I/O. High scores must be cumulative even after the game is closed. So high scores
  must be stored in an external file. One such type of file is a txt file, which can be
  written to and read by the game to show and change high scores.

  3. JUnit Tests. Tiles cannot go off of the screen (especially the player!) and the
  player should never pass through a wall. Walls and the target also should not move
  during a single game. It also tests the robustness of the I/O used to keep an display
  high scores.

  4. Inheritance. A Tile is a special type of GameObj that is always a square of 
  a set size, SIZE. The different types of tiles that can be put onto the board
  are represented as different subtypes of Tile. However, the Player tile should
  be drawn differently than another tile and has an arrX and arrY field as well.
  These fields represent its position in the int array that contains the game state
  and facilitate calculating its movement. However, these values need their own methods
  to bound them, etc. as well. 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Board is the equivalent of GameCourt in the example code. It represents where
  the game itself occurrs and holds all the tiles and performs the logic. Tile
  is the class of objects that can be shown on Board. Player is a Tile representing
  the player that moves. Wall is a Tile that cannot move and blocks the player.
  Target is a Tile that ends the game in a win once the player reaches it. Path is
  a Tile that doesn't do anything, it represents a road, the player can pass 
  through them.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Modeling the game state using 2d arrays was more difficult than expected. I needed
  one 2d array of the Tile objects so that I could loop through it and draw them.
  I also needed a 2d array of the ints that had the generate/ move logic performed
  on it. This array then changed the Tile array mentioned before. Keeping consistency
  and bounds between these was quite difficult. I also forgot to set the Board to 
  Focusable. So my keyboard controls would not work for an entire night and next day.
  I thought there was something wrong with my code, but it was because I forgot that
  one line.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  Keep collision properties encapsulated in the various Tile subclasses instead
  of handling some of it in Board.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  No outside sources were used.


