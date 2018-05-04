public class PQNode {
	
	public PCB data;
	public double priority;
	public PQNode next;
	
	
	
	public PQNode() {
		next = null;
	}
	
  public PQNode(PCB e, double Priority) {
       data = e;
       this.priority = Priority;
  }
  
 
}