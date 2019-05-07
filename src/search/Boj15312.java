package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 15312: 이름 궁합
 *
 *	@see https://www.acmicpc.net/problem/15312/
 *
 */
public class Boj15312 {
	private static final int[] VALUES = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		
		System.out.println(coupled(A, B));
	}
	
	private static StringBuilder coupled(char[] arr, char[] brr) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0; i < arr.length; i++) {
			q.offer(VALUES[arr[i] - 'A']);
			q.offer(VALUES[brr[i] - 'A']);
		}
		
		int prev = 0;
		int post = 0;
		
		while(q.size() > 2) {
			int size = q.size() - 1;
			
			prev = q.poll();
			
			while(size-- > 0) {
				post = q.poll();
				
				q.offer((prev + post) % 10);			// 앞뒤로 하나씩 붙여가며 궁합 계산
				prev = post;
			}
		}
		
		if(!q.isEmpty()) return sb.append(q.poll()).append(q.poll());
		return sb.append(prev).append(post);
	}
}
