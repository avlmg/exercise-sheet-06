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

	// TODO add documentation and contracts here
	/*@
	@ requires
	@ ensures
	@ throws TooLazyException
	 */
	/**
	 *
	 */
	public void tryToMove() {
		// TODO Implement here.
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
	@ requires
	@ ensures
	@ throws TooLazyException
	 */
	/**
	 *
	 */
	// TODO add documentation and contracts here
	public void moveMultipleSteps(int numberOfSteps) {
		// TODO Implement here.


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

	// TODO add documentation and contracts here
	public boolean isCaged() {
		// TODO Implement here.
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
