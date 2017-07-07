package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3076 {
	private static final String SPACE = " ";
	private static final String BLACK = "X";
	private static final String WHITE = ".";
	private static final String NEW_LINE = "\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), SPACE);
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		
		for (int row = 0; row < R; row++) {
			for (int re = 0; re < A; re++) {
				for (int col = 0; col < C; col++) {
					if ((col + row) % 2 == 0) {
						for(int i = 0; i < B; i++)
							sb.append(BLACK);
					} 
					else {
						for(int i = 0; i < B; i++)
							sb.append(WHITE);
					}
				}
				sb.append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}

}
