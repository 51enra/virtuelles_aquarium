
public class Fishlover {

	public static void main(String[] args) throws InterruptedException{
		// create aquarium
		// add a number of fish
		// Loop to show fish tank

		Fishtank fishtank = new Fishtank(40, 5);
		for (int i = 0; i < 3; i++) {
			fishtank.addFish();
		}
		for (int i = 0; i < 100; i++) {
		fishtank.showTank();
		Thread.sleep(100);
		}
	}

}
