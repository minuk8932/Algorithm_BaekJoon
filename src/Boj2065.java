import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2065 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String L = "left";

	private static Wait[] wait;
	
	private static class Wait implements Comparable<Wait> {
		boolean pos;
		int from;
		int to;

		public Wait(boolean pos, int from, int to) {
			this.pos = pos;
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Wait w) {
			return this.from < w.from ? -1: 1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

//		wait = new Wait[N];
		PriorityQueue<Wait> wait = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), SPACE);
			int time = Integer.parseInt(st.nextToken());
			boolean site = st.nextToken().equals(L) ? true : false;

//			wait[i] = new Wait(site, time, 0);
			wait.offer(new Wait(site, time, 0));
		}

		System.out.println(getArrivedTime(N, t, M, wait));
	}

	private static String getArrivedTime(int N, int time, int capacity, PriorityQueue<Wait> pq) {
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		int t = 0;
		
		while(!pq.isEmpty()) {
			Wait current = pq.poll();
			if(flag != current.pos) {
				flag = !flag;
				t += current.from;
			}
			
			
		}

		return sb.toString();
	}
}
