
/**
 * @author Jazzy Campbell
 * 250 886 086
 */
import java.io.IOException;

public class FindExit {
	/**
	 * Constructor for FindExit. Takes a string of the filename as a parameter
	 * 
	 * @param filename
	 */
	public FindExit(String filename) {
		try {
			Dungeon dun = new Dungeon(filename);
		} catch (IOException i) {
			System.out.println(filename + " has caused an IOException");
		}

	}

	/**
	 * A private method to check if the chamber given in the parameter is
	 * adjacent to a dragon
	 * 
	 * @param chamber
	 * @return
	 */
	private boolean adjacentToDragon(Hexagon chamber) {
		boolean tester = false;
		if (chamber != null) {
			for (int i = 0; i < 6; i++) {
				if (chamber.getNeighbour(i) != null) {
					Hexagon c = chamber.getNeighbour(i);
					if (c.isDragon()) {
						tester = true;
					}
				}
			}
		}
		return tester;
	}

	/**
	 * A private method to choose which chamber is the best to proceed through -
	 * going through empty, cacti, and lava chambers. Parameter is where the
	 * warrior currently is
	 * 
	 * @param chamber
	 * @return
	 */
	private Hexagon bestChamber(Hexagon chamber) {
		Hexagon c = null;
		for (int i = 0; i < 6; i++) {
			if (chamber.getNeighbour(i) != null) {
				Hexagon check = chamber.getNeighbour(i);
				if (!check.isMarked()) {
					if (check.isExit()) {
						c = check;
						return c;
					}
				}
			}
		}
		for (int x = 0; x < 6; x++) {
			if (chamber.getNeighbour(x) != null) {
				Hexagon check1 = chamber.getNeighbour(x);
				if (!check1.isMarked()) {
					if (check1.isEmpty()) {
						c = check1;
						return c;
					}
				}
			}
		}
		for (int k = 0; k < 6; k++) {
			if (chamber.getNeighbour(k) != null) {
				Hexagon check2 = chamber.getNeighbour(k);
				if (!check2.isMarked()) {
					if (check2.isCacti()) {
						c = check2;
						return c;
					}
				}
			}
		}
		for (int l = 0; l < 6; l++) {
			if (chamber.getNeighbour(l) != null) {
				Hexagon check3 = chamber.getNeighbour(l);
				if (!check3.isMarked()) {
					if (check3.isLava()) {
						c = check3;
						return c;
					}
				}
			}
		}
		return c;
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			throw new IllegalArgumentException("Please enter a file as a command line argument");
		}
		String dungeonFile = args[0];
		FindExit exit = new FindExit(dungeonFile);
		try {
			Dungeon layout = new Dungeon(dungeonFile);
			ArrayStack<Hexagon> dungLay = new ArrayStack<Hexagon>();
			Hexagon startCurrent = layout.getStart();
			dungLay.push(startCurrent);
			startCurrent.markStart();
			int numChambers = 0;

			while (!dungLay.isEmpty() && !dungLay.peek().isExit()) {
				Hexagon current = dungLay.peek();

				if (exit.adjacentToDragon(current)) {
					dungLay.pop().markPopped();
				} else {
					if (exit.bestChamber(current) == null) {
						dungLay.pop().markPopped();
					} else {
						Hexagon best = exit.bestChamber(current);
						dungLay.push(best);
						best.markPushed();
						numChambers++;
					}
				}
			}

			if (!dungLay.isEmpty() && dungLay.peek().isExit()) {
				System.out.println("The Exit has been found! \n" + numChambers
						+ " chambers have been crossed to path a way to the exit.");
			}
			if (dungLay.isEmpty()) {
				System.out.println("The exit has not been found");
			}
		} catch (IOException i) {
			System.out.println("IO Exeception");
		}
	}
}
