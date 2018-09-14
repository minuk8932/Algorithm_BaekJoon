package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15686번: 치킨 배달
 *
 *	@see https://www.acmicpc.net/problem/15686/
 *
 */
public class Boj15686 {
	private static final int INF = 1_000_001;
	private static final int STORE = 2;
	private static final int HOME = 1;
	
	private static int min = INF;
	private static int N = 0;
	private static int M = 0;
	private static int[][] map = null;
	
	private static ArrayList<Pos> chick = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == STORE) {
					chick.add(new Pos(i, j));
				}
			}
		}
		
		int[][] cost = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(cost[i], INF);
		}
		
		dfs(0, cost, 1);			// 깊이 우선 탐색을 통한 최솟 값 구하기
		
		System.out.println(min);	// 결과 값 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 깊이 우선 탐색 메소드
	 * 
	 */
	private static void dfs(int current, int[][] cost, int recur){
		int leng = chick.size();	// 치킨 집 갯수를 받아옴
		
		for(int i = current; i < leng; i++) {
			int[][] tmp = new int[N][N];
			
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					tmp[j][k] = cost[j][k];		// 현재까지 진행한 만큼의 치킨집 거리를 임시 배열에 저장
				}
			}
			
			Pos p = chick.get(i);				// 치킨집의 좌표를 받아와서
			int sum = getSum(p.x, p.y, tmp);	// 합의 최솟값을 메소드를 통해 구함
			
			if(M == recur) {		// M회 반복 완료시
				if(sum < min) {		// min보다 sum이 작다면 값 갱신
					min = sum;
				}
			}
			else {
				dfs(i + 1, tmp, recur + 1);	// M회보다 적게 반복된 경우 재귀 호출
			}
		}
	}
	
	/**
	 * 총합 구하기 메소드
	 * 
	 */
	private static int getSum(int row, int col, int[][] t) {
		int res = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == HOME) {		// 해당 위치가 집인 경우
					int tmp = Math.abs(row - i) + Math.abs(col - j);	// 집과 해당하는 치킨집의 맨하튼 거리를 구함
					
					if(t[i][j] > tmp) {		// 맨하튼 거리가 임시배열에 저장된 값보다 작은 경우
						t[i][j] = tmp;		// 값 갱신 후
					}
					
					res += t[i][j];		// 반환할 변수에 더해줌
				}
			}
		}
		
		return res; // 최소 합 반환
	}
}
