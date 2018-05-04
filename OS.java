import java.util.Scanner;

public class OS {
	public static HD hardDisk = new HD();
	public static RAM ram = new RAM();
	public static int pid = 0;
	public static int hdJobs = 0; // The number of initially generated jobs
									// stored on the H-disk.
	public static double avgPrgSize = 0;

	// Fills the Hard Disk with random programs.
	public static void seedHD() {

		double totalPrgSize = 0;
		while (!hardDisk.isFull()) {
			PCB rando = new PCB(pid++);
			if ((hardDisk.amountOccupied + rando.getPrgSize() < hardDisk.getMaxPrgSize())) {
				hardDisk.addProgram(rando);
				hdJobs++;
				totalPrgSize += rando.getPrgSize();
			} else {
				break;
			}

		}
		avgPrgSize = totalPrgSize / hdJobs;
	}

	// Fills the RAM with the programs from the hard disk.
	public static void seedRAM() {
		while (!ram.isFull()) {
			if (hardDisk.hdd.length() > 0) {
				PCB rando = hardDisk.ServeFromHD();
				ram.addProgram(rando);
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		ram.allocateForOS();
		int options = 0;
		do {
			System.out.println("Welcome to the Multiprogramming Simulator\n");
			Scanner input = new Scanner(System.in);
			System.out.println("please select your option:");
			System.out.println("1 - Start the Simulation");
			System.out.println("2 - Exit\n");
			System.out.println("only enter the number of your choice: {ex. 1}");
			options = input.nextInt();
			switch (options) {
			case 1: {
				seedHD();

				CPU cpu = new CPU();

				while (hardDisk.hdd.length() > 0) {

					seedRAM();

					if (ram.ram.length() > 0) {
						cpu.machineExecutionCycle(ram);
					}

				}

				System.out.println("The number of initially generated jobs stored on the H-disk: " + hdJobs);
				System.out.println("The average program size of all jobs: " + hardDisk.getAvgPrgSize());
				System.out.println("The average number of jobs that have completed their execution normally: "
						+ cpu.getAvgNormal());
				System.out.println("The average number of jobs that have completed their execution abnormally: "
						+ cpu.getAvgAbnormal());
				System.out.println("The number of CPU bound jobs: " + cpu.getCpuBound());

				System.out.println("\n\nDo you wish to restart the simulation?");
				System.out.println("Enter: YES or NO");
				String yesOrNo = input.next();
				if (yesOrNo.equalsIgnoreCase("yes")) {
					System.out.println("\n\n");
					hardDisk = new HD();
					ram = new RAM();
					pid = 0;
					hdJobs = 0;
					avgPrgSize = 0;
					continue;
				}
				if (yesOrNo.equalsIgnoreCase("no")) {
					System.out.println("Goodbye!");
					System.exit(0);
				} else {
					System.out.println("you did not enter a valid input, so we terminated the simulation. Goodbye!");
					System.exit(0);
				}
				break;
			}
			case 2: {
				System.exit(0);
				break;
			}
			default: {
				System.out.println("wrong option, please enter only the number of your option.");
				break;
			}

			}
			;
		} while (!(options == 2));

	}
}
