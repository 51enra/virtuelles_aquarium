
public class Fishtank {

	private int length;
	private int depth;
	private int noOfFish;
	private Fish[] fishPopulation;
	private String bottom;
	private String emptyLevel;

	public int getLength() {
		return this.length;
	}

	public int getDepth() {
		return this.depth;
	}

	public int getNoOfFish() {
		return this.noOfFish;
	}

	private void incrementNoOfFish() {
		this.noOfFish += 1;
	}

	public Fishtank(int length, int depth) {
		// creates a new empty fish tank instance
		if (length < 3)
			length = 3; // at least one fish size <><
		if (depth < 1)
			depth = 1;
		this.length = length;
		this.depth = depth;
		this.noOfFish = 0; // just to be cleanly initialized
		this.fishPopulation = new Fish[depth];
		this.createBottom();
		this.createEmptyLevel();
	}

	private void createEmptyLevel() {
		// create String that draws an empty depth level of the fishtank
		this.emptyLevel = "|";
		for (int i = 0; i < this.getLength(); i++)
			this.emptyLevel += " ";
		this.emptyLevel += "|";
	}

	private void createBottom() {
		// create String that draws the bottom of the fishtank
		this.bottom = "|";
		for (int i = 0; i < this.getLength(); i++)
			this.bottom += "_";
		this.bottom += "|";
	}

	public void addFish() {
		// puts a fish in the fishtank if a depth level is still free
		int freeLevels = this.getDepth() - this.getNoOfFish();
		if (freeLevels == 0) {
			System.out.println("With " + this.getNoOfFish() + " fish, the fishtank is fully occupied!");
		} else {
			// determine a free depth level of the fishtank where to place the fish.
			// if more than 1 level is free, the level will be picked randomly.
			int n = 0;
			if (freeLevels > 1) {
				n = (int) (Math.round(Math.random()) * (freeLevels - 1));
			}
			// find the n-th empty level
			int i = 0;
			while (n >= 0) {
				if (fishPopulation[i] == null)
					n--;
				i++;
			}
			fishPopulation[i-1] = new Fish(this.getLength());
			this.incrementNoOfFish();
		}
	}

	public void showTank() {
		for (int i = 0; i < this.getDepth(); i++) {
			Fish fish = fishPopulation[i];
			if (fish == null) {
				System.out.println(this.drawEmptyLevel());
			} else {
				System.out.println(this.drawFishLevel(fish));
			}
		}
		System.out.println(this.drawBottom());
		this.incrementTime();
	}

	private String drawBottom() {
		return this.bottom;
	}

	private String drawFishLevel(Fish fish) {
		// create String that draws a depth level of the fishtank which is occupied by a
		// fish
		int pos = fish.getPosition();
		String dir = fish.getDirection();
		String level = "|";
		if ("right".equals(dir)) {
			for (int i = 0; i < pos - 2; i++)
				level += " ";
			level += "><>";
			for (int i = pos + 1; i < this.length; i++)
				level += " ";
		} else {
			// direction equals left
			for (int i = 0; i < pos; i++)
				level += " ";
			level += "<><";
			for (int i = pos + 3; i < this.length; i++)
				level += " ";
		}
		level += "|";
		return level;
	}

	private String drawEmptyLevel() {
		return this.emptyLevel;
	}
	
	private void incrementTime() {
		for (int i = 0; i < this.getDepth(); i++) {
			Fish fish = fishPopulation[i];
			if (fish != null)
				fish.swim();
		}
	}
}
