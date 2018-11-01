package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5054번: 주차의 신
 *
 *	@see https://www.acmicpc.net/problem/5054/
 *
 */
public class Boj5054 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			int[] store = new int[n];
			int max = -1, min = 10000;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				store[i] = Integer.parseInt(st.nextToken());
				max = Math.max(store[i], max);
				min = Math.min(min, store[i]);
			}
			
			sb.append((max - min) * 2).append(NEW_LINE);		// 최대와 최소를 뺀 값 * 2의 결과를 버퍼에 저장
		}
		
		System.out.println(sb.toString());			// 결과 값 한번에 출력
	}
}
