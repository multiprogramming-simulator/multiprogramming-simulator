import java.util.Random;
import java.util.Timer;

public class CPU {
	private int cpuBound;
	private int normal;
	private int abnormal;
	private int numOfProgs;
	private double totalSize = 0;

	public void machineExecutionCycle(RAM r) {
		numOfProgs = r.ram.length();

		while (r.ram.length() > 0) {
			PCB current = r.serveFromRam();
			current.setState("Running");
			totalSize += current.getPrgSize();
			boolean flag = false;
			// ISRi

			while (current.numOfInstructions > 0 && current.getState().equalsIgnoreCase("Running")) {
				current.numOfInstructions--;
				current.CPUTime++;

				Random randObj = new Random();
				int rando;

				// Interrupt
				rando = randObj.nextInt(100);
				if (rando >= 0 && rando <= 9) {
					r.addProgram(current);
					break;
				}

				// IO Request
				rando = randObj.nextInt(100);
				if (rando >= 0 && rando <= 19) {
					IODevice io = new IODevice();
					io.request(current);
					r.addProgram(current);
					break;
				}

				// Normal Termination
				rando = randObj.nextInt(100);
				if (rando >= 0 && rando <= 4) {
					current.setState("Terminated Normally");
					flag = true;
					normal++;
					break;
				}

				// Abnormal Termination
				rando = randObj.nextInt(100);
				if (rando == 0) {
					current.setState("Terminated Abnormally");
					abnormal++;
					break;
				}

			}

			if (current.getState().contains("Normally")) {
				if (flag == false) {
					normal++;
				}
			}

			if (current.getState().contains("Terminated") && current.CPUTime > current.IOTime) {
				if (!current.cpuBound) {
					current.cpuBound = true;
					cpuBound++;
				}
			}

		}
	}

	// The average number of jobs that have completed their execution normally.
	public double getAvgNormal() {
		if (numOfProgs > 0)
			return (double) ((double) normal / (double) numOfProgs);
		return 0;
	}

	// The average number of jobs that have completed their execution
	// abnormally.
	public double getAvgAbnormal() {
		if (numOfProgs > 0)
			return (double) ((double) abnormal / (double) numOfProgs);
		return 0;
	}

	public int getNumOfProgs() {
		return this.numOfProgs;
	}

	public int getCpuBound() {
		return this.cpuBound;
	}

}
