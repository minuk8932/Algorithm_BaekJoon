package stack;

public class LinkedListStack {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.addLast(1);
		ll.addLast(2);
		ll.addLast(3);

		System.out.println(ll.toString());
		System.out.println(ll.get(2));
	}
	
}

class LinkedList {
	private int size;
	private Node rear;
	private Node head;

	public LinkedList() {
		this.size = 0;
		this.rear = null;
	}

	public void addLast(Object data) {
		Node node = new Node(data);

		if (size == 0) {
			head = rear = node;
		} else {
			rear.next = node;
			rear = node;
		}
		size++;
	}

	public void add(int idx, Object data) {

		if (idx >= size || idx < 0) {
			return;
		}

		Node currentNode = head;
		for (int i = 0; i < idx - 1; i++) {

			currentNode = currentNode.next;
		}

		Node node = new Node(data);
		node.next = currentNode.next;
		currentNode.next = node;
	}

	public Object get(int idx) {

		if (idx >= size || idx < 0 || size == 0) {
			return null;
		}
		if (size != 0) {
			Node currentNode = head;

			for (int i = 0; i <= idx; i++) {
				currentNode = currentNode.next;
			}

			return currentNode.data;
		}
		return null;
	}

	public int indexOf(Object data) {
		Node currentNode = head;
		for (int i = 0; i < size; i++) {
			if (currentNode.data == data) {
				return i;
			}
			currentNode = currentNode.next;
		}
		return -1;
	}

	public void remove(int idx) {
		if (size == 0) {
			return;
		}

		Node currentNode = head;

		for (int i = 0; i < idx - 1; i++) {
			currentNode = currentNode.next;
		}
		
		if (idx < size - 1) {

			Node removeNode = currentNode.next;
			Node nextNode = removeNode.next;
			currentNode.next = nextNode;
		}
		else{
			currentNode.next = null;
		}

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0 ? true : false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");

		if (size != 0) {
			Node currentNode = head;
			sb.append(currentNode.data).append(", ");
			while ((currentNode = currentNode.next) != null) {
				sb.append(currentNode.data).append(" ");
			}
		}

		sb.append(" ]");

		Node node = head;
		return null;

	}

	private static class Node {
		public Object data;
		private Node next;

		public Node(Object data) {
			this.data = data;
			this.next = null;
		}
	}
}