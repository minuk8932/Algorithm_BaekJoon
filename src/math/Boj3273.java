package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3273번: 두 수의 합
 *
 *	@see https://www.acmipc.net/problem/3273/
 *
 */
public class Boj3273 {
	private static final int INF = 2_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[] seq = new boolean[INF];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			seq[Integer.parseInt(st.nextToken())] = true;
		}
		
		int k = Integer.parseInt(br.readLine());
		
		int res = 0;		
		for(int i = 0; i < seq.length; i++) {
			if(i >= k) continue;
			
			if(seq[i]) {
				int idx = k - i;
				
				if(seq[idx] && i != idx) {	// 서로다른 두 숫자중 i + idx가 k를 만족하는 경우 + 1
					seq[i] = false;
					res++;
				}
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
}
