package Floyd_Warshall;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2610번 : 회의준비
 *
 *	@see https://www.acmicpc.net/problem/2610/
 *
 */
public class Boj2610 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int INF = 101;
	private static int[] chk = null;
	private static int[] max = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] grp = new int[N + 1][N + 1];
		int grpCnt = 0;
		
		for(int i = 0; i < N + 1; i++){
			Arrays.fill(grp[i], INF);
		}
		
		for(int i = 0; i < M; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			grp[from][to] = grp[to][from] = 1;
		}
		
		floydWashall(grp, N);											// FloydWashall 알고리즘 수행
		
		int[][] isVisited = new int[N + 1][N + 1];
		chk = new int[N + 1];
		max = new int[N + 1];
		grpCnt = bfs(grp, N, isVisited);							// BFS 알고리즘을 통한 단일 위원회를 제외한 나머지 위원회의 갯수를 반환
		
		ArrayList<Integer> res = new ArrayList<>();
		
		for(int i = 1; i < N + 1; i++){									// 개인 별 회장 자격을 구함 : 개인이 전체의 의사전달하는 시간 중 최대값
			for(int j = 1; j < N + 1; j++){
				if(i != j && grp[i][j] != INF){
					max[i] = Math.max(max[i], grp[j][i]);
				}
			}
		}
		
		for(int i = 1; i < N + 1; i++){									// 만약 단일 위원회라면
			for(int j = 1; j < N + 1; j++){
				if(max[i] == 0 && i == j){								// 위원회수 +1 및 리스트에 위원회 회장을 담아줌
					grpCnt++;
					res.add(i);
				}
			}
		}
		
		int tmp = 0;
		int[] idx = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++){
			int min = Integer.MAX_VALUE;
			
			for(int j = 1; j < N + 1; j++){								// 같은 그룹내에서
				if(i == chk[j]){
					min = Math.min(min, max[j]);					// 최소 의사 전달 횟수를 가진 사람을 찾아냄 : 회장
					
					if(min == max[j]){
						idx[chk[j]] = j;									// 마지막 회장의 번호를 배열안에 차례로 담아줌
					}
				}
			}
		}
		
		for(int i = 1; i < N + 1; i++){
			if(idx[i] != 0){
				res.add(idx[i]);
			}
		}
		
		Collections.sort(res);
		
		StringBuilder sb = new StringBuilder();
		sb.append(grpCnt).append(NEW_LINE);					// 위원회 수를 버퍼에 담아줌
		
		for(int i = 0; i < res.size(); i++){							// 위원회의 각 회장을 버퍼에 담아줌
			sb.append(res.get(i)).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());						// 결과값 한번에 출력
	}
	
	/**
	 * Floyd-Washall 알고리즘
	 * 	@param map : 각 회원간 관계를 나타낸 배열
	 * 	@param N : 인원수
	 */
	private static void floydWashall(int[][] map, int N){
		for(int v = 1; v < N + 1; v++){
			for(int s = 1; s < N + 1; s++){
				for(int e = 1; e < N + 1; e++){
					if(map[s][e] > map[s][v] + map[v][e]){
						map[s][e] = map[s][v] + map[v][e];
					}
				}
			}
		}
	}
	
	/**
	 * BFS 알고리즘
	 * 	@param map : 각 회원간 관계를 나타낸 배열
	 * 	@param N : 인원수
	 * 	@param isVisited : 각 회원의 의사전달 수치를 받아 둘 배열
	 * 	@return : 단일 위원회를 제외한 나머지 위원회의 갯수
	 */
	private static int bfs(int[][] map , int N, int[][] isVisited){
		int cnt = 0;
		
		for(int from = 1; from < N + 1; from++){
			for(int to = 1; to < N + 1; to++){
				if(isVisited[from][to] == 0){ 
					if(map[from][to] != INF){
						cnt++;
						isVisited[from][to] = cnt;
						chk[from] = cnt;
						
						Queue<Integer> q = new LinkedList<>();
						q.offer(from);
						
						while(!q.isEmpty()){
							int current = q.poll();
							
							for(int next = to; next < N + 1; next++){
								if(next > 0 && next < N + 1 && current > 0 && current < N + 1){
									if(map[current][next] != INF && isVisited[current][next] == 0){
										isVisited[current][next] = cnt;
										chk[next] = cnt;
										
										q.offer(next);
									}
								}
							}
						}
					}
				}	
			}
			
		}
		return cnt;
	}
}
