import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15886 {
	private static final int E = 1;
	private static final int W = -1;

	private static final char EAST = 'E';
	private static final char WEST = 'W';

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] way = br.readLine().toCharArray();

		int map[] = new int[N];
		int idx = 0;

		for (int i = 0; i < N; i++) {
			int cnt = 0;

			for (char tmp : way) {
				switch (tmp) {
				case EAST:
					cnt += E;
					idx = cnt + i;

					if (idx >= 0 && idx < N)
						map[idx]++;

					break;

				case WEST:
					cnt += W;
					idx = cnt + i;

					if (idx >= 0 && idx < N)
						map[idx]++;
					break;
				}
			}

		}
		
		int max = 0;

		for (int i = 0; i < N; i++) {
			if(max < map[i]) max = map[i];
		}
		
		int res = 0;
		for(int i = 0; i < N; i++) {
			if(max == map[i]) res++;
		}

		 System.out.println(res);
	}
}
