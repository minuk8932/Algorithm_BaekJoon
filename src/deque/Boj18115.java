package deque;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18115번: 카드 놓기
 *
 *	@see https://www.acmicpc.net/problem/18115/
 *
 */
public class Boj18115 {
	private static final String SPACE = " ";
	private static Deque<Integer> deq = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			deq.add(i + 1);
		}
		
		System.out.println(getSequence(N, A));
	}
	
	private static String getSequence(int n, int[] arr) {
		int[] res = new int[n];
		int len = n;
		
		for(int query: arr) {					// put cards reversed
			if(query == 1) {
				res[deq.poll() - 1] = len;
			}
			else if(query == 2) {
				int tmp = deq.poll();
				res[deq.poll() - 1] = len;
				
				deq.addFirst(tmp);
			}
			else {
				res[deq.pollLast() - 1] = len;
			}
			
			len--;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(res[i]).append(SPACE);
		}
		
		return sb.toString();
	}
}
