package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17842번: 버스 노선
 *
 *	@see https://www.acmicpc.net/problem/17842/
 *
 */
public class Boj17842 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] path = new int[N];
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			path[node1]++;						// add path
			path[node2]++;
		}
		
		int count = 1;
		
		for(int i = 0; i < N; i++) {
			if(path[i] > 1) continue;			// is start stop?
			count++;							// pairing
		}
		
		System.out.println(count / 2);
	}
}
