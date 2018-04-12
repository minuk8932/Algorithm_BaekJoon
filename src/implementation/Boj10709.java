package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj10709 {
	private static final int NONE = -1;
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String CLOUD = "c";

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		String[][] map = new String[H][W];

		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().split("");
		}
		br.close();

		int[][] res = new int[H][W];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j].equals(CLOUD)) {
					res[i][j] = 0;
				}

				else if (j >= 1 && res[i][j - 1] != NONE) {
					res[i][j] = res[i][j - 1] + 1;
				}

				else {
					res[i][j] = NONE;
				}
				sb.append(res[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		System.out.println(sb.toString());
	}
}