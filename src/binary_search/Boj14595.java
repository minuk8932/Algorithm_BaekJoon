package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14595번: 동방 프로젝트 (Large)
 *
 *	@see https://www.acmicpc.net/problem/14595/
 *
 */
public class Boj14595 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] set = new int[N];

		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			set[x]++;		// 박살 경계
			set[y]--;
		}
		
		System.out.println(getRooms(N, set));
	}
	
	private static int getRooms(int n, int[] set) {
		int count = n;
		int sum = 0;
		
		for (int i = 0; i < n; i++) {
			sum += set[i];
			if (sum > 0) count--; 	// 반으로 나누어 뚫린 방의 갯수를 체크
		}
		
		return count;
	}
}
