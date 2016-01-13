class Stack {
	Node head = null, middle = null;
	int size = 0;
	
	public void setMidElem() {
		if (middle == null)
		{
			middle = head;
			return;
		}	
		if (size % 2 != 0) {
			middle = middle.next;
		}
	}
	public void push(int data) {
		Node temp = new Node(data);
		if (head == null ) {
			head = temp;
			size++;
			return;
		}
		head.next = temp;
		temp.prev = head;
		head = temp;
		size++;
		setMidElem();
	}
	public int pop() {
		Node prev = head;
		int temp = head.data;
		head = head.next;
		prev.next = null;
		middle = middle.prev;
		return temp;
	}
	public int popMiddleElem() {
		int T = middle.data;
		Node prev = middle.prev;
		prev.next = middle.next;
		middle.next = null;
		return T;
	}
	public void pushMiddleElem(int data) {
		
	}
	private class Node {
		Node next, prev;
		int data;
		Node(int data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
 	}
}

class StackWithMiddleElem {
	

}