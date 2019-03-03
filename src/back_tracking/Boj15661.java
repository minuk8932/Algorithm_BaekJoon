package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15661번: 링크와 스타트
 *
 *	@see https://www.acmicpc.net/problem/15661/
 *
 */
public class Boj15661 {
	private static int diff = Integer.MAX_VALUE;
	
	private static boolean[] isVisited;
	private static int[][] poten;
	private static int[] size = new int[2];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		poten = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				poten[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int loop = N % 2 == 0 ? N / 2 : N / 2 + 1;
		
		for(int i = 0; i < loop; i++) {			// 인원수에 따라 달리 돌아간다, 경우마다 모두 탐색
			isVisited = new boolean[N];
			backTracking(N, i,loop, 1);
		}
			
		System.out.println(diff);
	}
	
	private static void backTracking(int n, int current, int depth, int count) {	
		if(current >= n) return;
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		if(depth == count) {
			int[][] tmp = getPermutation(n, depth);
			
			int aTeam = getTeamPotential(tmp[0], 0);
			int bTeam = getTeamPotential(tmp[1], 1);
			
			diff = Math.min(diff, Math.abs(aTeam - bTeam));	// 최소 차이
			return;
		}
		
		for(int next = current + 1; next < n; next++) {
			if(isVisited[next]) continue;
			
			backTracking(n, next, depth, count + 1);
			isVisited[next] = false;						//  백트래킹
		}
	}
	
	private static int[][] getPermutation(int n, int leng){
		int[][] arr = new int[2][leng];
		int idx1 = 0, idx2 = 0;
		
		for(int i = 0; i < n; i++) {
			if(isVisited[i]) arr[0][idx1++] = i;
			else arr[1][idx2++] = i;
		}
		
		size[0] = idx1;			// 참 거짓의 갯수가 서로 다르니 따로 크기를 잡아줌
		size[1] = idx2;
		
		return arr;
	}
	
	private static int getTeamPotential(int[] arr, int idx) {
		int sum = 0;
		
		for(int i = 0; i < size[idx]; i++) {
			for(int j = i + 1; j < size[idx]; j++) {				// 능력치를 더함
				if(arr[i] == arr[j]) continue;
				sum += poten[arr[i]][arr[j]] + poten[arr[j]][arr[i]];
			}
		}
		
		return sum;
	}
}
