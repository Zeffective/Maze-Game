=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
Maze Game README
Jesse Wu
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Description :=
===================

	This game was originally a project for a class. The goal was to make a game that
	used 4 of the core concepts taught in class. The project was done in a week.
	After the class, I changed the maze generator so that it was randomized
	rather than hard coded and added images as graphics as a personal project.

===================
=: Core Concepts :=
===================

  1. 2D Arrays
  2. Recursion
  2. I/O
  3. JUnit Tests
  4. Inheritance

========================
=: External Resources :=
========================
  
  This blog post was used for an outline of how the recursive backtracking algorithm
  works: http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking

========================
=: Notes :=
========================

  Very rarely, the player will freeze when moving to the right and it cannot move even
  if the replay button is hit. The timer still runs. I haven't found a way to consistently
  replicate this bug. Some debugging found that the keyboard listener was not recognizing 
  when the keyboard was pressed.
