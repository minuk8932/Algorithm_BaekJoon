package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 8974번: 희주의 수학시험
 *
 *	@see https://www.acmicpc.net/problem/8974/
 *
 */
public class Boj8974 {
	private static final int INF = 1_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] idx = new int[INF];
		for(int i = 1; i < INF; i++) {		// 인덱스계산 배열
			idx[i] = i + idx[i - 1];
		}
		
		int[] arr = new int[INF];
		boolean[] isVisited = new boolean[INF];
		
		for(int i = 1; i < INF; i++) {
			for(int j = 1; j < idx[i]; j++) {
				if(j < INF && !isVisited[j]) {		// 방문하지 않은 배열의 값을 각 숫자로 초기화
					isVisited[j] = true;
					arr[j] = i;
				}
			}
		}
		
		arr[0] = 1;		// 맨 처음 배열 초기화
		
		int sum = 0;
		for(int i = A - 1; i < B; i++) {	// 0 ~ B-1번째까지 값들을 더한 후
			sum += arr[i];
		}
		
		System.out.println(sum);		// 결과값 한번에 출력
	}
}