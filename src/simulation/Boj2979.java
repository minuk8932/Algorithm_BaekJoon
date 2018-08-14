package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2979번: 트럭 주차
 *
 *	@see https://www.acmicpc.net/problem/2979/
 *
 */
public class Boj2979 {
	private static final int INF = 101;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] cost = new int[INF];
		int loop = 3;
		
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 시간에 따른 차의 갯수를 배열에 더해줌
			for(int i = from; i < to; i++) {
				cost[i]++;
			}
		}
		
		int res = 0;
		
		for(int i = 1; i < INF; i++) {
			if(cost[i] == 0) continue;
			
			// 배열의 값에 따라 차의 수를 곱해서 값을 더함
			if(cost[i] == 1) res += A;
			else if(cost[i] == 2) res += (B * 2);
			else res += (C * 3);
		}
		
		System.out.println(res);	// 결과값 출력
	}
}
