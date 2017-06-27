package queue;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Queue
 * 	offer(add) : 넣기
 * 	poll : 빼기
 * 	peek : queue의 가장 첫번째 원소가 무엇인지
 * 	size : 크기
 * 
 */

public class QueueEx {
	public static void main(String[] args) throws Exception{
		Queue<Integer> queue = new LinkedList<>(); // 큐 객체를 사용 하는데 하위 객체인 링크드 리스트를 이용해 만들 것이다
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		
		System.out.println(queue.toString());
		System.out.println(queue.poll());
		System.out.println(queue.toString());
		System.out.println(queue.peek());
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());		
	}
}
