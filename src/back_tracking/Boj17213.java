package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 17213번: 과일 서리
 *
 *	@see https://www.acmicpc.net/problem/17213/
 *
 */
public class Boj17213 {
	private static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		System.out.println(getCount(N, M));
	}
	
	private static int getCount(int n, int m) {
		int diff = m - n;
		
		if(diff == 0) return 1;			// 하나씩
		else if(diff < 0) return 0;		// 못훔침
		else return steal(n, m, 0);		// 경우의 수 구하기
	}
	
	private static int steal(int n, int m, int type) {
		if(n - 1 == type) return result + 1;
		
		for (int f = 1; f < m; f++) {			
			result = steal(n, m - f, type + 1);		// 훔칠 수 있는 갯수 만큼 백트래킹
		}
		
		return result;
	}
}
