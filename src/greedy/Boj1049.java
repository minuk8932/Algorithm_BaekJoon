package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1049번: 기타줄
 *
 *	@see https://www.acmicpc.net/problem/1049/
 *
 */
public class Boj1049 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		

		int[] min = {Integer.MAX_VALUE, Integer.MAX_VALUE};		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			min[0] = Math.min(min[0], Integer.parseInt(st.nextToken()));		// 최소 패키지
			min[1] = Math.min(min[1], Integer.parseInt(st.nextToken()));		// 최소 낱개
		}
		
		System.out.println(getCost(N, M, min[0], min[1]));
	}
	
	private static int getCost(int n, int m, int pack, int one) {
		if(one * 6 < pack) {				// 6개를 사도 낱개가 이득인 경우
			if(n <= 6) return one * n;		// n이 6보다 작을때
			pack = one * 6;
		}
		
		if(n % 6 * one > pack) return n / 6 * pack + pack;		// 패키지가 가장 싼 경우
		else return n / 6 * pack + (n % 6 * one);				// 그외 최적
	}
}
