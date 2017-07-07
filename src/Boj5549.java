import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5549 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(st.nextToken()) + 1;
		int N = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(br.readLine());

		int mapsJ = 0;
		int mapsI = 0;
		int mapsO = 0;

		int[][] totalJ = new int[M][N];
		int[][] totalI = new int[M][N];
		int[][] totalO = new int[M][N];

		for (int row = 1; row < M; row++) {
			String lines = br.readLine();

			for (int col = 1; col < N; col++) {

				switch (lines.charAt(col - 1)) {

				case 'J':
					mapsJ++;
					totalJ[row][col] += mapsJ;

					totalO[row][col] += mapsO;
					totalI[row][col] += mapsI;
					break;

				case 'I':
					mapsI++;
					totalI[row][col] += mapsI;

					totalJ[row][col] += mapsJ;
					totalO[row][col] += mapsO;
					break;

				case 'O':
					mapsO++;
					totalO[row][col] += mapsO;

					totalJ[row][col] += mapsJ;
					totalI[row][col] += mapsI;
					break;
				}

			}
		}

		while (K-- > 0) {

			st = new StringTokenizer(br.readLine(), SPACE);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			int J = 0;
			int O = 0;
			int I = 0;

			sb.append(J).append(SPACE).append(O).append(SPACE).append(I).append(NEW_LINE);

		}

		br.close();

		System.out.println(sb.toString());
	}

}
