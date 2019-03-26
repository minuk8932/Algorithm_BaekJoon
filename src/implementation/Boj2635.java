package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 2635번: 수 이어가기
 *
 *	@see https://www.acmicpc.net/problem/2635/
 *
 */
public class Boj2635 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(getSequence(N));
	}
	
	private static StringBuilder getSequence(int n) {
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> list = new LinkedList<>();
		int max = 0;
		
		for(int i = 1; i < n + 1; i++) {
			list = new LinkedList<>();
			int current = n, next = i;
			int count = 1;
			
			list.add(current);
			
			while(next >= 0) {					// 각 수열 원소와 길이 찾기
				int tmp = current - next;
				current = next;
				next = tmp;
				
				list.add(current);
				
				count++;
			}
			
			if(count > max) {
				max = count;
				
				sb = new StringBuilder();
				
				sb.append(max).append(NEW_LINE);				// 최대 길이로 갱신
				while(!list.isEmpty()) {
					sb.append(list.removeFirst()).append(SPACE);
				}
			}
		}
		
		return sb;
	}
}
