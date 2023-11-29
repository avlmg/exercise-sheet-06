package de.unistuttgart.iste.sqa.pse.sheet06.homework.maze;

import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

/**
 * Describe the purpose of this class here.
 *
 * @author (Your name)
 * @version (a version number or a date)
 */
public class AmazingPauleHamsterGame extends SimpleHamsterGame {

	/**
	 * Creates a new AmazingPauleHamsterGame.<br>
	 * Do not modify!
	 */
	public AmazingPauleHamsterGame() {
		this.loadTerritoryFromResourceFile("/territories/AmazingPauleTerritory.ter");
		this.displayInNewGameWindow();
		game.startGame();
	}

	/**
	 * Ignore this method.<br>
	 * Put your code in passTheMaze()!
	 */
	@Override
	protected void run() {
		passTheMaze();
	}
	/*@
	@ requires paule != null
	@ ensures paule made three left turns
	 */
	/**
	 * Checks if paule´s right side is free
	 */
	public void turnRight() {
		paule.turnLeft();
		paule.turnLeft();
		paule.turnLeft();
	}

	/*@
	@ requires paule != null
	 */
	/**
	 * Checks if paule´s left side is free
	 */
	public boolean leftIsClear() {
		paule.turnLeft();
		boolean result = paule.frontIsClear();
		turnRight();
		return result;
	}
	/*@
	@ requires paule != null
	 */
	/**
	 * Checks if paule´s right side is free
	 */
	public boolean rightIsClear() {
		turnRight();
		boolean result = paule.frontIsClear();
		paule.turnLeft();
		return result;
	}
	/*@
	@ requires Hamster != null
	@ ensures Hamster passed the Maze
	 */
	/**
	 * Makes Paule pass the Maze using the left hand rule
	 * and pick one grain.
	 */
	void passTheMaze() {
		/*@
		@loop_invariant: Paule is inside the maze, following the left-hand rule.
		 */
		while (paule.mouthEmpty()) {
			if (leftIsClear()) {
				paule.turnLeft();
				paule.move();
			} else if (paule.frontIsClear()) {
				paule.move();
			} else if (rightIsClear()) {
				turnRight();
				paule.move();
			} else {
				paule.turnLeft();
				paule.turnLeft();
				paule.move();
			}
			if (paule.grainAvailable()) {
				paule.pickGrain();
				paule.write("I escaped the Maze! :)");
				break;
			}
		}
	}
}
