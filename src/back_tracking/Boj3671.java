package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * 	@author minchoba
 *	백준 3671	번: 산업 스파이의 편지
 *
 *	@see https://www.acmicpc.net/problem/3671/
 *
 */
public class Boj3671 {
	private static boolean[] isPrime = new boolean[10_000_000];
	private static LinkedList<Integer> list;
	private static int count;
	
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int c = Integer.parseInt(br.readLine());
		
		prime();		// 소수 찾기
		
		while(c-- > 0) {
			char[] paper = br.readLine().toCharArray();
			list = new LinkedList<>();
			count = 0;
			
			for(int i = 0; i < paper.length; i++) {
				boolean[] used = new boolean[paper.length];
				backTracking(i, paper[i] + "", paper, used);
			}
			
			while(!list.isEmpty()) {		// 탐색시 false가 된 소숫값을 다시 true로 변경
				int num = list.remove();
				isPrime[num] = true;
			}
			
			sb.append(count).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}
	
	private static void prime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i < isPrime.length; i++) {			// 에라토스테네스의 체
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}
	}
	
	private static void backTracking(int idx, String current, char[] arr, boolean[] used) {
		if(current.length() > arr.length) return;		// 길이가 주어진 문자열보다 길어진 경우
		int num = Integer.parseInt(current);
		
		used[idx] = true;

		if(isPrime[num]) {			// 확인된 소수를 배열에서 제거하고 리스트에 임시 저장
			list.add(num);
			
			isPrime[num] = false;
			count++;
		}
		
		for(int i = 0; i < arr.length; i++) {			
			if(used[i]) continue;				// 이미 사용된 숫자인 경우
			
			String next = current + arr[i];
			backTracking(i, next, arr, used);	// 다음으로 생성된 문자열로 재귀 탐색
			used[i] = false;					// 백트래킹
		}
	}
}
