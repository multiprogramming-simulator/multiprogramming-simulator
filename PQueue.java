public class PQueue {

	private int size;
	private PQNode head;

	public PQueue() {
		head = null;
		size = 0;
	}

	public int length() {
		return size;
	}

	public boolean full() {
		return false;
	}

	public boolean empty() {
		return head == null;
	}

	public void hardDiskEnqueue(PCB e, double pSize) {
		PQNode tmp = new PQNode(e, pSize);
		if ((size == 0) || (pSize < head.priority)) {
			tmp.next = head;
			head = tmp;
		} else {

			PQNode p = head;
			PQNode q = null;
			while ((p != null) && (pSize >= p.priority)) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
		}
		size++;
	}

	public void ramEnqueue(PCB e, int expectedTime) {
		PQNode tmp = new PQNode(e, expectedTime);
		if ((size == 0) || (expectedTime < head.priority)) {
			tmp.next = head;
			head = tmp;
		} else {

			PQNode ramNodeHead = head;
			PQNode ramNode = null;
			while ((ramNodeHead != null) && (expectedTime >= ramNodeHead.priority)) {
				ramNode = ramNodeHead;
				ramNodeHead = ramNodeHead.next;
			}
			tmp.next = ramNodeHead;
			ramNode.next = tmp;
		}
		size++;
	}

	public PQNode deQueue() {

		PQNode node = head;
		if (!(head.next == null)) {
			head = head.next;
		}
		size--;
		return node;

	}

}
