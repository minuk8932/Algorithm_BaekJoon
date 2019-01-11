package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * 	@author minchoba
 *	백준 1963번: 소수경로
 *
 *	@see https://www.acmicpc.net/problem/1963/
 *
 */
public class Boj1963 {
	private static final char END_LINE = '\n';
	private static final String NO = "Impossible";

	private static final int INF = 10_000;
	private static final int[] NUMS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	private static boolean[] isPrime;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

        prime();	

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int prime1 = Integer.parseInt(st.nextToken());
			int prime2 = Integer.parseInt(st.nextToken());

			int[] isVisited = new int[INF];
			int result = 0;

			if (prime1 != prime2) {	
				result = bfs(prime1, prime2, isVisited);
			}

			sb.append(result == -1 ? NO : result).append(END_LINE);	
		}
		
		System.out.println(sb);
	}
	
	private static void prime() {
        isPrime = new boolean[INF];
		Arrays.fill(isPrime, true);
        
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i < INF; i++) {
            if(!isPrime[i]) continue;
            
			for (int j = i + i; j < INF; j += i) {
				isPrime[j] = false;
			}
		}
	}
	
	private static int bfs(int num1, int num2, int[]isVisited) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(num1);

		isVisited[num1] = 1;

		while (!q.isEmpty()) {
			int current = q.poll();
			int uni = current - (current % 10);							
			int ten = current - ((current % 100) - (current % 10));		
			int hun = current - ((current % 1000) - (current % 100));	
			int tho = current - (current / 1000 * 1000);	
			
			int[] nextSet = {tho, hun, ten, uni};	
			for(int i = 0; i < nextSet.length; i++){
				int next = 0;
				
				for (final int N: NUMS) {
					next = getNext(nextSet[i], i, N);			// 다음 소수 갱신
	
					if (next > 1000 && next < INF && isPrime[next]) {
						if (isVisited[next] == 0) {					
							isVisited[next] = isVisited[current] + 1;
			
							if (next == num2) return isVisited[next] - 1;			
							q.offer(next);
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	private static int getNext(int place, int idx, int num) {
		int x = (int) Math.pow(10, 3 - idx);
		return place + (num * x);
	}
}
