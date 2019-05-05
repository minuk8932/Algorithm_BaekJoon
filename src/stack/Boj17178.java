package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17178번: 줄 세우기
 *
 *	@see https://www.acmicpc.net/problem/17178/
 *
 */
public class Boj17178 {
	private static LinkedList<Ticket> list = new LinkedList<>();
	
	private static class Ticket implements Comparable<Ticket>{
		char alpha;
		int number;
		
		public Ticket(char alpha, int number) {
			this.alpha = alpha;
			this.number = number;
		}

		@Override
		public int compareTo(Ticket t) {
			if(this.alpha < t.alpha) {
				return -1;
			}
			else if(this.alpha > t.alpha) {
				return 1;
			}
			else {
				if(this.number < t.number) return -1;
				else if(this.number > t.number) return 1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Ticket[] t = new Ticket[N * 5];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 5; j++) {
				StringTokenizer st1 = new StringTokenizer(st.nextToken(), "-");
				int index = i * 5 + j;
				
				t[index] = new Ticket(st1.nextToken().charAt(0), Integer.parseInt(st1.nextToken()));
				list.add(t[index]);
			}
		}
		
		Arrays.sort(t);						// 입장순 정렬
		System.out.println(possible(N, t));
	}
	
	private static String possible(int n, Ticket[] arr) {
		ArrayDeque<Ticket> stack = new ArrayDeque<>();
		int loop = n * 5;
		
		for(int i = 0; i < loop; i++) {
			while(!list.isEmpty()) {
				Ticket current = list.getFirst();
				
				if(turn(current, arr[i])) {								// 현재 들어갈 사람
					list.remove();
					break;				
				}
				if(!stack.isEmpty() && turn(arr[i], stack.peek())) {	// 대기열에서 들어갈 사람
					stack.pop();
					break;
				}
				
				list.remove();
				stack.push(current);				// 대기열로 들어갈 사람
			}
			
			if(list.isEmpty()) {
				if(!stack.isEmpty() && turn(arr[i], stack.peek())) stack.pop();		// 대기열 비우기
			}
		}
		
		if(stack.isEmpty()) return "GOOD";
		return "BAD";
	}
	
	private static boolean turn(Ticket a, Ticket b) {
		return a.alpha == b.alpha && a.number == b.number ? true: false;
	}
}
