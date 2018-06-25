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
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	private static final String NO = "Impossible";

	private static final int INF = 10_000;
	private static final int[] NUMS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	private static boolean[] isPrime = null;
	private static int[] isVisited = null;

	private static int prime1 = 0;
	private static int prime2 = 0;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		isPrime = new boolean[INF];
		Arrays.fill(isPrime, true);

		chkPrime();				// 1000에서 10000사이의 소수를 찾는 메소드

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			prime1 = Integer.parseInt(st.nextToken());
			prime2 = Integer.parseInt(st.nextToken());

			isVisited = new int[INF];

			int result = 0;

			if (prime1 != prime2) {			// 같은경우는 결과값 0 같지 않은경우 너비 우선탐색 실행
				result = bfs();
			}

			sb.append(result == -1 ? NO : result).append(END_LINE);		// 만약 탐색이 끝났는데 적절한 소수가 없다면 Impoosible 아니면 결과값을 버퍼에 담음
		}
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 * 	소수 찾기 메소드
	 */
	private static void chkPrime() {
		int loop = (int) Math.sqrt(INF);

		isPrime[1] = false;

		for (int i = 2; i < loop; i++) {		// 에라토스테네스의 체
			for (int j = 1000; j < INF; j++) {
				if(!isPrime[j]) continue;
				
				if (i != j && j % i == 0) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	/**
	 * 너비 우선 탐색 알고리즘
	 * @return: 해당 소수를 탐색하기까지 걸리는 횟수
	 */
	private static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(prime1);

		isVisited[prime1] = 1;

		while (!q.isEmpty()) {
			int current = q.poll();
			int uni = current - (current % 10);										// 소수에서 1의 자리를 0으로 
			int ten = current - ((current % 100) - (current % 10));			// 소수에서 10의 자리를 0으로 
			int hun = current - ((current % 1000) - (current % 100));		// 소수에서 100의 자리를 0으로 
			int tho = current - (current / 1000 * 1000);							// 소수에서 1000의 자리를 0으로 
			
			int[] nextSet = {tho, hun, ten, uni};			// 구한 값을 배열에 담고
			for(int i = 0; i < nextSet.length; i++){			// 반복문을 실행하는데
				int next = 0;
				
				for (final int N: NUMS) {							// 0~9 값을 차례로 돌려가면서 자릿수에 담아보면서 중첩 반복문 실행
					switch(i){
					case 0:
						next = nextSet[i] + (N * 1000);
						break;
						
					case 1:
						next = nextSet[i] + (N * 100);
						break;
					
					case 2:
						next = nextSet[i] + (N * 10);
						break;
						
					case 3:
						next = nextSet[i] + N;
						break;
					}
	
					if (next > 1000 && next < INF && isPrime[next]) {	// 값이 범위를 벗어나지 않으면서, 소수인 경우
						if (isVisited[next] == 0) {								// 동시에 아직 방문하지 않은 소수인 경우
							isVisited[next] = isVisited[current] + 1;	// 자릿수별 변경한 횟수를 배열에 담아줌
			
							if (next == prime2) {					// 목적한 소수를 찾았을 경우
								return isVisited[next] - 1;	// 해당 방문 배열의 값 - 1을 반환
							}
			
							q.offer(next);			// 아직 못찾은 경우 큐에 다음 값을 담아줌
						}
					}
				}
			}
		}
		return -1;			// 탐색 종료까지 목적 소수를 찾지 못한 경우
	}
}
