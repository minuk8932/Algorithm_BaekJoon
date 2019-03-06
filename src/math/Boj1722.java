package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1722번: 순열의 순서
 *
 *	@see https://www.acmicpc.net/problem/1722/
 *
 */
public class Boj1722 {	
	private static final String SPACE = " ";
	private static StringBuilder sb = new StringBuilder();
	
	private static long[] factorial;
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N + 1];
		getFactorial(N);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cmd = Integer.parseInt(st.nextToken());	
		
		if(cmd == 1) {
			long k = Long.parseLong(st.nextToken());
			permutation(N, k);
		}
		else {
			int[] seq = new int[N];
			
			for(int i = 0; i < N; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append(getCount(N, 0, seq));
		}
		
		System.out.println(sb);
	}
	
	private static void getFactorial(int n) {			// 팩토리얼 값 계산
		factorial = new long[n + 1];
		factorial[0] = factorial[1] = 1;
		
		for(int i = 2; i < n + 1; i++) {
			factorial[i] = factorial[i - 1] * i;
		}
	}
	
	private static void permutation(int n, long count) {
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n + 1; j++) {
				if(isVisited[j]) continue;
					
				if(factorial[n - i - 1] < count) {
					count -= factorial[n - i - 1];	// 몇번째에 어떤 수가 들어갈 것인가
				}
				else {
					arr[i] = j;
					isVisited[j] = true;			// 해당 위치에 수를 담아줌
					break;
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(arr[i]).append(SPACE);
		}
	}
	
	private static long getCount(int n, long k, int[] arr) {
		long count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < arr[i]; j++) {
				if(isVisited[j]) continue;
				count += factorial[n - i - 1];		// 값을 더하면서 몇번째 순열인지 찾아봄
			}
			
			isVisited[arr[i]] = true;
		}
		
		return count + 1;
	}
}
