package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1731번: 추론
 *
 *	@see https://www.acmicpc.net/problem/1731/
 *
 */
public class Boj1731 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] sequence = new int[N];
		for(int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(getNext(N, sequence));
	}
	
	private static int getNext(int n, int[] seq) {		
		boolean flag = false;
		
		for(int i = 0; i < n - 1; i++) {
			if(seq[i + 1] % seq[i] != 0) {		// 등비가 아니면 flag 세우기
				flag = true;
				break;
			}
		}
		
		return flag ? seq[n - 1] + (seq[n - 1] - seq[n - 2]) : seq[n - 1] * (seq[n - 1] / seq[n - 2]);
	}
}
