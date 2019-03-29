package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15464번: Bovine Shuffle
 *
 *	@see https://www.acmicpc.net/problem/15464/
 *
 */
public class Boj15464 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] move = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			move[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		int[] cows = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(shuffling(N, move, cows));
	}
	
	private static StringBuilder shuffling(int n, int[] m, int[] c) {
		StringBuilder sb = new StringBuilder();
		int[] tmp = new int[n];
		
		for(int x = 0; x < 3; x++) {						// shuffling
			for(int i = 0; i < n; i++) {
				int cow = c[i];
				
				for(int j = 0; j < n; j++) {
					if(m[j] == i) tmp[j] = cow;
				}
			}
			
			for(int i = 0; i < n; i++) {						// 3회차에 결과 저장
				if(x == 2) sb.append(tmp[i]).append(NEW_LINE);
				else c[i] = tmp[i];
			}
		}
		
		return sb;
	}
}
