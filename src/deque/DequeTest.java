package deque;
import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {
	public static void main(String[] args) throws Exception{
		Deque<Integer> deque = new LinkedList<>();
		
		System.out.println(deque);
		deque.addLast(1);
		deque.addLast(2);
		deque.addFirst(3);
		System.out.println(deque);
		deque.pollFirst();
		System.out.println(deque);
		deque.pollLast();
		System.out.println(deque);
	}
}
