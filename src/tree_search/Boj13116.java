package tree_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13116번: 30번
 *
 *	@see https://www.acmicpc.net/problem/13116/
 *
 */
public class Boj13116 {
	private static final String NEW_LINE = "\n";
	private static final int ANS = 10;
	private static final int SIZE = 1024;
	
	private static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append(search(A, B)).append(NEW_LINE);
		}
		
		System.out.println(sb);		// 결과 출력
	}

	private static int search(int leaf1, int leaf2) {	// 공통된 리프노드를 찾는 메서드
		isVisited = new boolean[SIZE];
		
		for(int i = leaf1; i > 0; i /= 2) {
			isVisited[i] = true;
		}
		
		for(int i = leaf2; i > 0; i /= 2) {
			if(isVisited[i]) return i * ANS;
		}
		
		return 1 * ANS;
	}
}
