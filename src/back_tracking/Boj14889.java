package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14889번: 스타트와 링크
 *
 *	@see https://www.acmicpc.net/problem/14889/
 *
 */
public class Boj14889 {
	private static int diff = Integer.MAX_VALUE;
	
	private static boolean[] isVisited;
	private static int[][] poten;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		poten = new int[N][N];
		isVisited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				poten[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(N, 0, N / 2, 1);
		System.out.println(diff);
	}
	
	private static void backTracking(int n, int current, int depth, int count) {	
		if(current >= n) return;
		
		if(isVisited[current]) return;
		isVisited[current] = true;
		
		if(depth == count) {
			int[][] tmp = getPermutation(n, depth);			// 방문 배열로 만들어진 순열 저장 (팀 구성)
			
			int aTeam = getTeamPotential(tmp[0], depth);	// 구성된 팀 마다 값을 구함
			int bTeam = getTeamPotential(tmp[1], depth);
			
			diff = Math.min(diff, Math.abs(aTeam - bTeam));	// 최소 차이
			return;
		}
		
		for(int next = current + 1; next < n; next++) {
			if(isVisited[next]) continue;
			
			backTracking(n, next, depth, count + 1);
			isVisited[next] = false;					// backTracking
		}
	}
	
	private static int[][] getPermutation(int n, int size){
		int[][] arr = new int[2][size];
		int idx1 = 0, idx2 = 0;
		
		for(int i = 0; i < n; i++) {
			if(isVisited[i]) arr[0][idx1++] = i;
			else arr[1][idx2++] = i;
		}
		
		return arr;
	}
	
	private static int getTeamPotential(int[] arr, int size) {
		int sum = 0;
		
		for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++) {			
				sum += poten[arr[i]][arr[j]] + poten[arr[j]][arr[i]];
			}
		}

		return sum;
	}
}
