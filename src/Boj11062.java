import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj11062 {
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] cards = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			sb.append(game(N, cards)).append(NEW_LINE);
		}

		System.out.println(sb);
	}
	
	private static int game(int n, int[] arr) {
		int[][] dp = new int[n][n];
		
		for(int i = 1; i < n; i++) {
			
		}
		
		return 0;
	}
}
