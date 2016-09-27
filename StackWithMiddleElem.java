class Stack {
	Node head = null, middle = null;
	int size = 0;
	
	public void setMidElem() {
		if (middle == null) {
			middle = head.prev;
			return; 
		}
		if (size % 2 != 0) {
			middle = middle.next;
		}
		//System.out.println(size + " : " + middle.data);
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
	public int pop() throws Exception{
		if (head == null)
			throw new Exception("Underflow!");
		Node prev = head;
		int temp = head.data;
		head = head.next;
		prev.next = null;
		middle = middle.prev;
		size--;
		return temp;
	}
	public int popMiddleElem() throws Exception{
		if (middle == null)
			throw new Exception("No middle element");
		int T = middle.data;
		Node temp = middle.prev;
		temp.next = middle.next;
		middle.next.prev = temp;
		middle.next = null;
		middle.prev = null;
		size--;
		if (size % 2 == 0) 
			middle = temp;
		else
			middle = temp.next;
		return T;
	}
	public int findMiddleElem() throws Exception{
		if (middle== null)
			throw new Exception("No middle element");
		return middle.data;
	}
	public void print() {
		Node temp = head;
		while(temp != null) {
			System.out.print(temp.data + " : ");
			temp = temp.prev;
		}
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
	public static void main(String[] args) throws Exception{
		Stack stack = new Stack();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		//stack.push(50);
		//stack.findMiddleElem();
		System.out.println(" Mid :" + stack.findMiddleElem());
		System.out.println(stack.popMiddleElem());
		stack.print();
		System.out.println();
		System.out.println(" Mid :" + stack.findMiddleElem());
		System.out.println(stack.pop());
	}

}