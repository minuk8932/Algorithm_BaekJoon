import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2065 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String L = "left";

	private static Wait[] wait;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		wait = new Wait[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int time = Integer.parseInt(st.nextToken());
			boolean site = st.nextToken().equals(L) ? true : false;

			wait[i] = new Wait(site, time);
		}

		System.out.println(getArrivedTime(N, t, M));
	}

	private static class Wait {
		boolean pos;
		int dockTime;

		public Wait(boolean pos, int dockTime) {
			this.pos = pos;
			this.dockTime = dockTime;
		}
	}

	private static StringBuilder getArrivedTime(int N, int time, int capacity) {
		StringBuilder sb = new StringBuilder();
		int total = 0;
		int[] arr = new int[N];
		boolean boat = true;
		
		

		return sb;
	}
}
