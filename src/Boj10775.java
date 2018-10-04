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
			int docked = find(gate);

			if (docked == 0) break;
			merge(docked, docked - 1);
			++res;
		}

		System.out.println(res);
	}

	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);

		plane[x] = y;
	}

	private static int find(int air) {
		if (plane[air] == air) {
			return air;
		}

		return plane[air] = find(plane[air]);
	}
}
