package de.unistuttgart.iste.sqa.pse.sheet06.homework.exceptions;

import de.hamstersimulator.objectsfirst.datatypes.Direction;
import de.hamstersimulator.objectsfirst.datatypes.Location;
import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

/**
 * A SimpleHamsterGame with exceptions
 *
 * @author (your name)
 */
public class LazyHamsterGame extends SimpleHamsterGame {

	/**
	 * This constructor is used for loading a territory for the game and for displaying it.
	 * Do not modify.
	 */
	public LazyHamsterGame(final String territoryFile) {
		this.loadTerritoryFromResourceFile(territoryFile);
		this.displayInNewGameWindow();
		game.startGame();
	}

	/**
	 * Do not modify this operation.
	 */
	@Override
	protected void run() {
		moveMultipleSteps(5);
	}

	/*@
	@ requires Hamster is initialised.
	 */
	/**
	 * Tries to move the Hamster forward.
	 *
	 * @throws TooLazyException If the hamster is too lazy to move.
	 * @throws NoWayToGoException If there is no way to go.
	 */
	public void tryToMove() {
		double randomValue = Math.random();
		if (randomValue < 0.7) {
			if (paule.frontIsClear()) {
				paule.move();
				paule.write("Paule moves forward!");
			}
		} else if (!paule.frontIsClear()) {
			throw new NoWayToGoException();
		} else {
			throw new TooLazyException();
		}

	}
	/*@
	@ requires Hamster is initialised
	@ ensures Hamster moves the specified number of steps.
	 */
	/**
	 * Ensures paule move the number of given step animating it if it is too lazy.
	 * @param numberOfSteps The number of steps to move.
	 * @throws TooLazyException If the hamster is too lazy to move.
	 */
	public void moveMultipleSteps(int numberOfSteps) {
		for (int i = 0; i < numberOfSteps; i++) {
			try {
				tryToMove();
				while (!paule.frontIsClear()) {
					isCaged();
				}
			} catch (TooLazyException exception) {
				paule.write("Come on Paule!");
				numberOfSteps = numberOfSteps + 1;
			} catch (NoWayToGoException exception) {
				if (isCaged()) {
					break;
				}
			}
		}
	}
	
	/*@
	@ requires Hamster is placed in the game.
	@ ensures boolean value of if the hamster is caged
	 */
	/**
	 * Checks if the hamster is caged.
	 *
	 * @return True if the hamster is trapped; otherwise, false.
	 */	public boolean isCaged() {
		Direction originalDirection = paule.getDirection();
		while (!paule.frontIsClear()) {
			paule.turnLeft();

			if (paule.getDirection().equals(originalDirection)) {
				break;
			}
		}
		if (paule.getDirection().equals(originalDirection)) {
			paule.write("I am trapped!");
		}
        return paule.getDirection().equals(originalDirection);
	}
}
