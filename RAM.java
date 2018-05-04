
public class RAM {

	public final static int ramSize = 192000;
	public PQueue ram;
	private double amountOccupied;
	private boolean isFull;

	public RAM() {
		ram = new PQueue();
		amountOccupied = 0;
		isFull = false;
	}

	public PCB serveFromRam() {
		if (isFull) {
			isFull = false;
		}
		PCB p = ram.deQueue().data;
		this.amountOccupied = this.amountOccupied - p.getPrgSize();
		return p;
	}

	public boolean addProgram(PCB p) {
		if (amountOccupied + p.getPrgSize() < ramSize) {
			
				p.setState("Ready");
				ram.ramEnqueue(p, p.getNumOfInstructions());
				this.amountOccupied += p.getPrgSize();
				return true;
		}else{
			
			this.isFull = true;
		}
		return false;
	}

	public double getAmount() {
		return amountOccupied;
	}

	public boolean isFull() {
		return (amountOccupied == ramSize) || isFull;
	}

	public boolean isEmpty() {
		return ram.empty();
	}

	public void allocateForOS() {
		this.amountOccupied = 60000;
	}

}
