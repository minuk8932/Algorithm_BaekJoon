package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6219번: 소수의 자격
 *
 *	@see https://www.acmicpc.net/problem/6219/
 *
 */
public class Boj6219 {
	private static boolean[] prime = new boolean[4_000_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		getPrime();
		System.out.println(process(A, B, D));
	}
	
	private static void getPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < 2_001; i++) {			// 미리 소수 찾기
			if(!prime[i]) continue;
			
			for(int j = i + i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}
	}
	
	private static int process(int from, int to, int target) {
		int count = 0;
		
		for(int i = from; i < to + 1; i++) {
			if(!prime[i]) continue;
			int value = i;
			
			while(value > 0) {				// 소수 중 해당 숫자를 포함하는 소수의 갯수 찾기
				int comp = value % 10;
				
				if(comp == target) {
					count++;
					break;
				}
				
				value /= 10;
			}
		}
		
		return count;
	}
}
