import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1963 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	private static final String NO = "Impossible";

	private static final int INF = 10_000;
	private static final char[] NUMS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static boolean[] isPrime = null;
	private static int[] isVisited = null;

	private static char[] prime1 = null;
	private static char[] prime2 = null;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		isPrime = new boolean[INF];
		Arrays.fill(isPrime, true);

		chkPrime();
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			prime1 = st.nextToken().toCharArray();
			prime2 = st.nextToken().toCharArray();

			isVisited = new int[INF];

			int result = 0;

			if (!prime1.equals(prime2)) {
				result = bfs();
			}

			sb.append(result == -1 ? NO : result).append(END_LINE);
		}
		System.out.println(sb.toString());
	}

	private static void chkPrime() {
		int loop = (int) Math.sqrt(INF);

		isPrime[1] = false;

		for (int i = 2; i < loop; i++) {
			for (int j = 1000; j < INF; j++) {
				if (i != j && j % i == 0) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	private static int makeInt(char[] num){
		int res = 0;
		
		for (int i = 0; i < num.length; i++) {
			res += Character.getNumericValue(num[i]) * Math.pow(10, num.length - 1 - i);
		}
		
		return res;
	}

	private static int bfs() {
		int endInt = makeInt(prime2);
		int startInt = makeInt(prime1);

		Queue<char[]> q = new LinkedList<>();
		q.offer(prime1);

		isVisited[startInt] = 1;

		while (!q.isEmpty()) {
			char[] current = (char[]) q.poll();
			int currentInt = makeInt(current);

			for (int i = 0; i < current.length; i++) {
				for (final char N : NUMS) {
					char tmp = current[i];
					current[i] = N;

					char[] next = current;

					int nextInt = makeInt(next);

					if (nextInt < 1000 || nextInt >= INF) {
						current[i] = tmp;
						continue;
					}

					if (!isPrime[nextInt]) {
						current[i] = tmp;
						continue;
					}

					if (isVisited[nextInt] != 0) {
						current[i] = tmp;
						continue;
					}

					isVisited[nextInt] = isVisited[currentInt] + 1;

//					System.out.println(nextInt + " " + isVisited[nextInt]);

					q.offer(next);

					if (nextInt == endInt) {
						return isVisited[nextInt] - 1;
					}

				}
			}
		}
		return -1;
	}
}
