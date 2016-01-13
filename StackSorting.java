/**
* Program to sort a stack in descending order
* Problem as described in "Cracking the coding Interview Chapter 3 Problem # 3.6"
* Using additional data structure, we can do this in O(n^2) time [[even with recursion, we can do this in the same time]]
*/
class Stack {
	Node top = null;
	public void push(int data) {
		Node temp = new Node(data);
		temp.next = top;
		top = temp;
	}
	public int peek() throws Exception{
		if (top == null)
			throw new Exception("Empty stack");
		return top.data;
	}
	public boolean isEmpty() {
		return (top == null);
	}
	public int pop() throws Exception{
		if (top == null) 
		{
			throw new Exception("Empty stack");
		}		
		int T = top.data;
		Node temp = top.next;
		top.next  = null;
		top = temp;
		return T;
	}
	private class Node {
		int data;
		Node next;
		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	public void print() {
		Node temp = top;
		while(temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
	}
}

class StackSorting {
	
	public static void sortStack(Stack s1, Stack s2) throws Exception{
		if (s1.isEmpty())
			return;
		int x;
		while (!s1.isEmpty()) {
			x = s1.pop();
			while (  (!s2.isEmpty())  && (s2.peek() > x)) {
				s1.push(s2.pop());
			}
			s2.push(x);
		}
		s1 = s2;	//stack2 has the data in proper order, swap the pointers
		System.out.println("Data after swapping :");
		s1.print();
	}
	public static void main(String[] args) throws Exception{
		Stack stack1 = new Stack();
		stack1.push(7);
		stack1.push(10);
		stack1.push(1);
		stack1.push(0);
		
		Stack stack2 = new Stack();
		
		StackSorting.sortStack(stack1,stack2);
	}
}