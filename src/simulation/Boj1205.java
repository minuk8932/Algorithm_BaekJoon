package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1205번: 등수 구하기
 *
 *	@see https://www.acmicpc.net/problem/1205/
 *
 */
public class Boj1205 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		System.out.println(getGrade(st, br, N, score, P));
	}
	
	private static int getGrade(StringTokenizer st, BufferedReader br, int n, int score, int p) throws Exception{
		if(n == 0) return 1;			// 혼자 랭크된 경우
		
		int[] points = new int[p];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		
		int stack = 0;
		int rank = 1;
		
		for(int i = 0; i < n; i++) {
			if(points[i] > score) {				// 점수가 자신보다 크면 랭크 ++
				if(stack == 0) rank++;
				else rank += stack;				// 쌓인 등수가 있다면 +쌓인등수
				
				stack = 0;
			}
			else if(points[i] == score) {		// 등수가 같은 경우 밀릴 경우를 대비해 값을 쌓아둠
				stack++;
			}
			else {
				break;
			}
		}
		
		if(stack != 0) {
			if(rank + stack > p) return -1;		// 쌓인 등수를 더 했을 때 랭크에 들지 못하는 경우
		}
		
		return rank > p ? -1 : rank;
	}
}
