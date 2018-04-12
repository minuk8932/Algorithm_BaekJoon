package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2960 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] prime = new boolean[N + 1];

		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			for (int j = i; j <= N; j += i) {
				if (prime[j] == false) {
					cnt++;
					prime[j] = true;
					if (cnt == K) {
						sb.append(j).append("\n");
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
}
