
public class PCB {
	
	private String state; //Program State
	private int pid; //Program ID
	
	private double prgSize; //Program Size
	private double execTime; //Expected Execution Time
	
	//start of time attributes, we assume that the clock is ticking every millisecond.
	public int numOfInstructions;
	public int IOTime, CPUTime, currentIO;
	//end of time attributes.
	public boolean cpuBound;
	
	public PCB(int pid) {
		this.pid = pid;
		cpuBound = false;
		this.state = "New";
		
		this.prgSize = Math.random() * (16384 - 16 + 1) + 16; //Uniform Random Generation 
		this.execTime = Math.random() * (512 - 16 + 1) + 16;
		
		//while the MEC executes one instruction every with every tick of the clock, we here treat the number of instructions as the exact 
		//execution simulated time (in milliseconds) of the program.
		this.numOfInstructions = (int) (Math.random() * (this.execTime - 1 + 1) + 1);//Exact execution time relative to number of instructions
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		//The state is set so the first letter is uppercase (Readability)
		state = state.toLowerCase();
		state = state.substring(0, 1).toUpperCase() + state.substring(1);
		
		this.state = state;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public double getPrgSize() {
		return prgSize;
	}

	public void setPrgSize(double prgSize) {
		if(prgSize >= 16 && prgSize <= 16384)
			this.prgSize = prgSize;
		else 
			System.out.printf("%d is not within limit of 16KB to 16384KB.\n", prgSize);
	}

	public double getExecTime() {
		return execTime;
	}

	public void setExecTime(double execTime) {
		if(execTime >= 16 && execTime <= 512)
			this.execTime = execTime;
		else
			System.out.printf("%d is not within limit of 16ns to 512ns.\n", execTime);
	}
	
	public int getNumOfInstructions() {
		return this.numOfInstructions;
	}
	
}
