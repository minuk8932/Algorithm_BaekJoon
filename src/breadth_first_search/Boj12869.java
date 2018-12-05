package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 12869번: 뮤탈리스크
 *
 *	@see https://www.acmicpc.net/problem/12869/
 *
 */
public class Boj12869 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] SCV = new int[3];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		for(int i = 0; i < N; i++) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(attackCounts(N, SCV));		// 결과 출력
	}
	
	private static class Units{
		int hp1;
		int hp2;
		int hp3;
		int cnt;
		
		public Units(int hp1, int hp2, int hp3, int cnt) {
			this.hp1 = hp1;
			this.hp2 = hp2;
			this.hp3 = hp3;
			this.cnt = cnt;
		}
	}
	
	private static int attackCounts(int n, int[] arr) {
		final int[][] DAMAGES = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9 ,1}, {1, 3, 9}, {1, 9, 3}};
		boolean[][][][] isHitted = new boolean[61][61][61][20];		// 뚜드려 맞은 후 남는 체력의 경우의 수
		
		Queue<Units> q = new LinkedList<>();
		q.offer(new Units(arr[0], arr[1], arr[2], 0));
		
		isHitted[arr[0]][arr[1]][arr[2]][0] = true;
		
		while(!q.isEmpty()) {
			Units current = q.poll();
			
			if(isAllZero(current.hp1, current.hp2, current.hp3)) return current.cnt;	// 다 터진 경우 끝남
			
			for(final int[] DAM: DAMAGES) {
				int nextHp1 = setNext(current.hp1, DAM[0]);		// 뚜드려맞은 후 터졌으면 0 아니면 남은 체력 저장
				int nextHp2 = setNext(current.hp2, DAM[1]);
				int nextHp3 = setNext(current.hp3, DAM[2]);
				int nextCnt = current.cnt + 1;
				
				if(isHitted[nextHp1][nextHp2][nextHp3][nextCnt]) continue;
				isHitted[nextHp1][nextHp2][nextHp3][nextCnt] = true;
				
				q.offer(new Units(nextHp1, nextHp2, nextHp3, nextCnt));
			}
		}
		
		return 0;
	}
	
	private static int setNext(int origin, int deal) {
		int diff = origin - deal;
		
		if(diff < 0) return 0;
		return diff;
	}
	
	private static boolean isAllZero(int a, int b, int c) {
		if(a + b + c == 0) return true;
		else return false;
	}
}
