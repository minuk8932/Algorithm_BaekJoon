package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10868번: 최솟값
 *
 *	@see https://www.acmicpc.net/problem/10868/
 *
 */
public class Boj10868 {
	private static int[] tree = null;
	
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000_001;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int S = 1;
		while(S < N) S <<= 1;
		
		tree = new int[S * 2];		// 트리 크기 설정
		Arrays.fill(tree, INF);		// 최솟값을 구해야 하므로 입력 가능한 수보다 더 큰 수를 배열에 채움
		
		for(int i = S; i < S + N; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = tree.length - 1; i >= 0; i -= 2) {		// 트리 값 초기화
			tree[i / 2] = Math.min(tree[i], tree[i - 1]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(getMin(S + from - 1, S + to - 1)).append(NEW_LINE);	// getMin 메소드를 통해 구간 최소를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 구간 최솟값 메소드
	 * 
	 */
	private static int getMin(int seg1, int seg2) {
		int min = INF;
		
		while(seg1 <= seg2) {
			if(seg1 % 2 == 1) {						// 시작 구간이 홀수인 경우
				min = Math.min(min, tree[seg1]);
				seg1++;
			}
			
			if(seg2 % 2 == 0) {						// 끝 구간이 짝수인 경우
				min = Math.min(min, tree[seg2]);
				seg2--;
			}
			
			seg1 /= 2;							// 구간을 2개씩 줄여들어감 -> 2진트리 형태이므로 두개씩 감소
			seg2 /= 2;
		}
		
		return min;			// 최종 최솟값을 반환
	}
}
