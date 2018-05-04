import java.util.*;

public class IODevice {

	public void request(PCB p) {
		p.currentIO = (int) (Math.random() * (200 - 100 + 1) + 100);
		p.setState("Waiting");
		while (p.currentIO > 0) {
			p.IOTime++;
			p.currentIO--;

			// Busy IO Device Termination
			Random randObj = new Random();
			int rando = randObj.nextInt(100);
			if (rando >= 0 && rando <= 19) {

				break;
			}

		}
		p.setState("Ready");
	}

}
