package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj5567 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		ArrayList<Integer> friendList = new ArrayList<>();
		boolean[][] link = new boolean[n + 1][n + 1];
		boolean[] isList = new boolean[n + 1];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (k == 1) {
				friendList.add(v);
				isList[v] = true;
			}

			link[k][v] = link[v][k] = true;
		}

		br.close();

		for (int k : friendList) {
			for (int v = 1; v <= n; v++) {
				if (link[k][v] && v != 1) {
					isList[v] = true;
				}
			}
		}

		int cnt = 0;

		for (int i = 1; i <= n; i++) {
			if (isList[i]) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}
}
