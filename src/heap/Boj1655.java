package heap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * 	@author minchoba
 *	백준 1655번: 가운데를 말해요
 *
 *	@see https://www.acmicpc.net/problem/1655/
 *
 */
public class Boj1655 {
	private static PriorityQueue<Integer> left = new PriorityQueue<>();
	private static PriorityQueue<Number> right = new PriorityQueue<>();
	
	private static final String NEW_LINE = "\n";
	
	private static class Number implements Comparable<Number>{
		int num;
		
		public Number(int num) {
			this.num = num;
		}

		@Override
		public int compareTo(Number n) {
			return this.num > n.num ? -1 : 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			sb.append(process(Integer.parseInt(br.readLine()))).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int process(int x) {		
		if(right.isEmpty()) {				// 오른쪽 큐 맨 앞이 중앙 값
			right.offer(new Number(x));
			return right.peek().num;
		}
		
		if(right.peek().num < x) left.offer(x);		// 중앙 값과 입력 비교
		else right.offer(new Number(x));
		
		int diff = right.size() - left.size();
		if(diff >= 0 && diff <= 1) return right.peek().num;		// 골고루 배치된 경우
		
		if(diff < 0) right.offer(new Number(left.poll()));		// 아닌 경우
		else left.offer(right.poll().num);
		
		return right.peek().num;
	}
}
