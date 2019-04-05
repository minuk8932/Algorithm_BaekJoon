package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12739번: 돌림판
 *
 *	@see https://www.acmicpc.net/problem/12739/
 *
 */
public class Boj12739 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] spin = new int[N];
		String line = br.readLine();
		
		for(int i = 0; i < N; i++) {
			int num = line.charAt(i) - 'B';
			if(num != 0) num = num < 10 ? 1: 2;
			
			spin[i] = num;
		}
		
		System.out.println(spinning(N, K, spin));
	}
	
	private static StringBuilder spinning(int n, int k, int[] arr) {
		StringBuilder sb = new StringBuilder();
		int[] color;
		
		while(k-- > 0) {
			int[] tmp = new int[n];
			
			for(int index = 0; index < n; index++) {
				int prev = index == 0 ? n - 1: index - 1;
				int post = (index + 1) % n;
				
				color = new int[3];				// R 2, G 1, B 0
				color[arr[prev]]++;
				color[arr[post]]++;
				color[arr[index]]++;
				
				if(isBlue(color)) tmp[index] = 0;			// 문제 조건에 따라 색칠
				else if(isRed(color)) tmp[index] = 2;
				else tmp[index] = 1;
			}
			
			for(int i = 0; i < n; i++) {					// 색 갱신
				arr[i] = tmp[i];
			}
		}
		
		color = new int[3];
		for(int i = 0; i < n; i++) {
			color[arr[i]]++;
		}
		
		return sb.append(color[2]).append(SPACE).append(color[1]).append(SPACE).append(color[0]);
	}
	
	private static boolean isBlue(int[] c) {
		return (c[0] == 1 && c[1] == 1 && c[2] == 1) || c[0] == 3 || c[1] == 3 || c[2] == 3 ? true: false;
	}
	
	private static boolean isRed(int[] c) {
		for(int current = 2; current >= 0; current--) {
			int next = current == 0 ? 2: current - 1;
			if(c[current] == 2 && c[next] == 1) return true;
		}
		
		return false;
	}
}
