package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5534번: 간판
 *
 *	@see https://www.acmicpc.net/problem/5534/
 *
 */
public class Boj5534 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count = 0;
		
		char[] target = br.readLine().toCharArray();
		while(N-- > 0) {
			count += matching(target, br.readLine().toCharArray());
		}
		
		System.out.println(count);
	}
	
	private static int matching(char[] t, char[] in) {
		for(int i = 0; i < in.length; i++) {
			int idx = 0;
			
			if(in[i] == t[idx]) {
				for(int j = 1; j < in.length; j++) {
					for(int k = i; k < in.length; k += j) {		// 간판생성
						if(in[k] != t[idx]) break;
						if(idx == t.length - 1) return 1;
						
						idx++;
					}
					
					idx = 0;
				}
			}
		}
		
		return 0;			// 간판 생성 불가
	}
}
