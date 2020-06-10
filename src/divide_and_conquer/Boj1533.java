package divide_and_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1533번: 길의 개수
 *
 * @see https://www.acmicpc.net/problem/1533/
 *
 */
public class Boj1533 {
	private static final int MOD = 1_000_003;

	private static int N, M;
	private static long[][] org;
	private static HashMap<Integer, long[][]> visit = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = N * 5;
		int S = (Integer.parseInt(st.nextToken()) - 1) * 5;
		int E = (Integer.parseInt(st.nextToken()) - 1) * 5;
		int T = Integer.parseInt(st.nextToken());

		org = modeling();

		for(int i = 0; i < N; i++) {
			String line = br.readLine();

			for(int j = 0; j < N; j++) {
				int data = line.charAt(j) - '0';
				int row = i * 5;
				int col = j * 5;

				if(data <= 1) org[row][col] = data;					// graph remodeling by cost 1
				else if(data > 1) org[row][col + data - 1] = 1;
			}
		}

		org = recursion(T, org);
		System.out.println(org[S][E]);
	}

	private static long[][] modeling() {
		long[][] arr = new long[M][M];

		for(int i = 0; i < N; i++) {
			for(int j = 1; j < 5; j++) {
				int index = i * 5 + j;
				arr[index][index - 1] = 1;
			}
		}

		return arr;
	}

	private static long[][] recursion(int t, long[][] arr) {
		if(t == 1) return arr;

		if(visit.containsKey(t)) return visit.get(t);	// memoization

		long[][] half, halfN;
		int h = t / 2;

		if(t % 2 == 0) {								// divide & conquer
			half = recursion(h, arr);
			visit.put(h, half);

			return matrixProduct(half, half);
		}
		else {
			half = recursion(h, arr);
			halfN = recursion(h + 1, arr);
			visit.put(h, half);
			visit.put(h + 1, halfN);

			return matrixProduct(half, halfN);
		}
	}

	private static long[][] matrixProduct(long[][] src, long[][] snk) {
		long[][] arr = new long[M][M];

		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				long value = 0;

				for(int k = 0; k < arr.length; k++) {
					value = modulation(value, src[i][k], snk[k][j]);
				}

				arr[i][j] = value;
			}
		}

		return arr;
	}

	private static long modulation(long v, long s, long t){
		return ((v % MOD) + ((s % MOD) * (t % MOD)) % MOD) % MOD;
	}
}
