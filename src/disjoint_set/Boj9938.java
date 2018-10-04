package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 9938번: 방 청소
 *
 *	@see https://www.acmicpc.net/problem/9938/
 *
 */
public class Boj9938 {
	private static final String SAVE = "LADICA";
	private static final String SPEND = "SMECE";
	private static final String NEW_LINE = "\n";
	
	private static int[] locker = null;
	private static boolean[] isVisited = null;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		locker = new int[L + 1];
		isVisited = new boolean[L + 1];
		
		for(int i = 1; i < L + 1; i++) {
			locker[i] = i;
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int l1 = Integer.parseInt(st.nextToken());
			int l2 = Integer.parseInt(st.nextToken());
			
			if(!isVisited[l1]) {		// 해당 서랍에 술이 들어있지 않은 경우
				isVisited[l1] = true;
				
				merge(l1, l2);			// 술을 담고 루트에 연결
			}
			else if(!isVisited[l2]) {
				isVisited[l2] = true;
				
				merge(l2, l1);
			}
			else if(!isVisited[find(locker[l1])]) {		// 서랍의 술들을 이동 가능할 경우
				isVisited[find(locker[l1])] = true;
				
				merge(l1, l2);							// 술을 옮기고, 새로운 술을 담음
			}
			else if(!isVisited[find(locker[l2])]) {
				isVisited[find(locker[l2])] = true;
				
				merge(l2, l1);
			}
			else {
				sb.append(SPEND).append(NEW_LINE);		// 술을 담을 수 없는 경우 마셔버림
			}	
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 비는 서랍을 찾기위해 두 서랍을 가져오는 메소드
	 * 
	 */
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		locker[x] = y;
		sb.append(SAVE).append(NEW_LINE);		// 서랍에 술을 담아줌
	}
	
	/**
	 * 남는 서랍을 찾는 메소드
	 * 
	 */
	private static int find(int bottle) {
		if(bottle == locker[bottle]) {
			return bottle;
		}
		
		return locker[bottle] = find(locker[bottle]);
	}
}
