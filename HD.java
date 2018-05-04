
public class HD {

	public final static int hdSize = 10000000;
	PQueue hdd;
	private int noApps;
	public double amountOccupied;
	private double maxPrgSize;
	private boolean isFull;

	public HD() {
		hdd = new PQueue();
		amountOccupied = 0;
		maxPrgSize = 500000;
		noApps = 0;
		isFull = false;
	}

	public PCB ServeFromHD() {
		if (isFull) {
			isFull = false;
		}
		PCB a = hdd.deQueue().data;
		amountOccupied = amountOccupied - a.getPrgSize();
		return a;
	}

	public void addProgram(PCB p) {
		if (amountOccupied + p.getPrgSize() >= this.maxPrgSize) {
			isFull = true;
		}
		hdd.hardDiskEnqueue(p, p.getPrgSize());
		this.amountOccupied += p.getPrgSize();
		noApps++;
	}

	public double getMaxPrgSize() {
		return this.maxPrgSize;
	}

	public double getAvgPrgSize() {
		return ((double) this.getMaxPrgSize() / noApps);
	}

	public boolean isFull() {
		return isFull;
	}

	public double getOcc() {
		return this.amountOccupied;
	}

	public boolean isEmpty() {
		return hdd.empty();
	}
}
