package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2563 {
	public static final int TEN = 10;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine().trim());

		int[][] area = new int[101][101];
		int sum = 0;
		int[] x = new int[N];
		int[] y = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			x[i] = Integer.parseInt(st.nextToken().trim());
			y[i] = Integer.parseInt(st.nextToken().trim());
		}
		for (int k = 0; k < N; k++) {
			for (int i = 1; i <= 100; i++) {
				for (int j = 1; j <= 100; j++) {
					if ((i > x[k] && i <= x[k] + 10) && (j > y[k] && j <= y[k] + 10) 
							&& (x[k] <= 90 && y[k] <= 90)) {
						area[i][j] = 1;
					}
				}
			}
		}

		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				sum += area[i][j];
			}
		}

		sb.append(sum);
		System.out.println(sb.toString());

	}

}
