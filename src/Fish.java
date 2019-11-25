
public class Fish {
	
	// private int depthLevel; //not needed actually by the fish
	private int position; // position of the fish head <><
	private String direction; //more descriptive than boolean?
	private int maxPos; // length of tank; minPos assumed to be 0
	
	private final int FISHBODYLENGTH = 2; // length without head; head length assumed 1.
	
	public int getMaxPos() {
		return maxPos;
	}
	public void setMaxPos(int maxPos) {
		this.maxPos = maxPos;
	}
	public int getPosition() {
		return position;
	}
	public String getDirection() {
		return direction;
	}
	private void setPosition(int position) {
		this.position = position;
	}
	private void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Fish(int maxPos) {
		// create a fish
		this.maxPos = maxPos;
		int fishBodySpace = 0;
		// determine initial swimming direction randomly
		if (Math.random() < 0.5) {
			this.direction = "left";
		} else {
			this.direction = "right";
			fishBodySpace = FISHBODYLENGTH; // fish head can't be at left fish tank wall
		}
        // determine initial fish position randomly
		this.position = ((int) Math.round(Math.random() * (maxPos - FISHBODYLENGTH - 1)) + fishBodySpace);
	}
	
	public void swim() {
		// change position by 1 or -1
		// change direction at tank walls and position by +-2
		if (this.getDirection().equals("left")) {
			if (this.getPosition() > 0) {
				this.setPosition(this.getPosition()-1);
			} else {
				// turn around at left wall
				this.setPosition(FISHBODYLENGTH);
				this.setDirection("right");
			}
		} else {
			if (this.getPosition() < this.getMaxPos() - 1) {
				this.setPosition(this.getPosition()+1);
			} else {
				// turn around at right wall
				this.setPosition(this.getMaxPos() - FISHBODYLENGTH - 1);
				this.setDirection("left");
			}	
		}
	}
}
