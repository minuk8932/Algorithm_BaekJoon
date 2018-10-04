package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10775번: 공항
 *
 *	@see https://www.acmicpc.net/problem/10775/
 *
 */
public class Boj10775 {
	private static final int INF = 100_001;
	private static int[] plane = new int[INF];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());

		for (int i = 1; i < G + 1; i++) {
			plane[i] = i;
		}

		int res = 0;

		for (int i = 1; i < P + 1; i++) {
			int gate = Integer.parseInt(br.readLine());
			int docked = find(gate);		// 각 연결된 게이트를 찾고

			if (docked == 0) break;			// 도킹이 불가능하면 반복문종료
			merge(docked, docked - 1);		// 도킹이 가능하고, 현재 게이트가 도킹이 된 상태면 해당 게이트 - 1번째 게이트와 union 후
			++res;						// 도킹한 비행기 수 + 1
		}

		System.out.println(res);		// 도킹한 비행기 수 출력
	}

	/**
	 * union 메소드
	 * 
	 */
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);

		plane[x] = y;
	}
	
	/**
	 * find 메소드
	 * 
	 */
	private static int find(int air) {
		if (plane[air] == air) {
			return air;
		}

		return plane[air] = find(plane[air]);
	}
}
